package ru.braveowlet.common.mvi.impl

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.koin.core.qualifier.qualifier
import ru.braveowlet.common.mvi.general.Mvi
import ru.braveowlet.common.mvi.general.MviBaseView
import ru.braveowlet.common.mvi.general.MviAction
import ru.braveowlet.common.mvi.general.MviEffect
import ru.braveowlet.common.mvi.general.MviEvent
import ru.braveowlet.common.mvi.general.MviState

abstract class MviView<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState>(
    private val tag: String,
    private val content: @Composable Mvi<Action, Effect, Event, State>.(state: State) -> Unit
) : Screen, MviBaseView<Action, Effect, Event, State> {

    @Composable
    override fun mvi(): Mvi<Action, Effect, Event, State> =
        getScreenModel<MviModel<Action, Effect, Event, State>>(qualifier(tag)).mvi


    @Composable
    override fun Content() = mviContent(content)
}
