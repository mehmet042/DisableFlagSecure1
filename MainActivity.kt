package com.example.disableflagsecure

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var btnEnable: Button
    private lateinit var btnDisable: Button
    private lateinit var btnCheck: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.tvStatus)
        btnEnable  = findViewById(R.id.btnEnable)
        btnDisable = findViewById(R.id.btnDisable)
        btnCheck   = findViewById(R.id.btnCheck)

        btnEnable.setOnClickListener  { enableSecure() }
        btnDisable.setOnClickListener { disableSecure() }
        btnCheck.setOnClickListener   { checkSecure() }

        checkSecure()   // İlk açılışta mevcut durumu göster
    }

    /** FLAG_SECURE'u etkinleştirir — ekran görüntüsü / kayıt engellenir */
    private fun enableSecure() {
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        updateStatus("🔒 FLAG_SECURE ETKİN\nEkran görüntüsü ve ekran kaydı engellendi.", true)
    }

    /** FLAG_SECURE'u devre dışı bırakır */
    private fun disableSecure() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        updateStatus("🔓 FLAG_SECURE DEVRE DIŞI\nEkran görüntüsü ve ekran kaydına izin verildi.", false)
    }

    /** Mevcut durumu kontrol eder */
    private fun checkSecure() {
        val flags = window.attributes.flags
        val isSecure = flags and WindowManager.LayoutParams.FLAG_SECURE != 0
        if (isSecure) {
            updateStatus("🔒 Mevcut Durum: FLAG_SECURE ETKİN", true)
        } else {
            updateStatus("🔓 Mevcut Durum: FLAG_SECURE DEVRE DIŞI", false)
        }
    }

    private fun updateStatus(message: String, isSecure: Boolean) {
        statusText.text = message
        statusText.setBackgroundColor(
            if (isSecure)
                getColor(android.R.color.holo_red_light)
            else
                getColor(android.R.color.holo_green_light)
        )
    }
}
