package nl.soqua.dl.domain.projection.album

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.jetbrains.exposed.sql.transactions.transaction
import rx.Observable
import rx.lang.kotlin.observable

class AlbumProjection() {
    val gson: Gson = GsonBuilder().create()

    fun <T> decodeJSON(model: String, target: Class<T>): T {
        return gson.fromJson(model, target)
    }

    fun <T> lazyGetIterable(body: () -> Iterable<T>): Observable<T> = observable { subscriber ->
        if (subscriber.isUnsubscribed) {
            return@observable
        }
        try {
            transaction {
                body().forEach { subscriber.onNext(it) }
            }
            subscriber.onCompleted()
        } catch (e: Exception) {
            subscriber.onError(e)
        }
    }

    fun albumByIdentity(identity: String?): Observable<Album> = identity.let {
        if (identity != null) {
            return lazyGetIterable {
                AlbumProjectionModel
                        .find { AlbumProjectionTable.identity eq identity }
                        .map { decodeJSON(it.data, Album::class.java) }
            }
        } else {
            return Observable.empty()
        }
    }
}
