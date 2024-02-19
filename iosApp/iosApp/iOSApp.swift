import SwiftUI
import app

@main
struct iOSApp: App {

    init() {
        MainViewControllerKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}