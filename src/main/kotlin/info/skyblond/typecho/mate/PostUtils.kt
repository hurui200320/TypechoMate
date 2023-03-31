package info.skyblond.typecho.mate

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.closeQuietly

object PostUtils {
    private val httpClient = OkHttpClient()

    fun doPost(url: String, form: Map<String, String>) {
        val formBody = FormBody.Builder()
            .apply {
                form.forEach { (k, v) ->
                    add(k, v)
                }
            }
            .build()
        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()
        val call = httpClient.newCall(request)
        call.execute().also { it.closeQuietly() }
    }
}
