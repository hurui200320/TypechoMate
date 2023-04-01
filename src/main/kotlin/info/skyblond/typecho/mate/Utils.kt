package info.skyblond.typecho.mate

fun String.cutoff(len: Int, extra: String = "..."): String =
    if (this.length <= len) this
    else this.take(len - extra.length) + extra
