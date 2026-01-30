package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import viewmodel.MainViewModel


@Composable
fun MainScreen(
    uiState: MainViewModel.UiState,
    viewModel: MainViewModel
) {
    Box{
        TextField(
            value = uiState.currentUiContent,
            onValueChange = { viewModel.onContentChanged(it) },
            modifier = Modifier
                .fillMaxSize(),
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }

}