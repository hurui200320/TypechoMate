package info.skyblond.typecho.mate

import io.javalin.http.Context
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class Comment(
    val commentId: Int,
    val commentCreatedTimestamp: Long,
    val commentAuthor: String,
    val commentAuthorId: Int,
    val commentAuthorMail: String,
    val commentAuthorIP: String,
    val commentText: String,
    val commentPermalink: String,
    val commentStatus: String,
    val commentParentId: Int,
    val contentId: Int,
    val contentOwnerId: Int,
    val contentTitle: String,
) {
    val hasParent: Boolean = commentParentId != 0

    val time: String = DateTimeFormatter.ofPattern(Config.dataTimeFormat)
        .format(
            ZonedDateTime.ofInstant(
                Instant.ofEpochSecond(this.commentCreatedTimestamp),
                ZoneId.of(Config.timezone)
            )
        )


    companion object {
        private const val CONTENT_ID = "cid"
        private const val COMMENT_ID = "coid"
        private const val COMMENT_CREATED_TIMESTAMP = "created"
        private const val COMMENT_AUTHOR = "author"
        private const val COMMENT_AUTHOR_ID = "authorId"
        private const val CONTENT_OWNER_ID = "ownerId"
        private const val COMMENT_AUTHOR_MAIL = "mail"
        private const val COMMENT_AUTHOR_IP = "ip"
        private const val CONTENT_TITLE = "title"
        private const val COMMENT_TEXT = "text"
        private const val COMMENT_PERMALINK = "permalink"
        private const val COMMENT_STATUS = "status"
        private const val COMMENT_PARENT_ID = "parent"
        fun Context.extractComment(prefix: String): Comment = Comment(
            commentId = this.formParam(prefix + COMMENT_ID)?.toIntOrNull() ?: 0,
            commentCreatedTimestamp = this.formParam(prefix + COMMENT_CREATED_TIMESTAMP)?.toLongOrNull() ?: 0,
            commentAuthor = this.formParam(prefix + COMMENT_AUTHOR) ?: "",
            commentAuthorId = this.formParam(prefix + COMMENT_AUTHOR_ID)?.toIntOrNull() ?: 0,
            commentAuthorMail = this.formParam(prefix + COMMENT_AUTHOR_MAIL) ?: "",
            commentAuthorIP = this.formParam(prefix + COMMENT_AUTHOR_IP) ?: "",
            commentText = this.formParam(prefix + COMMENT_TEXT) ?: "",
            commentPermalink = this.formParam(prefix + COMMENT_PERMALINK) ?: "",
            commentStatus = this.formParam(prefix + COMMENT_STATUS) ?: "",
            commentParentId = this.formParam(prefix + COMMENT_PARENT_ID)?.toIntOrNull() ?: 0,
            contentId = this.formParam(prefix + CONTENT_ID)?.toIntOrNull() ?: 0,
            contentOwnerId = this.formParam(prefix + CONTENT_OWNER_ID)?.toIntOrNull() ?: 0,
            contentTitle = this.formParam(prefix + CONTENT_TITLE) ?: ""
        )
    }
}
