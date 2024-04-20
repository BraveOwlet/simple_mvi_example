package ru.braveowlet.common.mvi.impl

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.koin.core.qualifier.qualifier
import ru.braveowlet.common.mvi.general.models.Mvi
import ru.braveowlet.common.mvi.general.models.MviAction
import ru.braveowlet.common.mvi.general.models.MviEffect
import ru.braveowlet.common.mvi.general.models.MviEvent
import ru.braveowlet.common.mvi.general.presentation.BaseMviScreen
import ru.braveowlet.common.mvi.general.models.MviState

abstract class MviScreen<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    tag: String,
    content: @Composable Mvi<Action, Effect, Event, State>.(state: State) -> Unit
) : Screen, BaseMviScreen<Action, Effect, Event, State>(tag, content) {

    @Composable
    override fun getMvi(key: String): Mvi<Action, Effect, Event, State> {
        return getScreenModel<MviModel<Action, Effect, Event, State>>(qualifier(key)).controller
    }

    @Composable
    override fun Content() {
        mviContent()
    }
}
