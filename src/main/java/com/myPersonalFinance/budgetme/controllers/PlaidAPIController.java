package com.myPersonalFinance.budgetme.controllers;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.myPersonalFinance.budgetme.data.AccessTokenRepository;
import com.myPersonalFinance.budgetme.data.TransactionsRepository;
import com.myPersonalFinance.budgetme.data.UserRepository;
import com.myPersonalFinance.budgetme.models.ActualTransaction;
import com.myPersonalFinance.budgetme.models.PlaidAccessToken;
import com.myPersonalFinance.budgetme.models.User;
import com.plaid.client.ApiClient;
import com.plaid.client.model.*;
import com.plaid.client.request.PlaidApi;
import okhttp3.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/plaid/")
    class PlaidAPIController {
    //Repositories
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccessTokenRepository accessTokenRepository;
    @Autowired
    private TransactionsRepository transactionsRepository;

    private static PlaidApi plaidClient;

    //Used to send the link token to the frontend instead of a response body
    public static class LinkToken {
        @JsonProperty
        private String linkToken;


        public LinkToken(String linkToken) {
            this.linkToken = linkToken;
        }
    }


    @GetMapping(path = "generateLinkToken")
    public LinkToken handle(@CookieValue(value = "sessionId") int sessionId) throws IOException {

        // Create your Plaid client

        HashMap<String, String> apiKeys = new HashMap<String, String>();
        apiKeys.put("clientId", "64a74bf17c8ccb0013c8bf5c");
        apiKeys.put("secret", "d2c7d5dd31c038447448eed176d954");
        apiKeys.put("plaidVersion", "2020-09-14");
        ApiClient apiClient = new ApiClient(apiKeys);
        apiClient.setPlaidAdapter(ApiClient.Sandbox);

        plaidClient = apiClient.createService(PlaidApi.class);

        // Get the clientUserId by searching for the current user

        LinkTokenCreateRequestUser user = new LinkTokenCreateRequestUser()
                .clientUserId(String.valueOf(sessionId));

        // Create a link_token for the current user

        LinkTokenCreateRequest request = new LinkTokenCreateRequest()
                .user(user)
                .clientName("Budget Me")
                .products(Arrays.asList(Products.fromValue("assets"), Products.fromValue("transactions")))
                .countryCodes(Arrays.asList(CountryCode.US))
                .language("en");
        //Sends the request to plaid, the data is returned in response.body()
        Response<LinkTokenCreateResponse> response = plaidClient

                .linkTokenCreate(request)

                .execute();

        // Send the data to the frontend, should have used a response body but oh well


        return new LinkToken(response.body().getLinkToken());
    }

    @PostMapping(path = "createAccessToken", consumes = "text/plain")
    public ResponseEntity createAccessToken(@RequestBody String public_token, @CookieValue(value = "sessionId") int sessionId) throws IOException {
        //Creates a request object with the public token to be exchanged
        ItemPublicTokenExchangeRequest request = new ItemPublicTokenExchangeRequest()
                .publicToken(public_token);
        //Sends the request to plaid and returns with the access token, an id, and a request id in the response
        Response<ItemPublicTokenExchangeResponse> response = plaidClient
                .itemPublicTokenExchange(request)
                .execute();
        //Checks the repository to see if the bank information is already linked
        PlaidAccessToken checkForSavedToken = accessTokenRepository.findByitemId(response.body().getItemId());
        //Returns an error if the bank account has an access token
        if (checkForSavedToken != null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bank account already registered!");
        }
        //Creates an access token object which relates to the current user and the current item associated with
        //a bank account and saves it to the database below
        else if (checkForSavedToken == null) {
            User user = userRepository.findById(sessionId);
            PlaidAccessToken plaidAccessToken =
                    new PlaidAccessToken(response.body().getAccessToken(), user, response.body().getItemId());
            accessTokenRepository.save(plaidAccessToken);
            return ResponseEntity.ok("Access token saved");
        }
        //Don't know when this would be used but figured it should be here
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong...");
        }


    }

    @GetMapping(path = "getTransactionData")
    public ResponseEntity getPlaidTransactions(@CookieValue(value = "sessionId") int sessionId) throws IOException {
        //Grabs a list of access tokens from the database using the logged in user's id and the current user
        List<PlaidAccessToken> userAccessTokens = accessTokenRepository.findByUserId(sessionId);
        User user = userRepository.findById(sessionId);

        //Initializes the plaid client if it's null
        if (plaidClient == null){
            HashMap<String, String> apiKeys = new HashMap<String, String>();
            apiKeys.put("clientId", "64a74bf17c8ccb0013c8bf5c");
            apiKeys.put("secret", "d2c7d5dd31c038447448eed176d954");
            apiKeys.put("plaidVersion", "2020-09-14");
            ApiClient apiClient = new ApiClient(apiKeys);
            apiClient.setPlaidAdapter(ApiClient.Sandbox);

            plaidClient = apiClient.createService(PlaidApi.class);
        }
        //Loops through the access token objects, puts the plaid Transaction objects from every access token in an arraylist without separating them by bank
        //Maybe change this eventually and track it by bank account as well?
        List<Transaction> added = new ArrayList<Transaction>();
        for (int i = 0; i < userAccessTokens.size(); i++) {
            //Gets the cursor that plaid uses to ignore old data from the access token object, or null
            String transactionCursor = userAccessTokens.get(i).getTransactionCursor();

            TransactionsSyncRequestOptions options = new TransactionsSyncRequestOptions()
                    .includePersonalFinanceCategory(true);
            //hasMore is returned by plaid, stops the loop for an access token when false
            boolean hasMore = true;
            while(hasMore == true){
                System.out.println(hasMore);

                //Creates a request object to send to plaid with the user and cursor
                TransactionsSyncRequest request = new TransactionsSyncRequest()
                        .accessToken(userAccessTokens.get(i).getAccessToken())
                        .cursor(transactionCursor)
                        .options(options);
                //Sends the request to plaid, the data is returned in response.body()
                Response<TransactionsSyncResponse> response = plaidClient
                        .transactionsSync(request)
                        .execute();
                hasMore = response.body().getHasMore();
                transactionCursor = response.body().getNextCursor();
                added.addAll(response.body().getAdded());

            }
            //Saves the cursor for each access token object after all transactions have been called for that access token
            userAccessTokens.get(i).setTransactionCursor(transactionCursor);
            accessTokenRepository.save(userAccessTokens.get(i));
        }
        //Iterates through the array of premade plaid objects, converts them to our local type, and saves them to the db
        for(int i = 0; i < added.size(); i++){
            ActualTransaction actualTransaction =
                    new ActualTransaction(user, added.get(i).getAmount(), added.get(i).getDate(), added.get(i).getName(), readability(added.get(i).getPersonalFinanceCategory().getPrimary()));
            //System.out.println(actualTransaction);
            transactionsRepository.save(actualTransaction);
        }
        return ResponseEntity.ok("Transactions Fetched");
    }
    //Removes the _ between words and replaces it with a space and converts the caps to pascal case
    public String readability(String personalFinance){
        String target = "_";
        String replacement = " ";
        String personalFinanceSpace = personalFinance.replace(target, replacement);
        String[] arr = personalFinanceSpace.toLowerCase().split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        System.out.println(sb.toString().trim());
        return sb.toString().trim();
    }

}
