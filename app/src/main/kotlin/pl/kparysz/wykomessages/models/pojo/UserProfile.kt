package pl.kparysz.wykomessages.models.pojo

import com.google.gson.annotations.SerializedName

class UserProfile {
    @SerializedName("login")
    var login: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("public_email")
    var public_email: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("www")
    var www: String? = null
    @SerializedName("jabber")
    var jabber: String? = null
    @SerializedName("gg")
    var gg: String? = null
    @SerializedName("city")
    var city: String? = null
    @SerializedName("about")
    var about: String? = null
    @SerializedName("author_group")
    var author_group: Int = 0
    @SerializedName("links_added")
    var links_added: Int = 0
    @SerializedName("links_published")
    var links_published: Int = 0
    @SerializedName("comments")
    var comments: Int = 0
    @SerializedName("rank")
    var rank: Int = 0
    @SerializedName("followers")
    var followers: Int = 0
    @SerializedName("following")
    var following: Int = 0
    @SerializedName("entries")
    var entries: Int = 0
    @SerializedName("entries_comments")
    var entries_comments: Int = 0
    @SerializedName("diggs")
    var diggs: Int = 0
    @SerializedName("buries")
    var buries: Int = 0
    @SerializedName("groups")
    var groups: Int = 0
    @SerializedName("related_links")
    var related_links: Int = 0
    @SerializedName("signup_date")
    var signup_date: String? = null
    @SerializedName("avatar")
    var avatar: String? = null
    @SerializedName("avatar_big")
    var avatar_big: String? = null
    @SerializedName("avatar_med")
    var avatar_med: String? = null
    @SerializedName("avatar_lo")
    var avatar_lo: String? = null
    @SerializedName("is_observed")
    var is_observed: Boolean = false
    @SerializedName("is_blocked")
    var is_blocked: Boolean = false
    @SerializedName("sex")
    var sex: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("violation_url")
    var violation_url: String? = null
    @SerializedName("userkey")
    var userkey: String? = null
}
