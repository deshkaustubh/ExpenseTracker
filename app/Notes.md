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
