package com.example.noteapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    noteViewModel: NoteViewModel
) {
    val uiState by noteViewModel.notes.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier
            .padding(vertical = 30.dp),
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    noteViewModel.clear()
                    navController.navigate("AddNote")
                },
                containerColor = Color(0xFF073B3A),
                contentColor = Color.White,
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.background,
                //modifier = Modifier.padding(top = 20.dp)
            ) {

                Box(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = "Notes",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .align(alignment = Alignment.Center),
                        textAlign = TextAlign.Start,
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
                            noteViewModel.updateSort()
                        },
                        modifier = Modifier
                            .testTag("ic_back_button")
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_sort_by_alpha_24),
                            contentDescription = "",
                            tint = Color(0xFF073B3A)
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
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Box(
                    modifier =
                    Modifier
                        //.background(color = secondaryColor)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {

                        LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxHeight()
                            .weight(1f)) {
                            itemsIndexed(uiState.notes) { index, item ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(2.dp)
                                        .padding(vertical = 2.dp)
                                        .border(
                                            2.dp,
                                            Color(0xFF073B3A),
                                            shape = MaterialTheme.shapes.medium
                                        )

                                        .shadow(
                                            elevation = 15.dp,
                                            shape = MaterialTheme.shapes.medium
                                        ),
                                    colors = CardDefaults.cardColors(Color.White),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                                    onClick = {

                                    }
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(2.dp)
                                    ) {
                                        Row {
                                            Text(
                                                text = item.title,
                                                style = MaterialTheme.typography.bodySmall,
                                                modifier = Modifier.padding(start = 12.dp, end  = 8.dp, top = 8.dp, bottom = 4.dp).align(Alignment.CenterVertically),
                                                color = Color(0xFF073B3A),
                                                fontFamily = FontFamily(Font(R.font.poppins))
                                            )
                                            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                                IconButton(
                                                    onClick = {
                                                        noteViewModel.updateSelectedId(item)
                                                        navController.navigate("AddNote")
                                                    },
                                                    modifier = Modifier
                                                        .testTag("ic_back_button")
                                                        .align(Alignment.CenterVertically)
                                                        .width(20.dp)
                                                        .height(20.dp)
                                                ) {
                                                    Icon(
                                                        painter = painterResource(R.drawable.baseline_edit_24),
                                                        contentDescription = "",
                                                        tint = Color(0xFF073B3A)
                                                    )
                                                }
                                                IconButton(
                                                    onClick = {
                                                        noteViewModel.delete(item)
                                                    },
                                                    modifier = Modifier
                                                        .testTag("ic_back_button")
                                                        .align(Alignment.CenterVertically)
                                                ) {
                                                    Icon(
                                                        painter = painterResource(R.drawable.baseline_delete_24),
                                                        contentDescription = "",
                                                        tint = Color(0xFF073B3A)
                                                    )
                                                }
                                            }
                                        }

                                        Text(
                                            text = item.content,
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier
                                                .padding(start = 12.dp, end  = 12.dp, top = 4.dp, bottom = 8.dp),
                                            color = Color(0xFF073B3A),
                                            fontFamily = FontFamily(Font(R.font.poppins))
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}