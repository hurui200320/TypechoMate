package info.skyblond.typecho.mate.ifttt

import info.skyblond.typecho.mate.Comment
import info.skyblond.typecho.mate.Config
import info.skyblond.typecho.mate.PostUtils
import info.skyblond.typecho.mate.cutoff
import mu.KotlinLogging

private val logger = KotlinLogging.logger("IFTTT")
fun noticeIFTTT(comment: Comment) {
    logger.info { "Sending IFTTT for comment ${comment.commentId}" }
    try {
        PostUtils.doPost(
            "https://maker.ifttt.com/trigger/${Config.iftttEventName}/with/key/${Config.iftttApiKey}",
            mapOf(
                "value1" to "有人在您的博客发表了评论",
                "value2" to "${comment.commentAuthor}在《${comment.contentTitle}》下说到：<br><br>" +
                        "${comment.commentText.cutoff(1500)}<br><br>" +
                        "状态：${comment.commentStatus}<br>" +
                        "评论IP：${comment.commentAuthorIP}<br>" +
                        "评论Email：${comment.commentAuthorMail.replace('.', '#')}",
                "value3" to comment.commentPermalink,
            )
        )
    } catch (t: Throwable) {
        logger.error(t) { "Error when sending IFTTT for comment ${comment.commentId}" }
    }
    logger.info { "Finish sending IFTTT for comment ${comment.commentId}" }
}
