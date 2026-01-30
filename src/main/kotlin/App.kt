
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import mytexteditor.resources.Res
import mytexteditor.resources.save
import org.jetbrains.compose.resources.painterResource
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
                    },
                    actions = {
                        uiState.uiMessage?.let { message ->
                            Text(
                                text = message,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        IconButton(
                            onClick = { viewModel.onSaveNewFile() },
                            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
                            enabled = uiState.currentUiContent.text.isNotEmpty()
                        ){
                            Icon(
                                painter = painterResource(Res.drawable.save),
                                contentDescription = "save file",
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
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
