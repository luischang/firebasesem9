package dev.luischang.firebasesem9

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvCurso: TextView = findViewById(R.id.tvCurso)
        val tvNota: TextView = findViewById(R.id.tvNota)
        val db = FirebaseFirestore.getInstance()

        db.collection("courses")
            .addSnapshotListener{snapshots, error ->
                if(error != null){
                    Log.w("MainActivity", "Listen failed", error)
                    return@addSnapshotListener
                }

                snapshots?.documents?.forEach { dc ->
                    tvCurso.text = dc.getString("description")
                    tvNota.text = dc.getString("score")
                }
//                for (dc in snapshots!!.documents){
//                    val curso = dc.getString("description")
//                    val nota = dc.getString("score")
//                    tvCurso.text = curso
//                    tvNota.text = nota
//                }
            }
    }
}