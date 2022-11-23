package com.example.jetbee.presentaion.cart_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetbee.R
import com.example.jetbee.presentaion.common.UserViewModel
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun CartScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = "My Cart", navController = navController, navIcon = painterResource(
                        id = R.drawable.back_icon
                    )
                )
            }, content = { padding ->
                Card(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize(), shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp)
                                .weight(5f)
                        ) {
                            items(userViewModel.userState.value.cartProducts) { product ->
                                CartBodyItem(cartProduct = product)
                            }
                        }
                        //Spacer(modifier = Modifier.height(10.dp))
                        CartBillItem(modifier = Modifier)
                    }
                }

            }
        )

    }


}