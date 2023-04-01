package info.skyblond.typecho.mate.pushover

import info.skyblond.typecho.mate.Comment
import info.skyblond.typecho.mate.Config
import info.skyblond.typecho.mate.PostUtils
import info.skyblond.typecho.mate.cutoff
import mu.KotlinLogging

private val logger = KotlinLogging.logger("Pushover")
fun noticePushover(comment: Comment) {
    logger.info { "Sending Pushover for comment ${comment.commentId}" }
    try {
        PostUtils.doPost(
            "https://api.pushover.net/1/messages.json",
            mapOf(
                "token" to Config.pushoverApiToken,
                "user" to Config.pushoverUserKey,
                "title" to "有人在您的博客发表了评论",
                "message" to "${comment.commentAuthor}在《${comment.contentTitle}》下说到：\n\n" +
                        "${comment.commentText.cutoff(700)}\n\n" +
                        "状态：${comment.commentStatus}\n" +
                        "评论IP：${comment.commentAuthorIP}\n" +
                        "评论Email：${comment.commentAuthorMail.replace('.', '#')}",
                "url" to comment.commentPermalink,
                "url_title" to comment.contentTitle
            )
        )
    } catch (t: Throwable) {
        logger.error(t) { "Error when sending Pushover for comment ${comment.commentId}" }
    }
    logger.info { "Finish sending Pushover for comment ${comment.commentId}" }
}
