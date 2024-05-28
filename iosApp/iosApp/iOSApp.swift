import SwiftUI
import app

class IosUtilsImpl: IosUtils {
    func getThreadName() -> String{
        if let queueName = String(validatingUTF8: __dispatch_queue_get_label(nil)) {
                    return queueName
                } else if let operationQueueName = OperationQueue.current?.name, !operationQueueName.isEmpty {
                    return operationQueueName
                } else if let dispatchQueueName = OperationQueue.current?.underlyingQueue?.label, !dispatchQueueName.isEmpty {
                    return dispatchQueueName
                } else {
                    return "failed get thread name"
                }
    }
}

@main
struct iOSApp: App {

    init() {
        MainViewControllerKt.doInitApp(iosUtils: IosUtilsImpl())
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
