
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import theme.AppTheme
import theme.getJetbrainsMonoFamily
import ui.MainScreen
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
                        OutlinedTextField(
                            value = uiState.tempFileName,
                            onValueChange = { viewModel.onSetFileName(it) },
                            textStyle = TextStyle(
                                fontFamily = getJetbrainsMonoFamily()
                            ),
                            shape = MaterialTheme.shapes.largeIncreased,
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
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
