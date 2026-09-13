package com.route.data.core.session

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton


sealed interface SessionEvent {
    data object Expired : SessionEvent
    data object LoggedOut : SessionEvent
}

@Singleton
class SessionManager @Inject constructor() {

    private val _sessionEvents = Channel<SessionEvent>(Channel.BUFFERED)
    val sessionEvents: Flow<SessionEvent> = _sessionEvents.receiveAsFlow()

    suspend fun notifySessionExpired() {
        _sessionEvents.send(SessionEvent.Expired)
    }

    suspend fun notifyLoggedOut() {
        _sessionEvents.send(SessionEvent.LoggedOut)
    }
}
