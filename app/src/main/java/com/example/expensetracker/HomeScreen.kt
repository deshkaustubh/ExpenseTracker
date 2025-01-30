package com.example.expensetracker

import android.icu.text.CaseMap.Title
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.expensetracker.ui.theme.Zinc

//import androidx.constraintlayout.compose.createRefs

@Composable
fun HomeScreen() {
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
            CardIem(modifier = Modifier
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            TransactionList(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(list) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }

            )
        }
    }
}


@Composable
fun CardIem(modifier: Modifier) {
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
            CardRowItem(title = "Income", amount = "$23,000", modifier = Modifier.align(Alignment.CenterStart), image = R.drawable.ic_income,)

            CardRowItem(title = "Expense", amount = "$12,000", modifier = Modifier.align(Alignment.CenterEnd), image = R.drawable.ic_expense)
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
fun TransactionList(modifier: Modifier) {
Column (modifier = modifier.padding(16.dp)) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Recent Transactions", fontSize = 20.sp)
        Text(
            text = "See All",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterEnd),
        )
    }
    TransactionItem(
        title = "Netflix",
        amount = "-$ 200.00",
        icon = R.drawable.ic_netflix,        date = "Today",
        color = Color.Red
    )
    TransactionItem(
        title = "Netflix",
        amount = "-$ 200.00",
        icon = R.drawable.ic_youtube,
        date = "Today",
        color = Color.Red
    )
    TransactionItem(
        title = "Netflix",
        amount = "-$ 200.00",
        icon = R.drawable.ic_paypal,
        date = "Today",
        color = Color.Red
    )
    TransactionItem(
        title = "Netflix",
        amount = "-$ 200.00",
        icon = R.drawable.ic_upwork,
        date = "Today",
        color = Color.Red
    )

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
