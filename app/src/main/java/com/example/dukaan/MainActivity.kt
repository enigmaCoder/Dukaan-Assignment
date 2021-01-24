package com.example.dukaan

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.floating_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        fun onClickWhatsApp(context: Context) {
            val pm: PackageManager = context.packageManager
            try {
                val waIntent = Intent(Intent.ACTION_SEND)
                waIntent.type = "text/plain"
                val text = "Hey! Now you can visit the online store and place the orders from this <a href='sellplus.com/mano'>link</a>"
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
                waIntent.setPackage("com.whatsapp")
                waIntent.putExtra(Intent.EXTRA_TEXT, text)
                ContextCompat.startActivity(
                    context,
                    Intent.createChooser(waIntent, "Share with"),
                    null
                )
            } catch (e: PackageManager.NameNotFoundException) {
                Toast.makeText(context, "WhatsApp not Installed", Toast.LENGTH_SHORT).show()
            }
        }
        navView.setupWithNavController(navController)
        val badge = navView.getOrCreateBadge(R.id.navigation_orders)
        badge.number = 3
        badge.isVisible = true
        share_btn.setOnClickListener {
            onClickWhatsApp(this)
        }
    }
}