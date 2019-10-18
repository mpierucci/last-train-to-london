package properties

import java.io.File
import java.io.FileInputStream
import java.util.*

fun loadLocalProperties(rootDir:String): Properties {
    return Properties().apply {
        load(FileInputStream(File("$rootDir/local.properties")))
    }
}