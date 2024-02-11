import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        KoinModulesKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}