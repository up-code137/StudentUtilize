package com.example.layoutspractice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class signuplayout : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signuplayout)

        // This handles padding for status bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigation: Already have account? Go back to Login
        val login = findViewById<Button>(R.id.login33)
        login.setOnClickListener {
            val intent = Intent(this, loginlayout::class.java)
            startActivity(intent)
        }

        // Inputs
        val signup = findViewById<Button>(R.id.signupp)
        val email = findViewById<EditText>(R.id.email123)
        val password = findViewById<EditText>(R.id.password1)
        val confirmpass = findViewById<EditText>(R.id.confirmpass)
        val schoolid = findViewById<EditText>(R.id.schoolid)
        val firstname = findViewById<EditText>(R.id.firstnamee)
        val lastname = findViewById<EditText>(R.id.lastname)

        // When Signup button is clicked
        signup.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val confirmpassText = confirmpass.text.toString().trim()
            val schoolidText = schoolid.text.toString().trim()
            val firstnameText = firstname.text.toString().trim()
            val lastnameText = lastname.text.toString().trim()

            // ✅ Step 1: Check if fields are empty
            if (emailText.isEmpty() || passwordText.isEmpty() || confirmpassText.isEmpty()
                || schoolidText.isEmpty() || firstnameText.isEmpty() || lastnameText.isEmpty()
            ) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Step 2: Check if passwords match
            if (passwordText != confirmpassText) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Step 3: Firebase Signup
            Firebase.auth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val uid = task.result?.user?.uid // ✅ Get UID before signOut()

                        val userData = mapOf(
                            "fullName" to "$firstnameText $lastnameText",
                            "schoolId" to schoolidText
                        )

                        if (uid != null) {
                            FirebaseDatabase.getInstance().getReference("users")
                                .child(uid)
                                .setValue(userData)
                                .addOnSuccessListener {
                                    Firebase.auth.signOut()
                                    Toast.makeText(this, "✅ Account created successfully!", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, loginlayout::class.java))
                                    finish()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "❌ Failed to save user data", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(this, "❌ UID is null", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "❌ Signup failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }
}
