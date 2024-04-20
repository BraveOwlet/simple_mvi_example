package ru.braveowlet.kmmpr.features.resources_screen.impl

import cafe.adriel.voyager.core.screen.Screen
import ru.braveowlet.kmmpr.features.resources_screen.api.ResourcesScreenApi
import ru.braveowlet.kmmpr.features.resources_screen.impl.screens.resources_screen.view.ResourcesScreen

internal class ResourcesScreenImpl(
    private val tag : String
) : ResourcesScreenApi {

    override fun resourcesScreen(): Screen = ResourcesScreen(tag)
}
