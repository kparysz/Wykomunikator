package pl.kparysz.wykomessages.models.pojo

import com.google.gson.annotations.SerializedName

class PrivateMessage {
    @SerializedName("date")
    var lastUpdate: String? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("author_avatar")
    var authorAvatar: String? = null
    @SerializedName("author_avatar_med")
    var authorAvatarMedium: String? = null
    @SerializedName("author_avatar_lo")
    var authorAvatarLow: String? = null
    @SerializedName("author_group")
    var authorGroup: Int? = null
    @SerializedName("author_sex")
    var authorSex: String? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("body")
    var body: String? = null
    @SerializedName("direction")
    var direction: String? = null
}
