package info.skyblond.typecho.mate.telegram

import info.skyblond.typecho.mate.Comment
import info.skyblond.typecho.mate.Config
import info.skyblond.typecho.mate.PostUtils
import info.skyblond.typecho.mate.cutoff
import info.skyblond.typecho.mate.escapeMarkdownV2
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger("Telegram")

fun noticeTelegram(comment: Comment) {
    logger.info { "Sending Telegram for comment ${comment.commentId}" }
    try {
        val messageText = """
            |有人在您的博客发表了评论
            |
            |${comment.commentAuthor.escapeMarkdownV2()} 在《${comment.contentTitle.escapeMarkdownV2()}》下评论：
            |
            |>${comment.commentText.cutoff(3500).escapeMarkdownV2().lines().joinToString("\n>")}
            |
            |状态： ${comment.commentStatus.escapeMarkdownV2()}
            |评论IP： ${comment.commentAuthorIP.escapeMarkdownV2()}
            |评论Email： ${comment.commentAuthorMail.escapeMarkdownV2()}
            |
            |[查看评论](${comment.commentPermalink.escapeMarkdownV2()})
        """.trimMargin()

        Config.telegramUserIds.forEach { chatId ->
            try {
                PostUtils.doPost(
                    "https://api.telegram.org/bot${Config.telegramBotToken}/sendMessage",
                    mapOf(
                        "chat_id" to chatId,
                        "text" to messageText,
                        "parse_mode" to "MarkdownV2"
                    )
                )
                logger.info { "Sent Telegram message to chat $chatId for comment ${comment.commentId}" }
            } catch (t: Throwable) {
                logger.error(t) { "Error when sending Telegram message to chat $chatId for comment ${comment.commentId}" }
            }
        }
    } catch (t: Throwable) {
        logger.error(t) { "Error when preparing Telegram message for comment ${comment.commentId}" }
    }
    logger.info { "Finish sending Telegram for comment ${comment.commentId}" }
}
