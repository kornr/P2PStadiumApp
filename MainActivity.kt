cat > app/src/main/java/cat/alfonso/p2pstadium/MainActivity.kt << 'EOF'
package cat.alfonso.p2pstadium

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {
    private lateinit var statusText: TextView
    private lateinit var db: P2PDatabase
    private lateinit var connector: P2PConnector

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startP2P()
        } else {
            statusText.text = "Cal permís de ubicació per Wi-Fi P2P"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        statusText = findViewById(R.id.statusText)
        db = P2PDatabase.getInstance(this)
        connector = P2PConnector(this)
        findViewById<Button>(R.id.startButton).setOnClickListener {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        when {
            android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU -> {
                requestPermissionLauncher.launch(Manifest.permission.NEARBY_WIFI_DEVICES)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun startP2P() {
        lifecycleScope.launchWhenCreated {
            connector.startDiscovery()
            statusText.text = "Xarxa P2P activa. Descobrint..."
        }
    }
}
EOF
