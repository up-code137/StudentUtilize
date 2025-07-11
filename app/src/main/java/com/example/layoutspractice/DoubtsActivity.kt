package com.example.layoutspractice

import Doubt
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DoubtsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DoubtAdapter
    private val doubtList = ArrayList<Doubt>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doubts) // 👈 this must match your layout file

        recyclerView = findViewById(R.id.doubtRecyclerView)
        adapter = DoubtAdapter(doubtList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid

        FirebaseDatabase.getInstance().getReference("doubts")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    doubtList.clear()
                    for (doubtSnap in snapshot.children) {
                        val doubt = doubtSnap.getValue(Doubt::class.java)
                        if (doubt != null && doubt.uid == currentUserUid) {
                            doubtList.add(doubt)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@DoubtsActivity, "Failed to load doubts", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
