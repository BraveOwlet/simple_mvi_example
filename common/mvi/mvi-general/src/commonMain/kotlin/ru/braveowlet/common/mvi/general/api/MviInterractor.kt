package ru.braveowlet.common.mvi.general.api

import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.common.mvi.general.api.models.MviActor
import ru.braveowlet.common.mvi.general.api.models.MviBootstrap
import ru.braveowlet.common.mvi.general.api.models.MviCoroutineScope
import ru.braveowlet.common.mvi.general.api.models.MviEffect
import ru.braveowlet.common.mvi.general.api.models.MviEvent
import ru.braveowlet.common.mvi.general.api.models.MviReducer
import ru.braveowlet.common.mvi.general.api.models.MviState
import ru.braveowlet.common.mvi.general.impl.MviInterractorImpl

interface MviInterractor<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>
    : Mvi<Action, Effect, Event, State> {

    suspend fun push(event: Event)
    suspend fun push(effect: Effect)

    class Builder<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> {

        private var logEnable: Boolean = true
        private var customLogEnable: Boolean = true

        private lateinit var defaultState: State
        private lateinit var tag: String
        private lateinit var coroutineScope: MviCoroutineScope
        private lateinit var reducer: MviReducer<Effect, State>
        private lateinit var bootstrap: MviBootstrap
        private lateinit var actor: MviActor<Action>

        fun setLogEnable(enable: Boolean): Builder<Action, Effect, Event, State> =
            this.apply {
                this@apply.logEnable = enable
            }

        fun setCustomLogEnable(enable: Boolean): Builder<Action, Effect, Event, State> =
            this.apply {
                this@apply.customLogEnable = enable
            }

        fun setDefaultState(state: State): Builder<Action, Effect, Event, State> =
            this.apply {
                this@apply.defaultState = state
            }

        fun setTag(tag: String): Builder<Action, Effect, Event, State> =
            this.apply {
                this@apply.tag = tag
            }

        fun setScope(scope: MviCoroutineScope): Builder<Action, Effect, Event, State> =
            this.apply {
                this@apply.coroutineScope = scope
            }

        fun setReducer(reducer: MviReducer<Effect, State>): Builder<Action, Effect, Event, State> =
            this.apply {
                this@apply.reducer = reducer
            }

        fun setBootstrap(bootstrap: MviBootstrap): Builder<Action, Effect, Event, State> =
            this.apply {
                this@apply.bootstrap = bootstrap
            }

        fun setActor(actor: MviActor<Action>): Builder<Action, Effect, Event, State> =
            this.apply {
                this@apply.actor = actor
            }

        fun build(): MviInterractor<Action, Effect, Event, State> = MviInterractorImpl(
            defaultState = defaultState,
            tag = tag,
            logEnable = logEnable,
            customLogEnable = customLogEnable,
            reducer = reducer,
            bootstrap = bootstrap,
            actor = actor,
            coroutineScope = coroutineScope
        )
    }
}
