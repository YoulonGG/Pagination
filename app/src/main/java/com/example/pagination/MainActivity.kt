package com.example.pagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagination.ui.theme.PaginationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaginationTheme {
                PaginatedList(viewModel = PaginationViewModel())
            }
        }
    }
}

@Composable
fun PaginatedList(viewModel: PaginationViewModel) {
    val items by viewModel.items.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo }
            .collect { layoutInfo ->
                val visibleItems = layoutInfo.visibleItemsInfo
                if (visibleItems.isNotEmpty()) {
                    val lastVisibleItemIndex = visibleItems.lastOrNull()?.index ?: 0
                    viewModel.onScrollToEnd(lastVisibleItemIndex)
                }
            }
    }


    LazyColumn(
        modifier = Modifier.padding(vertical = 20.dp),
        state = listState
    ) {
        items(items) {
            UserProfileCard("Youlong", 21, "Android Developer", "Active")
        }

        item {
            if (isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                )
            }
        }
    }
}



