import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import email.EmailService.sendEmail
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var recipients by remember { mutableStateOf("") }
        var subject by remember { mutableStateOf("") }
        var body by remember { mutableStateOf("") }
        var attachment by remember { mutableStateOf("") }
        var isLoading by remember { mutableStateOf(false) }
        var resultMessage by remember { mutableStateOf<String?>(null) }
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()

        LaunchedEffect(resultMessage) {
            resultMessage?.let {
                scaffoldState.snackbarHostState.showSnackbar(it)
                resultMessage = null
            }
        }

        Scaffold(
            scaffoldState = scaffoldState
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        enabled = !isLoading,
                        value = recipients,
                        onValueChange = { recipients = it },
                        label = { Text("Recipients") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        enabled = !isLoading,
                        value = subject,
                        onValueChange = { subject = it },
                        label = { Text("Subject") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        enabled = !isLoading,
                        value = body,
                        onValueChange = { body = it },
                        label = { Text("Body") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        enabled = !isLoading,
                        value = attachment,
                        onValueChange = { attachment = it },
                        label = { Text("Attachment (file path)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        enabled = !isLoading,
                        onClick = {
                            if (!isLoading) {
                                coroutineScope.launch {
                                    isLoading = true
                                    try {
                                        sendEmail(recipients, subject, body, attachment)
                                        resultMessage = "Email has been successfully sent!"
                                    } catch (e: Exception) {
                                        resultMessage = "Error: ${e.message}"
                                    } finally {
                                        isLoading = false
                                    }
                                }
                            }
                        }
                    ) {
                        Text("Send email")
                    }
                }
            }
        }


        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}