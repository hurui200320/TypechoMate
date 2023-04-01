package info.skyblond.typecho.mate

import java.io.File
import java.util.*

object Config {
    @Volatile
    var dataTimeFormat: String = "yyyy-MM-dd HH:mm:ss z"
        private set

    @Volatile
    var timezone: String = "UTC+8"
        private set


    @Volatile
    var smtpSSLPort: Int = 465
        private set

    @Volatile
    var smtpServer: String = "smtp.example.com"
        private set

    @Volatile
    var smtpUsername: String = "something@example.info"
        private set

    @Volatile
    var smtpPassword: String = "your-password"
        private set

    @Volatile
    var smtpFromAddress: String = "something@example.info"
        private set

    @Volatile
    var smtpFromNickname: String = "your-blog-name"
        private set

    @Volatile
    var ownerMailAddress: String = "something@example.info"
        private set


    @Volatile
    var iftttEventName: String = "your-event-name"
        private set

    @Volatile
    var iftttApiKey: String = "your-api-key"
        private set


    @Volatile
    var pushoverUserKey: String = "your-user-key"
        private set

    @Volatile
    var pushoverApiToken: String = "your-api-token"
        private set

    fun load(file: File) {
        val p = Properties()
        if (file.exists()) {
            file.reader().use { p.load(it) }
            p.getProperty("dataTimeFormat")?.let { dataTimeFormat = it }
            p.getProperty("timezone")?.let { timezone = it }
            p.getProperty("smtpSSLPort")?.toIntOrNull()?.let { smtpSSLPort = it }
            p.getProperty("smtpServer")?.let { smtpServer = it }
            p.getProperty("smtpUsername")?.let { smtpUsername = it }
            p.getProperty("smtpPassword")?.let { smtpPassword = it }
            p.getProperty("smtpFromAddress")?.let { smtpFromAddress = it }
            p.getProperty("smtpFromNickname")?.let { smtpFromNickname = it }
            p.getProperty("ownerMailAddress")?.let { ownerMailAddress = it }
            p.getProperty("iftttEventName")?.let { iftttEventName = it }
            p.getProperty("iftttApiKey")?.let { iftttApiKey = it }
            p.getProperty("pushoverUserKey")?.let { pushoverUserKey = it }
            p.getProperty("pushoverApiToken")?.let { pushoverApiToken = it }
        } else {
            p.setProperty("dataTimeFormat", dataTimeFormat)
            p.setProperty("timezone", timezone)
            p.setProperty("smtpSSLPort", smtpSSLPort.toString())
            p.setProperty("smtpServer", smtpServer)
            p.setProperty("smtpUsername", smtpUsername)
            p.setProperty("smtpPassword", smtpPassword)
            p.setProperty("smtpFromAddress", smtpFromAddress)
            p.setProperty("smtpFromNickname", smtpFromNickname)
            p.setProperty("ownerMailAddress", ownerMailAddress)
            p.setProperty("iftttEventName", iftttEventName)
            p.setProperty("iftttApiKey", iftttApiKey)
            p.setProperty("pushoverUserKey", pushoverUserKey)
            p.setProperty("pushoverApiToken", pushoverApiToken)
            file.writer().use { p.store(it, "TypechoMate config") }
            error("No config file found. Generated one.")
        }
    }
}
