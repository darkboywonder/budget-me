package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Expense {
        @Id
        @GeneratedValue
        private int id;
        @NotBlank(message= "Name is required")
        private String name;

        private int amount ;
        private String frequency;
        private  boolean is_repeated;
        private String due_date;
        private String tag;


        public Expense(String name,int amount, String frequency, boolean is_repeated, String due_date, String tag) {
            this.name = name;
            this.amount = amount;
            this.frequency = frequency;
            this.is_repeated = is_repeated;
            this.due_date = due_date;
            this.tag = tag;
        }

        public Expense() {}
        public int getId() {

            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAmount() {

            return amount;
        }

        public void setAmount() {
            this.amount = amount;
        }

        public String getFrequency() {
            return frequency;
        }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean getIs_repeated() {
        return is_repeated;
    }

    public void setIs_repeated(boolean is_repeated) {
        this.is_repeated = is_repeated;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;
        Expense expense = (Expense) o;
        return id == expense.id;
    }

    @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        public String toString() {

            if (this.tag.equals("")){
                this.tag = "Data not available";
            }
            if (this.frequency.equals("")) {
                this.frequency = "Data not available";
            }
            return "\n" + "ID: " + getId() + "\n" +
                    "Name: " + getName() + "\n" +
                    "Amount: " + getAmount() + "\n" +
                    "Frequency: " + getFrequency() + "\n" +
                    "Current due date: "  + getDue_date() + "\n" +
                    "Repeating: " + getIs_repeated() + "\n" +
                    "Tag: " + getTag() + "\n";

        }
    }




