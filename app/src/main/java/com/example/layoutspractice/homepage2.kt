package com.example.layoutspractice

import Doubt
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.layoutspractice.databinding.ActivityHomepage2Binding
import custom_menu
import menu_assignments
import menupyq
import menutimetabel

class homepage2 : AppCompatActivity() {
    private val binding by lazy {
        ActivityHomepage2Binding.inflate(layoutInflater)
    }
    val cusTomMenu = custom_menu()
    val menuassign = menu_assignments()
    val menutimetable = menutimetabel()
    val menupy  = menupyq()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val classList = listOf("Select Class", "9th", "10th", "11th", "12th")
        val subjectsMap = mapOf(
            "9th" to listOf("Hindi", "English", "Mathematics", "Science", "Social Science", "Sanskrit", "Computer Applications"),
            "10th" to listOf("Hindi", "English", "Mathematics", "Science", "Social Science", "Sanskrit", "Computer Applications"),
            "11th" to listOf("Physics", "Chemistry", "Biology", "Mathematics", "Economics", "History", "Geography", "Political Science", "Accountancy", "Business Studies", "Sociology", "Psychology"),
            "12th" to listOf("Physics", "Chemistry", "Biology", "Mathematics", "Economics", "History", "Geography", "Political Science", "Accountancy", "Business Studies", "Sociology", "Psychology", "Entrepreneurship")
        )
        val classSpinner = findViewById<Spinner>(R.id.classSpinner)
        val subjectSpinner = findViewById<Spinner>(R.id.subjectSpinner)

        val classAdapter = ArrayAdapter(this, R.layout.spinner_back, classList)
        classAdapter.setDropDownViewResource(R.layout.spinner_back)
        classSpinner.adapter = classAdapter

        classSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedClass = classList[position]
                val subjectList = subjectsMap[selectedClass] ?: listOf("Select Subject")
                val subjectAdapter = ArrayAdapter(this@homepage2, R.layout.spinner_back, subjectList)
                subjectAdapter.setDropDownViewResource(R.layout.spinner_back)
                subjectSpinner.adapter = subjectAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        findViewById<Button>(R.id.recordlec).setOnClickListener {
            startActivity(Intent(this, recordedLectures::class.java))
        }

        findViewById<Button>(R.id.messages).setOnClickListener {
            startActivity(Intent(this, DoubtsActivity::class.java))
        }

        findViewById<Button>(R.id.menubutton).setOnClickListener {
            cusTomMenu.customMenu(this, it)
        }

        findViewById<Button>(R.id.assignment).setOnClickListener {
            menuassign.assignmentss(this, it)
        }

        findViewById<Button>(R.id.timetablee).setOnClickListener {
            menutimetable.assignmentss(this, it)
        }

        findViewById<Button>(R.id.pyqq).setOnClickListener {
            menupy.assignmentss(this, it)
        }

        val sweetAlert = findViewById<Button>(R.id.buttonaleart)
        val doubtEditText = findViewById<EditText>(R.id.editTextText4)

        sweetAlert.setOnClickListener {
            val selectedSubject = subjectSpinner.selectedItem.toString()
            val userDoubt = doubtEditText.text.toString()

            if (selectedSubject.isNotEmpty() && userDoubt.isNotEmpty()) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    val uid = user.uid
                    val dbRef = FirebaseDatabase.getInstance().getReference("doubts").push()

                    val fakeAnswer = generateFakeAIAnswer(userDoubt, selectedSubject)

                    val doubt = Doubt(
                        subject = selectedSubject,
                        doubt = userDoubt,
                        uid = uid,
                        aiAnswer = fakeAnswer
                    )

                    dbRef.setValue(doubt)

                    SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Send Successfully")
                        .setContentText("Check your message section for the result")
                        .show()

                    doubtEditText.text.clear()
                } else {
                    Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.resultss).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://ggsipu.ac.in/ExamResults/ExamResultsmain.htm")))
        }

        findViewById<Button>(R.id.faculty).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.msit.in/it")))
        }

        findViewById<Button>(R.id.examupdates).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://ipu.ac.in/exam_notices.php")))
        }

        findViewById<Button>(R.id.academiccalender).setOnClickListener {
            startActivity(Intent(this, PdfViewerActivity::class.java))
        }

        val quotes = listOf(
            "Believe in yourself.", "Stay positive, work hard.",
            "Success is no accident.", "Small steps every day.",
            "Focus on progress, not perfection.",
            "You are stronger than you think.",
            "Push yourself, no one else will do it.",
            "Dream big. Start small. Act now.",
            "Make today count.",
            "Mistakes are proof you are trying.",
            "Never give up on a dream.",
            "Discipline is the bridge between goals and success.",
            "Hustle in silence. Let success make the noise.",
            "You miss 100% of the shots you don’t take.",
            "The best way out is always through.",
            "Every day is a fresh start.",
            "Act as if what you do makes a difference. It does.",
            "One day or day one. You decide.",
            "Do something today that your future self will thank you for.",
            "Opportunities don't happen. You create them.",
            "Wake up with determination, sleep with satisfaction.",
            "Stay away from people who drain your energy.",
            "Be a voice, not an echo.",
            "Doubt kills more dreams than failure ever will.",
            "If it was easy, everyone would do it.",
            "Prove yourself to yourself, not others.",
            "Your only limit is your mind.",
            "Work hard in silence, let your success be the noise.",
            "It always seems impossible until it’s done.",
            "The comeback is always stronger than the setback."
        )

        val index = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_YEAR) % quotes.size
        val todayQuote = quotes[index]

        findViewById<Button>(R.id.quotes).setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setTitle("🌟 Daily Quote")
                .setMessage(todayQuote)
                .setPositiveButton("Close", null)
                .show()
        }
    }

    // ✅ This is the missing function that caused red error
    private fun generateFakeAIAnswer(doubt: String, subject: String): String {
        return when (subject.lowercase()) {
            "mathematics" -> "This is a common math question. Try breaking the problem into smaller steps."
            "science" -> "This concept relates to scientific theory. Consider revisiting your class notes or textbook."
            "english" -> "Focus on grammar and comprehension here. You can re-read the passage slowly."
            "hindi" -> "You should understand the main idea and meaning of the passage or poem."
            "social science", "history", "geography", "political science" -> "Revise important dates, locations, or keywords in this topic."
            "economics", "business studies", "accountancy" -> "Revisit the definitions and examples related to this concept."
            else -> "That's a great question! Think about what you've learned so far and try to apply it."
        } + "\n\n(⚠️ Note: This is a demo AI response and may not be fully accurate.)"
    }
}
