package info.skyblond.typecho.mate

import java.util.*

fun String.cutoff(len: Int, extra: String = "..."): String =
    if (this.length <= len) this
    else this.take(len - extra.length) + extra

fun String.encodeForMail(): String =
    "=?utf-8?b?${Base64.getEncoder().encodeToString(this.encodeToByteArray())}?="
