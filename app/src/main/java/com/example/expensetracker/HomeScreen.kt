package com.example.expensetracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.expensetracker.data.model.ExpenseEntity
import com.example.expensetracker.ui.theme.Zinc
import com.example.expensetracker.viewmodel.HomeViewModel
import com.example.expensetracker.viewmodel.HomeViewModelFactory

//import androidx.constraintlayout.compose.createRefs

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = HomeViewModelFactory(LocalContext.current).create(HomeViewModel::class.java)
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
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ){
                Column {
                    Text(text = "Hello,", fontSize = 16.sp, color = Color.White)
                    Text(text = "Kaustubh Deshpande", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            val state = viewModel.expenses.collectAsState(initial = emptyList())
            val balance = viewModel.getBalance(state.value)
            val totalExpense = viewModel.getTotalExpense(state.value)
            val totalIncome = viewModel.getTotalIncome(state.value)

            CardIem(modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, balance, totalExpense, totalIncome
            )
            TransactionList(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(list) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }, list = state.value , viewModel

            )
        }
    }
}


@Composable
fun CardIem(modifier: Modifier, balance: String, totalExpense: String, totalIncome: String) {
    Column ( modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Zinc)
        .padding(16.dp)
    ){
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Column (modifier = Modifier.align(Alignment.CenterStart)) {
                Text(text = "Total Balance", fontSize = 16.sp, color = Color.White)
                Text(text = "$ 1000", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Image(
                painter = painterResource(id = R.drawable.dots_menu),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
       // Spacer(modifier = Modifier.fillMaxWidth().weight(1f))

        Box (modifier = Modifier
            .fillMaxWidth()
            .weight(1f)){
            CardRowItem(title = "Income", amount = totalIncome, modifier = Modifier.align(Alignment.CenterStart), image = R.drawable.ic_income,)

            CardRowItem(title = "Expense", amount = totalExpense, modifier = Modifier.align(Alignment.CenterEnd), image = R.drawable.ic_expense)
        }
    }
}

@Composable
fun CardRowItem(modifier: Modifier, title: String, amount: String, image: Int) {
    Column(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = image),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = title, fontSize = 16.sp, color = Color.White)
        }
        Text(text = amount, fontSize = 20.sp, color = Color.White)
    }
}

@Composable
fun TransactionList(modifier: Modifier, list: List<ExpenseEntity>, viewModel: HomeViewModel) {
LazyColumn (modifier = modifier.padding(16.dp)) {
    item {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Recent Transactions", fontSize = 20.sp)
            Text(
                text = "See All",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        }
    }
    items(list) { item->
        TransactionItem(
            title = item.title,
            amount = item.amount.toString(),
            icon = viewModel.getItemIcon(item),
            date = item.date.toString(),
            color = if (item.type == "Income") Color.Green else Color.Red,
        )
    }
}
}

@Composable
fun TransactionItem(title: String, amount: String, icon: Int, date: String, color: Color) {
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row {
            Image(painter = painterResource(id = icon), contentDescription = null,
                modifier = Modifier.size(50.dp)
                )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Text(text = date, fontSize = 12.sp)
            }
        }
        Text(text = amount, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterEnd), color = color, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}
