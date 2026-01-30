package ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mytexteditor.resources.Res
import mytexteditor.resources.edit
import org.jetbrains.compose.resources.painterResource
import theme.getJetbrainsMonoFamily
import viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun EditNameComponent(
    uiState: MainViewModel.UiState,
    viewModel: MainViewModel
){
    AnimatedContent(
        targetState = uiState.isEditingFileName,
        transitionSpec = { scaleIn(animationSpec = tween()) togetherWith scaleOut(animationSpec = tween()) }
    ){ isEditing ->
        if(isEditing){
            OutlinedTextField(
                value = uiState.tempFileName,
                onValueChange = { viewModel.onSetFileName(it) },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = getJetbrainsMonoFamily()
                ),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.exitEditName() }
                    ){
                        Icon(
                            painter = painterResource(Res.drawable.edit),
                            contentDescription = "edit file name",
                            modifier = Modifier
                                .size(24.dp)
                                .pointerHoverIcon(PointerIcon.Hand)
                        )
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.largeIncreased,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }else{
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = uiState.tempFileName.text,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = getJetbrainsMonoFamily(),
                        fontSize = 22.sp
                    )
                )

                IconButton(
                    onClick = { viewModel.enterEditName() }
                ){
                    Icon(
                        painter = painterResource(Res.drawable.edit),
                        contentDescription = "stop editing file name",
                        modifier = Modifier
                            .size(24.dp)
                            .pointerHoverIcon(PointerIcon.Hand)
                    )
                }
            }
        }

    }

}