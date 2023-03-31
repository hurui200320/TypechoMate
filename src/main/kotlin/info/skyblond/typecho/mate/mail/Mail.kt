package info.skyblond.typecho.mate.mail

import info.skyblond.typecho.mate.Comment
import info.skyblond.typecho.mate.Config
import jakarta.mail.*
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeBodyPart
import jakarta.mail.internet.MimeMessage
import jakarta.mail.internet.MimeMultipart
import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger("Email")

private fun sendOneEmail(
    toAddress: String, subject: String, content: String
) {
    val prop = Properties()
    prop["mail.smtp.auth"] = true
    prop["mail.smtp.ssl.enable"] = "true"
    prop["mail.smtp.host"] = Config.smtpServer
    prop["mail.smtp.port"] = Config.smtpSSLPort.toString()
    prop["mail.smtp.ssl.trust"] = Config.smtpServer

    val session = Session.getInstance(prop, object : Authenticator() {
        override fun getPasswordAuthentication() =
            PasswordAuthentication(Config.smtpUsername, Config.smtpPassword)
    })

    val message: Message = MimeMessage(session)
    message.setFrom(InternetAddress(Config.smtpFromAddress))
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress))

    message.subject = subject
    val mimeBodyPart = MimeBodyPart()
    mimeBodyPart.setContent(content, "text/html; charset=utf-8")

    val multipart: Multipart = MimeMultipart()
    multipart.addBodyPart(mimeBodyPart)

    message.setContent(multipart)
    Transport.send(message)
}

fun sendEmail(comment: Comment, parent: Comment?) {
    // in all cases, notify owner first
    logger.info { "Sending mail to owner. Comment id: ${comment.commentId}" }
    try {
        sendOneEmail(
            Config.ownerMailAddress,
            "[${comment.contentTitle}] 一文有新的评论",
            generateOwner(comment)
        )
    } catch (t: Throwable) {
        logger.error(t) { "Error when sending mail to owner. Comment id: ${comment.commentId}" }
    }
    // then if we have a parent comment, then notify the parent comment author
    parent?.let {
        if (comment.commentAuthorMail == it.commentAuthorMail) {
            logger.info { "Skip comment id ${comment.commentId} because of same author" }
            return@let
        }
        if (it.commentAuthorMail == Config.ownerMailAddress) {
            logger.info { "Skip comment id ${comment.commentId} because the author-to-reply is owner" }
            return@let
        }
        logger.info { "Sending mail to parent author. Comment id: ${comment.commentId}" }
        try {
            sendOneEmail(
                it.commentAuthorMail,
                "您在 [${comment.contentTitle}] 的评论有了回复",
                generateGuest(comment, it)
            )
        } catch (t: Throwable) {
            logger.error(t) { "Error when sending mail to parent author. Comment id: ${comment.commentId}" }
        }
    }
    logger.info { "Finish sending email for comment ${comment.commentId}" }
}
