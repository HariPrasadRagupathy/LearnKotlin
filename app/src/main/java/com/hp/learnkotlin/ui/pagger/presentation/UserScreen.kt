package com.hp.learnkotlin.ui.pagger.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.hp.learnkotlin.ui.pagger.domain.User
import com.hp.learnkotlin.ui.theme.LearnKotlinTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    userViewModel: UserViewModel = hiltViewModel()
) {
    val users: LazyPagingItems<User> = userViewModel.userPagingFlow.collectAsLazyPagingItems()
    val searchText by userViewModel.searchText.collectAsState()
    val isSearching by userViewModel.isSearching.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = users.loadState, block = {
        if (users.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error : ${(users.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    })
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(value = searchText, onValueChange = userViewModel::onSearchTextChange)
        Box(modifier = Modifier.fillMaxSize()) {
            if (users.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(users.itemCount) { userItemIndex ->
                        users[userItemIndex]?.let { UserItem(user = it) }
                    }
                    item {
                        if (users.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }

                }
            }
        }
    }


}


@Composable
fun UserItem(
    user: User,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = "https://ajantabottle.com/public/storage/media/eUJ7jmknY0EjhK6bXa6yNfkz8PkwEqVqqRLpWQfS.jpg",
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(user.userName)
            }
        }
    }
}

@Preview
@Composable
fun UserItemPreview() {
    LearnKotlinTheme {
        UserItem(
            user = User(
                userId = "1",
                userName = "User",
                userEmail = "Email",
                userPhone = "phone",
                userCountryCode = "CountryCode",
                userRole = "Role",
                userStatus = "Status"
            )
        )
    }
}