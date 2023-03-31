import info.skyblond.typecho.mate.Comment
import info.skyblond.typecho.mate.Comment.Companion.extractComment
import info.skyblond.typecho.mate.Config
import info.skyblond.typecho.mate.ifttt.noticeIFTTT
import info.skyblond.typecho.mate.mail.sendEmail
import info.skyblond.typecho.mate.pushover.noticePushover
import io.javalin.Javalin
import io.javalin.http.HttpStatus
import mu.KotlinLogging
import java.io.File
import java.util.concurrent.CompletableFuture

private val logger = KotlinLogging.logger("Application")

fun main(args: Array<String>) {
    require(args.size == 1) {"Usage: TypechoMate <path_to_config_file>"}
    Config.load(File(args[0]))
    Javalin.create()
        .post("/comment") { ctx ->
            try {
                val currentComment = ctx.extractComment("current_")
                val parentComment = if (currentComment.hasParent) ctx.extractComment("parent_") else null
                handleComment(currentComment, parentComment)
                // return 204
                ctx.status(HttpStatus.NO_CONTENT)
            } catch (t: Throwable) {
                logger.error(t) { "Error when handling request" }
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        .start(8080)
}

private fun handleComment(currentComment: Comment, parentComment: Comment?) {
    CompletableFuture.runAsync { sendEmail(currentComment, parentComment) }
    CompletableFuture.runAsync { noticeIFTTT(currentComment) }
    CompletableFuture.runAsync { noticePushover(currentComment) }
}
