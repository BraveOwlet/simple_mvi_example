package ru.braveowlet.common.mvi.impl

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.koin.core.qualifier.qualifier
import ru.braveowlet.common.mvi.general.api.Mvi
import ru.braveowlet.common.mvi.general.api.models.MviAction
import ru.braveowlet.common.mvi.general.api.models.MviEffect
import ru.braveowlet.common.mvi.general.api.models.MviEvent
import ru.braveowlet.common.mvi.general.api.base.BaseMviScreen
import ru.braveowlet.common.mvi.general.api.models.MviState

abstract class MviScreen<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val tag: String,
    private val content: @Composable Mvi<Action, Effect, Event, State>.(state: State) -> Unit
) : Screen, BaseMviScreen<Action, Effect, Event, State> {

    @Composable
    override fun interractor(): Mvi<Action, Effect, Event, State> =
        getScreenModel<MviModel<Action, Effect, Event, State>>(qualifier(tag)).interractor


    @Composable
    override fun Content() = mviContent(content)
}
