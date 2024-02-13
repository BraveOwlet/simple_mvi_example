import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        AppModulesKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}