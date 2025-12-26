cat > app/src/main/java/cat/alfonso/p2pstadium/P2PMessageDao.kt << 'EOF'
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface P2PMessageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessage(message: P2PMessage): Long
    @Query("SELECT * FROM p2p_messages ORDER BY timestamp DESC LIMIT 50")
    fun getRecentMessages(): Flow<List<P2PMessage>>
}
EOF
