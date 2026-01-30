
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import theme.AppTheme
import ui.MainScreen
import ui.components.EditNameComponent
import viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App(
    viewModel: MainViewModel,
    uiState: MainViewModel.UiState
) {
    AppTheme(darkTheme = uiState.darkMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        EditNameComponent(
                            uiState = uiState,
                            viewModel = viewModel
                        )
                    }
                )
            }
        ){innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                MainScreen(
                    uiState = uiState,
                    viewModel = viewModel
                )
            }
        }
    }
}
