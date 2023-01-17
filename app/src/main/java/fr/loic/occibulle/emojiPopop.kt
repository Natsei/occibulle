package fr.loic.occibulle

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.loic.occibulle.adapter.EmojiAdapter
import kotlin.math.E

class emojiPopop(
    private val adapter: EmojiAdapter,
    private val currentEmoji: EmojiModel
) : Dialog(adapter.context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popop_emoji_detail)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }

    private fun updateStar(button: ImageView) {
        val starButton = findViewById<ImageView>(R.id.star_button)

        if(currentEmoji.liked) {
            starButton.setImageResource(R.drawable.ic_star)
        }
        else {
            starButton.setImageResource(R.drawable.ic_unstar)
        }

    }

    private fun setupStarButton() {
        // recuperer
        val starButton = findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)

        // interaction
        starButton.setOnClickListener{
            currentEmoji.liked = !currentEmoji.liked
            val repo = EmojiRepository()
            repo.updateEmoji(currentEmoji)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
            // supprimer la plante de la base de donné
            val repo = EmojiRepository()
            repo.deleteEmoji(currentEmoji)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            // fermer la popup
            dismiss()
        }
    }

    private fun setupComponents() {
        // actualisé l'image de la plante
        val emojiImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentEmoji.imageUrl)).into(emojiImage)

        // actualisé le nom de l'image
        findViewById<TextView>(R.id.popup_page_emoji_name).text = currentEmoji.name

        // actualisé la desc de l'image
        findViewById<TextView>(R.id.popup_page_emoji_subtitle).text = currentEmoji.desc
    }
}