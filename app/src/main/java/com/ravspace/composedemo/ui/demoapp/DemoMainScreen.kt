package com.ravspace.composedemo.ui.demoapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ravspace.composedemo.MainActivity


@Composable
fun DemoMainScreen() {
    TextFieldsDemo()
    StyleTextFiledDemo()

}

@Composable
fun StyleTextFiledDemo() {

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldsDemo() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        var newTextValue by remember { mutableStateOf("") }
        Column(verticalArrangement = Arrangement.spacedBy(25.dp)) {

            TextField(value = newTextValue, onValueChange = {
                newTextValue = it
            }, label = {
                Text(text = "Enter your name")
            }, singleLine = true, modifier = Modifier.width(300.dp),
                //    visualTransformation = PasswordVisualTransformation()
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = " "
                    )
                }, trailingIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "icon click", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.BrokenImage, contentDescription = " "
                        )
                    }
                })



            OutlinedTextField(value = newTextValue, onValueChange = {
                newTextValue = it
            }, label = {
                Text(text = "Enter your name")
            }, singleLine = true, modifier = Modifier.width(300.dp),
                //    visualTransformation = PasswordVisualTransformation()
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person, contentDescription = " "
                    )
                }, trailingIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "icon click", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.BrokenImage, contentDescription = " "
                        )
                    }
                },

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Send
                ),

                keyboardActions = KeyboardActions(
                    onSend = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                ))
        }
    }

}

@Preview
@Composable
fun DemoMainScreenPreview() {
    DemoMainScreen()

}
fun demo(){
    val a:String

}