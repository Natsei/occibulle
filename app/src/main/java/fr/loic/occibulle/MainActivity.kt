package fr.loic.occibulle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.loic.occibulle.fragments.CategorieFragment
import fr.loic.occibulle.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // charger notre emoji repository
        val repo = EmojiRepository()

        //mettre a jour la liste
        repo.updateData{
            // injecte le fragment dans notre boite
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}