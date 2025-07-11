import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.layoutspractice.R
import com.example.layoutspractice.databinding.ActivityMainBinding
import com.example.layoutspractice.loginlayout
import com.example.layoutspractice.profilepage
import com.example.layoutspractice.settingpage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class custom_menu {

    fun customMenu(context: Context, view: View) {

        val pop = PopupMenu(context, view)
        pop.inflate(R.menu.menu1)

        pop.setOnMenuItemClickListener {
            when (it!!.itemId) {
                R.id.first -> {
                    val intent = Intent(context, profilepage::class.java)
                    context.startActivity(intent)
                    true
                }
                R.id.first2 -> {
                    val intent = Intent(context, settingpage::class.java)
                    context.startActivity(intent)
                    true
                }

                R.id.first4 -> {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Join Community 🔒")
                    builder.setMessage("This feature is coming soon. Stay tuned!")
                    builder.setPositiveButton("OK", null)
                    builder.show()
                    true
                }

                R.id.first3 -> {
                    val dialog = AlertDialog.Builder(context)
                    dialog.setCancelable(false)
                    dialog.setTitle("Logout")
                    dialog.setMessage("Are you sure you want to logout?")
                    dialog.setPositiveButton("Yes") { dialogInterface, which ->
                        // ✅ Step 1: Sign out from Firebase
                        Firebase.auth.signOut()

                        // ✅ Step 2: Show toast
                        Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()

                        // ✅ Step 3: Go back to login screen
                        val intent = Intent(context, loginlayout::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                    }
                    dialog.setNegativeButton("No") { dialogInterface, which ->
                        // ❌ Cancel logout
                        Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
                        dialogInterface.dismiss()
                    }
                    dialog.show()
                    true
                }

                R.id.first5 -> {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Check this app!")
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey! Download this cool study app made for students.")
                    context.startActivity(Intent.createChooser(intent, "Share via"))
                    true
                }


                else -> {true}
            }

        }
        pop.show()


    }
}