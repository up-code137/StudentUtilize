package com.example.layoutspractice

import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class PdfViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageView = ImageView(this)
        setContentView(imageView)

        val fileName = "academicalendar.pdf"

        // 1. Copy PDF from assets to cache dir
        val file = File(cacheDir, fileName)
        if (!file.exists()) {
            val input = assets.open(fileName)
            val output = FileOutputStream(file)
            input.copyTo(output)
            input.close()
            output.close()
        }

        // 2. Open file with PdfRenderer
        val fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        val renderer = PdfRenderer(fileDescriptor)

        if (renderer.pageCount > 0) {
            val page = renderer.openPage(0) // Only showing page 1 for now
            val bitmap = android.graphics.Bitmap.createBitmap(
                page.width,
                page.height,
                android.graphics.Bitmap.Config.ARGB_8888
            )
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            imageView.setImageBitmap(bitmap)
            page.close()
        }

        renderer.close()
    }
}
