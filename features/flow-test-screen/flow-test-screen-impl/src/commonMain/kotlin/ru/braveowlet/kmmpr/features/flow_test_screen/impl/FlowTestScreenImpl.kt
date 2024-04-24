package ru.braveowlet.kmmpr.features.flow_test_screen.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.flow_test_screen.api.FlowTestScreenApi
import ru.braveowlet.kmmpr.features.flow_test_screen.impl.screens.resources_screen.FlowTestScreen

internal class FlowTestScreenImpl(
    private val tag : String
) : FlowTestScreenApi {

    override fun flowTestScreen(): Screen = FlowTestScreen(tag)
}
