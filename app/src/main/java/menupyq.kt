

import android.widget.Toast
import com.example.layoutspractice.R
import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.PopupMenu



class menupyq{

    fun assignmentss (context: Context, view: View){
        val contextThemeWrapper = ContextThemeWrapper(context, R.style.CustomPopupMenu)
        val pop = PopupMenu(contextThemeWrapper, view)

        pop.inflate(R.menu.menu4)

        pop.setOnMenuItemClickListener {

            when (it!!.itemId) {
                R.id.class9 -> {
                    Toast.makeText(context, "No data available#", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.class10 ->{
                    Toast.makeText(context, "No data available#", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.class11science ->{
                    Toast.makeText(context, "No data available#", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.class11commerce->{
                    Toast.makeText(context, "No data available#", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.class11arts->{
                    Toast.makeText(context, "No data available#", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.class12science->{
                    Toast.makeText(context, "No data available#", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.class12commerce->{
                    Toast.makeText(context, "No data available#", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.class12arts->{
                    Toast.makeText(context, "No data available#", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {true}

            }
        }
        pop.show()
    }
}