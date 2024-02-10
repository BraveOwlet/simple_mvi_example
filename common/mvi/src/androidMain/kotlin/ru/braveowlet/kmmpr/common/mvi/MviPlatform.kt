package ru.braveowlet.kmmpr.common.mvi

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MviViewModel<Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> @Inject constructor(
    defaultState: State,
    actor: MviActor<Action, Effect, State>,
    boot: MviBoot<Effect>,
    eventProducer: MviEventProducer<Effect, Event, State>,
    stateProducer: MviStateProducer<Effect, State>,
    tag: String,
    logEnable: Boolean = true,
    logger: MviLogger = MviLoggerDefault,
) : ViewModel() {
    val controller = MviController(
        defaultState = defaultState,
        actor = actor,
        boot = boot,
        eventProducer = eventProducer,
        stateProducer = stateProducer,
        tag = tag,
        logEnable = logEnable,
        logger = logger,
    )
}

@Composable
fun <Action : MviAction, Effect : MviEffect, Event : MviEvent, State : MviState> getMvi(
    defaultState: State,
    actor: MviActor<Action, Effect, State>,
    boot: MviBoot<Effect>,
    eventProducer: MviEventProducer<Effect, Event, State>,
    stateProducer: MviStateProducer<Effect, State>,
    tag: String,
    logEnable: Boolean = true,
    logger: MviLogger = MviLoggerDefault,
): Mvi<Action, Event, State> {

    val viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    val viewModel: MviViewModel<Action, Effect, Event, State> = viewModel(
        key = tag,
        factory = if (viewModelStoreOwner is HasDefaultViewModelProviderFactory) {
            HiltViewModelFactory(
                context = LocalContext.current,
                delegateFactory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return MviViewModel(
                            defaultState = defaultState,
                            actor = actor,
                            boot = boot,
                            eventProducer = eventProducer,
                            stateProducer = stateProducer,
                            tag = tag,
                            logEnable = logEnable,
                            logger = logger,
                        ) as T
                    }
                }
            )
        } else {
            null
        }
    )
    return viewModel.controller
}