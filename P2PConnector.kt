cat > app/src/main/java/cat/alfonso/p2pstadium/P2PConnector.kt << 'EOF'
import android.content.Context
import android.net.wifi.p2p.WifiP2pManager
import android.widget.Toast

class P2PConnector(private val context: Context) {
    private val manager = context.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
    private val channel = manager.initialize(context, context.mainLooper, null)
    fun startDiscovery() {
        manager.discoverPeers(channel, object : WifiP2pManager.ActionListener {
            override fun onSuccess() {
                Toast.makeText(context, "Descobrint peers...", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(reason: Int) {
                Toast.makeText(context, "Error al descobrir: $reason", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
EOF
