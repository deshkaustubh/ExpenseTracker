package com.example.expensetracker.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.expensetracker.R
import com.example.expensetracker.widget.ExpenseTextView


@Composable
fun AddExpense() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, list, card, topBar) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_topbar),
                contentDescription = null,
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                .constrainAs(nameRow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Text(
                    text = "Add Expense",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
                Image(
                    painter = painterResource(id = R.drawable.dots_menu),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
        }
            DataForm(
                modifier = Modifier.fillMaxWidth()
                    .constrainAs(card) {
                        top.linkTo(nameRow.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        } // -> Constraint Layout
    } // -> Surface
} // -> Add Expense

@Composable
fun DataForm(modifier: Modifier) {
    val name = remember {
        mutableStateOf("")
    }
    val amount = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf(0L)
    }
    val dateDialogVisibility = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
           // .height(200.dp)
    ) {

        ExpenseTextView(text = "Name", fontSize = 14.sp)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value = it
            }, modifier= Modifier.fillMaxWidth())
       ExpenseTextView(text = "Amount", fontSize = 14.sp)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = amount.value,
            onValueChange = {
                name.value = it
            }, modifier = Modifier.fillMaxWidth())

        //date
        ExpenseTextView(text = "Date", fontSize = 14.sp)
        Spacer(modifier = Modifier.size(8.dp))
        OutlinedTextField(
            value = date.value.toString(),
             modifier = Modifier
                .fillMaxWidth()
                .clickable { dateDialogVisibility.value = true },
            enabled = false
        )

        // dropdown

        //type

        Button(
            onClick = { },
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .fillMaxWidth()
        ) {
            ExpenseTextView(
                text = "Add Expense",
                fontSize = 14.sp,
                color = Color.White
            )
        }
        if(dateDialogVisibility.value) {
            ExpenseDatePickerDialog(onDateSelected = {date.value = it
                                                     dateDialogVisibility = false
                                                     }, onDismiss = {
                                                         dateDialogVisibility.value =false
            })
        }
    } // -> Column
} // DatForm Function

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseDatePickerDialog(
    onDateSelected: (date: Long) -> Unit, // A call back function
    onDismiss: () -> Unit  // To handle dismiss of dialog
) {
    val datePickerState  =  rememberDatePickerState() // This creates and remembers the state for the date picker
    val selectedDate = datePickerState.selectedDateMillis  ?: 0L // it retrieves the selected date , if no date is selected 0L which means default or invalid date
    DatePickerDialog(onDismissRequest = {onDismiss()}, // displays a dialog with date picker
        confirmButton = { // This button confirms the selected date
            TextButton(onClick = { onDateSelected(selectedDate) }) {
                Text("Confirm")
            }
        },
        dismissButton = { // This button cancels the selection
            TextButton(onClick = { onDateSelected(selectedDate) }) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState) // it is the actual date picker component
        }
}

@Composable
@Preview(showBackground = true)
fun PreviewAddExpense() {
    AddExpense()
}