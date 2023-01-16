package fr.loic.occibulle

import java.net.URL

class EmojiModel(
    val id: String = "Emoji0",
    val name: String = "emoji",
    val desc: String = "description",
    val imageUrl: String = "https://images.emojiterra.com/google/noto-emoji/v2.034/512px/1fae2.png",
    var liked: Boolean = false
)