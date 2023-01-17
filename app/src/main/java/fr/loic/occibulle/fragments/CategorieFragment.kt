package fr.loic.occibulle.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.loic.occibulle.EmojiRepository.Singleton.emojiList
import fr.loic.occibulle.MainActivity
import fr.loic.occibulle.R
import fr.loic.occibulle.adapter.EmojiAdapter
import fr.loic.occibulle.adapter.EmojiItemDecoration
import java.security.AccessControlContext

class CategorieFragment(
    private val context: MainActivity
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_categorie, container, false)

        // recuperer le recycleview
        val categorieRecyclerView = view?.findViewById<RecyclerView>(R.id.categorie_recycle_list)
        categorieRecyclerView?.adapter = EmojiAdapter(context, emojiList.filter { it.liked }, R.layout.item_vertical_emoji)
        categorieRecyclerView?.layoutManager = LinearLayoutManager(context)
        categorieRecyclerView?.addItemDecoration(EmojiItemDecoration())

        return view
    }
}