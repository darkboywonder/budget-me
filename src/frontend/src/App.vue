<template>
  <hello-world/>
  <Expense-List/>
</template>

<script>
import HelloWorld from './components/Example.vue'
import ExpenseList from './components/ExpenseList.vue'

export default {
  name: 'App',
  components: {
    HelloWorld,
    ExpenseList
  }
}
</script>


