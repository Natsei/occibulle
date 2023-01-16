package fr.loic.occibulle.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.loic.occibulle.EmojiModel
import fr.loic.occibulle.EmojiRepository
import fr.loic.occibulle.MainActivity
import fr.loic.occibulle.R


class EmojiAdapter(
    private val context: MainActivity,
    private val emojiList: List<EmojiModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<EmojiAdapter.ViewHolder>() {

    // boite pour ranger tout les coposant a controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // img de l'emoji
        val emojiImage: ImageView = view.findViewById(R.id.image_item)
        val emojiName:TextView? = view.findViewById(R.id.name_item)
        val emojiDesc:TextView? = view.findViewById(R.id.desc_item)
        val starIcon: ImageView? = view.findViewById(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les informations demoji
        val currentEmoji = emojiList[position]

        // recuperer le repository
        val repo = EmojiRepository()

        // utiliser glide pour recuper l'image a partir de son lien
        Glide.with(context).load(Uri.parse(currentEmoji.imageUrl)).into(holder.emojiImage)

        // mettre a jour le nom de l'emoji
        holder.emojiName?.text = currentEmoji.name

        // mettre a jour la desc de l'emoji
        holder.emojiDesc?.text = currentEmoji.desc

        // Verifier si l'emoji est liked

        if(currentEmoji.liked) {
            holder.starIcon?.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon?.setImageResource(R.drawable.ic_unstar)
        }
        // rajouter une interaction sur cette etoile
        holder.starIcon?.setOnClickListener{
            // inverser si le bouton est like ou non
            currentEmoji.liked = !currentEmoji.liked
            // mettre a jour l'object emoji
            repo.updateEmoji(currentEmoji)
        }
    }


    override fun getItemCount(): Int = emojiList.size
}