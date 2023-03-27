# GZIBAndroidFont

## Convert Font file to gzib format using python code

``` python
import gzip

# Open the font file for reading
with open('font.ttf', 'rb') as f:
    font_data = f.read()

# Compress the font data with gzip using a higher compression level (9)
compressed_data = gzip.compress(font_data, compresslevel=9)

# Write the compressed data to a new file
with open('font_compressed.ttf.gz', 'wb') as f:
    f.write(compressed_data)

```

## This code will read font file from res/raw folder and uncompressed font data to a temporary file
``` kotlin
package com.faysal.gzibfont

import android.content.Context
import android.graphics.Typeface
import android.widget.Toast
import java.io.File
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

```

## Finally in activity 

``` kotlin
val typeface = loadCompressedFontFromResource(context, R.raw.font_compressed)
textView.typeface = typeface
```

