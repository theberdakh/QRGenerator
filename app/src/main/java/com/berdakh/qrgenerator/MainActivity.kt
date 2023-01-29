package com.berdakh.qrgenerator

import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.WindowManager
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.berdakh.qrgenerator.databinding.ActivityMainBinding
import java.nio.charset.CharsetEncoder


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var bitmap: Bitmap
    lateinit var qrEncoder: QRGEncoder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnQr.setOnClickListener {
                if (etQr.text.toString().isNotEmpty()) {
                    val windowManager: WindowManager =
                        getSystemService(WINDOW_SERVICE) as WindowManager

                    // on below line we are initializing a
                    // variable for our default display
                    val display: Display = windowManager.defaultDisplay

                    // on below line we are creating a variable
                    // for point which is use to display in qr code
                    val point: Point = Point()
                    display.getSize(point)

                    // on below line we are getting
                    // height and width of our point
                    val width = point.x
                    val height = point.y

                    var dimen = if (width < height) width else height
                    dimen = dimen * 3 / 4

                    // on below line we are initializing our qr encoder
                    qrEncoder = QRGEncoder(etQr.text.toString(), null, QRGContents.Type.TEXT, dimen)

                    try {

                        bitmap = qrEncoder.bitmap

                        // on below line we are setting
                        // this bitmap to our image view
                        ivQr.setImageBitmap(bitmap)

                    } catch (e: Exception) {
                        // on below line we
                        // are handling exception
                        e.printStackTrace()
                    }

                } else {
                    Toast.makeText(applicationContext, "Enter your message", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


    }
}