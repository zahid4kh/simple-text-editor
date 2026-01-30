package viewmodel
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

class MainViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val homeDir = System.getProperty("user.home")
    private val downloadsDir = "$homeDir/Downloads"
    private val documentsDir = "$homeDir/Documents"

    fun onContentChanged(text: TextFieldValue){
        _uiState.update {
            it.copy(currentUiContent = text)
        }
    }

    fun onSetFileName(name: TextFieldValue){
        _uiState.update {
            it.copy(tempFileName = name)
        }
    }

    fun onSetFileExtension(extension: String){
        _uiState.update {
            it.copy(tempFileExtension = extension)
        }
    }

    fun onSaveNewFile(){
        viewModelScope.launch(Dispatchers.IO) {
            val file = FileToSave(
                fileName = _uiState.value.tempFileName.text,
                fileExtension = _uiState.value.tempFileExtension,
                fileContent = _uiState.value.currentUiContent.text,
                fileLocation = File(documentsDir, "${_uiState.value.tempFileName.text}.${_uiState.value.tempFileExtension}")
            )

            try {
                file.fileLocation?.writeText(
                    text = file.fileContent
                )
                showUiMessage("File saved: ${file.fileName}")
            }catch (e: IOException){
                showUiMessage("Could not save file: ${e.localizedMessage}")
            }
        }
    }

    private suspend fun showUiMessage(message: String){
        withContext(Dispatchers.Main){
            _uiState.update { it.copy(uiMessage = message) }
            delay(2000)
            _uiState.update { it.copy(uiMessage = null) }
        }
    }

    fun enterEditName(){
        _uiState.update { it.copy(isEditingFileName = true) }
    }

    fun exitEditName(){
        _uiState.update { it.copy(isEditingFileName = false) }
    }

    fun toggleDarkMode(){
        _uiState.update {
            it.copy(darkMode = !it.darkMode)
        }
    }

    data class UiState(
        val darkMode: Boolean = false,
        val fileToSave: FileToSave? = null,
        val currentUiContent: TextFieldValue = TextFieldValue(""),
        val tempFileName: TextFieldValue = TextFieldValue("NewFile"),
        val tempFileExtension: String? = "md",
        val uiMessage: String? = null,
        val isEditingFileName: Boolean = false
    )

    data class FileToSave(
        val fileName: String = "",
        val fileExtension: String? = "",
        val fileContent: String = "",
        val fileLocation: File? = null
    )
}