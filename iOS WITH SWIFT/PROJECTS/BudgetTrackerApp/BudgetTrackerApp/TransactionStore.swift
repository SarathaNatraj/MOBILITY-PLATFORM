//
//  TransactionStore.swift
//  BudgetTrackerApp
//
//  Created by admin on 27/01/25.
//

import Foundation


//Transaction Model
struct Transaction : Identifiable{
    let id = UUID()
    let amount: Double
    let  catagory: String
    let description: String
}


//Service layer class to manage the transactions
//common class shared multiple views
//Inside Views you have to declare this class object EnviromentObject
class TransactionStore : ObservableObject{
    @Published var transactions: [Transaction] = []
    
    //total income
    var totalIncome : Double {
        transactions.filter{$0.catagory == "Income"}.reduce(0) {$0+$1.amount}
    }
    
    //total expense
    var totalExpense : Double {
        transactions.filter{$0.catagory == "Expense"}.reduce(0) {$0+$1.amount}
    }
    var currentBalance : Double {
        totalIncome - totalExpense
    }
}
