package com.example.noteapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun AddNote(
    navController: NavHostController,
    noteViewModel: NoteViewModel
) {
    val uiState by noteViewModel.addNoteUiState.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(top = 20.dp)
            ) {

                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.testTag("ic_back_button")
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = ""
                        )
                    }
                    Text(
                        text = "Add Note",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .align(alignment = Alignment.Center),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontSize = 18.sp,
                            lineHeight = 16.sp
                        ),
                        color = Color(0xFF073B3A),
                        letterSpacing = 1.8.sp,
                        fontFamily = FontFamily(Font(R.font.poppins))

                        )
                    IconButton(
                        onClick = {
                            if (noteViewModel.validate()){
                                noteViewModel.addNote()
                                navController.popBackStack()
                            }

                        },
                        modifier = Modifier
                            .testTag("ic_back_button")
                            .align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_check_24),
                            contentDescription = "",
                        )
                    }
                }

            }
        }
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp),
                    value = uiState.title,
                    onValueChange = { noteViewModel.updateTitle(it) },
                    shape = MaterialTheme.shapes.extraSmall,
                    label = {
                        Text(
                            modifier = Modifier.wrapContentHeight(),
                            text = "Title",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                lineHeight = 12.sp,
                                fontWeight = FontWeight.W600
                            ),
                            color = Color(0xFF073B3A),
                            fontFamily = FontFamily(Font(R.font.poppins))
                        )

                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFF073B3A),
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 12.dp, end = 12.dp, top = 6.dp, bottom = 6.dp),
                    value = uiState.content,
                    onValueChange = { noteViewModel.updateContent(it) },
                    shape = MaterialTheme.shapes.extraSmall,
                    label = {
                        Text(
                            modifier = Modifier.wrapContentHeight(),
                            text = "Description",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                lineHeight = 12.sp,
                                fontWeight = FontWeight.W600
                            ),
                            color = Color(0xFF073B3A),
                            fontFamily = FontFamily(Font(R.font.poppins))
                        )

                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFF073B3A),
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                    ),
                )

            }
        }
    }
}