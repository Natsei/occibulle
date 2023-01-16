package fr.loic.occibulle.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.loic.occibulle.EmojiModel
import fr.loic.occibulle.EmojiRepository.Singleton.emojiList
import fr.loic.occibulle.MainActivity
import fr.loic.occibulle.R
import fr.loic.occibulle.adapter.EmojiAdapter
import fr.loic.occibulle.adapter.EmojiItemDecoration

class HomeFragment(
    private val context: MainActivity
) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)


        // recuperer le cycyvlerview
        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView?.adapter = EmojiAdapter(context, emojiList, R.layout.item_horizontal_emoji)

        //recuperer le second recyclerview
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = EmojiAdapter(context, emojiList, R.layout.item_vertical_emoji)
        verticalRecyclerView?.addItemDecoration(EmojiItemDecoration())
        return view
    }
}