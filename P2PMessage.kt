cat > app/src/main/java/cat/alfonso/p2pstadium/P2PMessage.kt << 'EOF'
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "p2p_messages", indices = [Index(value = ["msg_id"], unique = true)])
data class P2PMessage(
    @PrimaryKey
    val msg_id: String,
    val sender_id: String,
    val timestamp: Long,
    val ttl: Int,
    val type: String,
    val zone: String?,
    val content: String,
    val signature: String? = null
)
EOF
