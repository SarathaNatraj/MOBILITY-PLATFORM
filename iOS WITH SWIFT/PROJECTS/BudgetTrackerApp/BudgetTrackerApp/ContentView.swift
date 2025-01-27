//
//  ContentView.swift
//  BudgetTrackerApp
//
//  Created by admin on 27/01/25.
//

import SwiftUI

struct ContentView: View {
    @EnvironmentObject var transactionStore : TransactionStore
    
    var body: some View {
        NavigationView{
            VStack (spacing : 20){
                Image(systemName: "globe")
                    .imageScale(.large)
                    .foregroundColor(.accentColor)
                Text("Welcome to Budget Tracker App").font(.headline).padding()
                Text("Current Balance ").font(.title2)
                Text("$\(transactionStore.currentBalance)").font(.largeTitle)
                
                Spacer()
                NavigationLink(destination: TransactionListView()){
                    Text("View Transactions")
                }
                
            }
            .navigationTitle("Dashboard")
        }
    }
}
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
