package com.example.expensetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDataBase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        const val DATABASE_NAME = "expense_database"

        @JvmStatic
        fun getInstance(context: Context): ExpenseDataBase {
            return Room.databaseBuilder(
                context,
                ExpenseDataBase::class.java,
                DATABASE_NAME
            ).addCallback(object: Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                }

                fun InitBasicData(context: Context) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getInstance(context).expenseDao()
//                        dao.insertExpense(ExpenseEntity(1, "Salary", 500000.00, System.currentTimeMillis(), "Salary", "Income"))
//                        dao.insertExpense(ExpenseEntity(2, "Paypal", 1000.00, System.currentTimeMillis(),"Paypal", "Income"))
//                        dao.insertExpense(ExpenseEntity(3, "Netflix", 1000.00, System.currentTimeMillis(), "Netflix", "Expense"))
//                        dao.insertExpense(ExpenseEntity(4, "Starbucks", 500000.00, System.currentTimeMillis(), "Starbucks", "Expense"))
                    }
                }
            }
            ).build()
        }
    }
}

