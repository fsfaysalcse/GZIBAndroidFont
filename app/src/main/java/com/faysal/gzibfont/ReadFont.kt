package com.faysal.gzibfont

import android.content.Context
import android.graphics.Typeface
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.zip.GZIPInputStream

fun loadCompressedFontFromResource(context: Context, resourceId: Int): Typeface? {
    try {
        // Open the compressed font file from the app's resources
        val compressedFontData = context.resources.openRawResource(resourceId).readBytes()

        // Decompress the font data from the gzip file
        val gzipInputStream = GZIPInputStream(compressedFontData.inputStream())
        val fontData = gzipInputStream.readBytes()
        gzipInputStream.close()

        // Write the uncompressed font data to a temporary file
        val tempFontFile = File.createTempFile("font_compressed", "ttf", context.cacheDir)
        tempFontFile.outputStream().use { output ->
            output.write(fontData)
        }

        // Load the font from the temporary file into a Typeface object
        return Typeface.createFromFile(tempFontFile)
    } catch (e: Exception) {
        Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
        return Typeface.DEFAULT
    }
}


