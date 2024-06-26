package email

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.*
import javax.mail.internet.*

object EmailService {
    suspend fun sendEmail(recipients: String, subject: String, body: String, attachment: String) {
        withContext(Dispatchers.IO) {
                val props = Properties().apply {
                    put("mail.smtp.auth", "true")
                    put("mail.smtp.starttls.enable", "true")
                    put("mail.smtp.host", System.getenv("SMTP_HOST"))
                    put("mail.smtp.port", System.getenv("SMTP_PORT"))
                    put("mail.smtp.socketFactory.port", System.getenv("SMTP_PORT"))
                    put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                }

                val session = Session.getInstance(props, object : Authenticator() {
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(
                            System.getenv("EMAIL_USER"),
                            System.getenv("EMAIL_PASSWORD")
                        )
                    }
                })

                val message = MimeMessage(session).apply {
                    setFrom(InternetAddress(System.getenv("EMAIL_USER")))
                    setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients))
                    setSubject(subject)
                    setText(body)

                    if (attachment.isNotEmpty()) {
                        val mimeBodyPart = MimeBodyPart()
                        mimeBodyPart.setText(body)

                        val attachmentBodyPart = MimeBodyPart().apply {
                            dataHandler = DataHandler(FileDataSource(attachment))
                            fileName = attachment.split("/").last()
                        }

                        val multipart = MimeMultipart().apply {
                            addBodyPart(mimeBodyPart)
                            addBodyPart(attachmentBodyPart)
                        }

                        setContent(multipart)
                    } else {
                        setText(body)
                    }
                }

                Transport.send(message)
                println("Email sent successfully!")
        }
    }
}