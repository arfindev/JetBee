package com.example.jetbee.presentaion.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetbee.navigation.BottomNavItem
import com.example.jetbee.navigation.BottomNavigationBar
import com.example.jetbee.navigation.Screens
import com.example.jetbee.presentaion.common.UserViewModel
import com.example.jetbee.presentaion.detail_screen.DetailViewModel
import com.example.jetbee.presentaion.home_screen.components.SearchBox
import com.example.jetbee.presentaion.home_screen.components.TopBar
import com.example.jetbee.presentaion.menu_screen.DrawerBody
import com.example.jetbee.presentaion.menu_screen.DrawerHeader
import com.example.jetbee.presentaion.menu_screen.MenuItem
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    navController: NavHostController,
    detailViewModel: DetailViewModel,
) {
    val state = viewModel.getAllCoffee.collectAsState()


    val state2 = userViewModel.userState.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Scaffold(scaffoldState = scaffoldState, backgroundColor = Color.Black,
        topBar = {
            TopBar(onNavigationIconClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            })

        }, drawerGesturesEnabled = scaffoldState.drawerState.isOpen, drawerContent = {
            DrawerHeader()
            DrawerBody(items = listOf(
                MenuItem(
                    direction = "Home",
                    title = "Home",
                    contentDescription = "Go to home",
                    icon = Icons.Default.Home
                ),
                MenuItem(
                    direction = "Home",
                    title = "Home",
                    contentDescription = "Go to home",
                    icon = Icons.Default.Home
                ),
                MenuItem(
                    direction = "Home",
                    title = "Home",
                    contentDescription = "Go to home",
                    icon = Icons.Default.Home
                ),
                MenuItem(
                    direction = "Home",
                    title = "Home",
                    contentDescription = "Go to home",
                    icon = Icons.Default.Home
                ),
                MenuItem(
                    direction = "Home",
                    title = "Home",
                    contentDescription = "Go to home",
                    icon = Icons.Default.Home
                ),
                MenuItem(
                    direction = "Home",
                    title = "Home",
                    contentDescription = "Go to home",
                    icon = Icons.Default.Home
                ),
            ), onItemClick = {
                println("CLick on ${it.title}")
            })
        }, bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        icon = com.example.jetbee.R.drawable.home,
                        route = Screens.HomeScreen.route
                    ),
                    BottomNavItem(
                        name = "Cart",
                        icon = com.example.jetbee.R.drawable.cart,
                        route = Screens.CartScreen.route,
                        badgeCount = state2.cartProducts.size
                    ),
                    BottomNavItem(
                        name = "Favourite",
                        icon = com.example.jetbee.R.drawable.heart,
                        route = Screens.Favourite.route
                    ),
                    BottomNavItem(
                        name = "Profile",
                        icon = com.example.jetbee.R.drawable.profile,
                        route = Screens.Profile.route
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }, content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 18.dp, end = 18.dp, top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        SearchBox()
                        Spacer(modifier = Modifier.height(26.dp))
                        if (state.value.coffeeSuccess?.isNotEmpty() == true) {
                            AllCoffeeContent(
                                product = state.value.coffeeSuccess!!,
                                navController = navController,
                                detailViewModel = detailViewModel
                            )
                        }
                    }
                }
            }
        })
}

