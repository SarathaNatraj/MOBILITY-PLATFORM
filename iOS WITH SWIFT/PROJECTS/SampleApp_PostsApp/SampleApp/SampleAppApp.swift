//
//  SampleAppApp.swift
//  SampleApp
//
//  Created by admin on 26/01/25.
//

import SwiftUI

@main
struct SampleAppApp: App {
    let persistentController = PersistenceController.shared
    
    var body: some Scene {
        WindowGroup {
            PostsView()
                .environment(\.managedObjectContext, persistentController.container.viewContext)
        }
    }
}
