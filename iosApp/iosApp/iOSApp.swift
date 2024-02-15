import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        AppKoinModulesKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}