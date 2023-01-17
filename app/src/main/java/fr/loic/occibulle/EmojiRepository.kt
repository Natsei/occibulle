package fr.loic.occibulle

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import fr.loic.occibulle.EmojiRepository.Singleton.databaseRef
import fr.loic.occibulle.EmojiRepository.Singleton.emojiList
import javax.security.auth.callback.Callback

class EmojiRepository {

    object Singleton {
        // se connecter a la reference emoji
        val databaseRef = FirebaseDatabase.getInstance().getReference("Emoji")

        // crée une liste qui va contenir nos emoji
        val emojiList = arrayListOf<EmojiModel>()
    }

    fun updateData(callback: () -> Unit){
        // absorber les données depuit la databaseRef -> list emoji
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciences
                emojiList.clear()
                // recoler la list
                for (ds in snapshot.children) {
                    // construire l'object
                    val emoji = ds.getValue(EmojiModel::class.java)

                    // verifie que la plante est pas null
                    if (emoji != null){
                        // ajoute a la liste l'emoji
                        emojiList.add(emoji)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    // update un object de la base
    fun updateEmoji(emoji: EmojiModel) = databaseRef.child(emoji.id).setValue(emoji)

    // supprimer une plnate de la base
    fun deleteEmoji(emoji: EmojiModel) = databaseRef.child(emoji.id).removeValue()


}