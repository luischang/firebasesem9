package dev.luischang.firebasesem9

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etFullName: EditText = findViewById(R.id.etFullName)
        val etCountry: EditText = findViewById(R.id.etCountry)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val btSaveRegister: Button = findViewById(R.id.btSaveRegister)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        btSaveRegister.setOnClickListener {
            val fullName = etFullName.text.toString()
            val country = etCountry.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            //Generate user in Firebase Auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user: FirebaseUser? = auth.currentUser
                        val userModel = hashMapOf(
                            "fullName" to fullName,
                            "country" to country,
                            "email" to email,
                            "uid" to user?.uid
                        )
                        db.collection("users")
                            .add(userModel)
                            .addOnSuccessListener { documentReference ->
                                //Snackbar
                                Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "User registered successfully",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                //Redirect to LoginActivity
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                            .addOnFailureListener { e ->
                                //Snackbar error
                                Snackbar.make(
                                    findViewById(android.R.id.content),
                                    "Error registering user",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                    }
                }
        }
    }
}