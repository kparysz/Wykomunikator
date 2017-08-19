package pl.kparysz.wykomessages.login.repository

import io.reactivex.Observable
import pl.kparysz.wykomessages.models.dataclass.User

interface UserLoginApi {
    fun loginUser(token: String): Observable<User>
}