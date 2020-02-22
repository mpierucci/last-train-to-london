package com.mpierucci.lasttraintolondon.ristretto

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object FileReaderUtil {

    fun loadTestFile(caller: Class<*>, assetPath: String): String {
        try {
            val inputStream = caller.classLoader?.getResourceAsStream(assetPath)
            checkNotNull(inputStream)
            return inputStreamToString(
                inputStream,
                "UTF-8"
            )
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    inline fun <reified T> loadModelFromTestFile(caller: Class<*>, fileName: String): T {
        val inputStream = caller.classLoader?.getResourceAsStream(fileName)
        checkNotNull(inputStream)
        val inputStreamReader = InputStreamReader(inputStream)

        return try {
            GsonBuilder().create().fromJson<T>(inputStreamReader, object : TypeToken<T>() {}.type)
        } finally {
            inputStream.close()
            inputStreamReader.close()
        }
    }

    private fun inputStreamToString(inputStream: InputStream, charsetName: String): String {
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, charsetName)
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}