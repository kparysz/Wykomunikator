package pl.kparysz.wykomessages.models.dataclass

data class PrivateMessageDetail(val body: String,
                                val author: String,
                                val date: String,
                                val isOwner: Boolean)