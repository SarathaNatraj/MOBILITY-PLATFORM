//
//  BudgetTrackerAppApp.swift
//  BudgetTrackerApp
//
//  Created by admin on 27/01/25.
//

import SwiftUI

@main
struct BudgetTrackerAppApp: App {
    @StateObject private var transactionStore = TransactionStore()
    
    var body: some Scene {
        WindowGroup {
            ContentView().environmentObject(transactionStore)
        }
    }
}
