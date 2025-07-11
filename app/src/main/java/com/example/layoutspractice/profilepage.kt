package com.example.layoutspractice

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference

class profilepage : AppCompatActivity() {

    private lateinit var nameText: TextView
    private lateinit var emailText: TextView
    private lateinit var schoolIdText: TextView
    private lateinit var avatarImage: ImageView
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilepage)

        nameText = findViewById(R.id.nameText)
        emailText = findViewById(R.id.emailText)
        schoolIdText = findViewById(R.id.schoolIdText)

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            emailText.text = user.email

            // Firebase Realtime Database reference
            databaseRef = FirebaseDatabase.getInstance().getReference("users").child(user.uid)
            databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    nameText.text = snapshot.child("fullName").value.toString()
                    schoolIdText.text = snapshot.child("schoolId").value.toString()
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }
}
