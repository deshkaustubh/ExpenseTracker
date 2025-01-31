# Learnings from the Project 

* Working with the Room Database
* Faced Issues with kapt/ksp, also depreciated gradle and also version not supported
### Utils.kt
* ```kotlin
  object Utils {

  fun formateDateToHumanReadableForm(dateInMillis: Long): String {
  val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
  return dateFormatter.format(dateInMillis)
  }
  }
  ```

The dateFormatter creates an instance of SimpleDateFormat with the pattern dd/MM/yyyy to format the date
Locale.getDefault() sets the locale of the device, ensuring the formatting is suitable for user's region.
dateFormatter formats the dateInMillis and returns string

We use this later on in our code in Add Expense to store date.

*  ```kotlin
   fun AddExpense() {
      val coroutineScope = rememberCoroutineScope()}
```
A coroutine is like a task that can pause and resume without blocking everything else around it. Imagine you’re cooking multiple dishes at once. You might put one dish on the stove and let it simmer while you chop vegetables for another dish. You’re not waiting around doing nothing—you’re multitasking efficiently.
Similarly, a coroutine can start a task, pause it while waiting for something (like data from the internet), and do other things in the meantime. This helps your app run smoothly and respond quickly, even if some tasks take a while to complete.

val coroutineScope = rememberCoroutineScope() -> Imagine you have a helper who can do different tasks for you without you having to keep track of them all the time. rememberCoroutineScope() gives you such a helper specifically for handling background tasks. It ensures that even if your app's UI changes or updates, you still have the same helper doing the tasks for you.