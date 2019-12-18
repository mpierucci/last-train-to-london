package com.mpierucci.lasttraintolondon.lines

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

//TODO https://github.com/mpierucci/last-train-to-london/issues/33
inline fun <reified T> loadModelFromTestFile(caller: Class<*>, fileName: String): T {
    val inputStream = caller.classLoader?.getResourceAsStream(fileName)
    val inputStreamReader = InputStreamReader(inputStream)

    return try {
        GsonBuilder().create().fromJson<T>(inputStreamReader, object : TypeToken<T>() {}.type)
    } finally {
        inputStream?.close()
        inputStreamReader.close()
    }
}