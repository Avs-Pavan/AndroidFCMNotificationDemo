package com.kevin.androidfcmnotificationdemo.utils.eventbus

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventBus {
    private val eventChannel = MutableSharedFlow<Event>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val events: Flow<Event> = eventChannel.asSharedFlow()

    fun postEvent(event: Event) {
        // Post the event to the shared flow
        eventChannel.tryEmit(event)
    }
}