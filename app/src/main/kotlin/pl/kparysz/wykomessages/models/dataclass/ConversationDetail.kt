package pl.kparysz.wykomessages.models.dataclass

data class ConversationDetail(val authorName: String,
                              val avatarUrl: String,
                              val lastUpdate: String,
                              val status: String,
                              val authorGroup: Int,
                              val authorSex: String) {
    var onClickAction: () -> Unit = {}
}