package vn.miraway.tutone.utils

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedInputStream
import java.io.FileOutputStream
import java.net.URL


class FileDownloader(val ous: FileOutputStream) : AsyncTask<String, Integer, String>() {
    val tag = "[file_downloader]"
    override fun doInBackground(vararg params: String?): String? {
        var count: Int
        try {
            val url = URL(params[0])
            val conexion = url.openConnection()
            conexion.connect()
            // this will be useful so that you can show a tipical 0-100% progress bar
            val lenghtOfFile = conexion.getContentLength()

            val input = BufferedInputStream(url.openStream())

            val data = ByteArray(1024)
            var total: Long = 0
            var count = input.read(data)
            while (count != -1) {
                total += count.toLong()
                ous.write(data, 0, count)
                count = input.read(data)
            }

            ous.flush()
            ous.close()
            input.close()
        } catch (e: Exception) {
            Log.e(tag, e.toString())
        }
        return null
    }

}