package ru.braveowlet.kmmpr.features.flow_test_screen.api

import cafe.adriel.voyager.core.screen.Screen

interface FlowTestScreenApi {

    fun flowTestScreen() : Screen

    companion object {
        const val TAG: String = "FlowTestScreen"
    }
}
