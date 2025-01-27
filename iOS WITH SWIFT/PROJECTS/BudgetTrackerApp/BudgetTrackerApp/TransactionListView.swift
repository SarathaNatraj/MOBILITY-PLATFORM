//
//  TransactionListView.swift
//  BudgetTrackerApp
//
//  Created by admin on 27/01/25.
//

import SwiftUI

struct TransactionListView: View {
    @EnvironmentObject var transactionStore : TransactionStore
    
    
    var body: some View {
        List{
            ForEach(transactionStore.transactions){ transaction in
                HStack{
                    VStack(alignment: .leading){
                        Text(transaction.description).font(.headline)
                        Text(transaction.catagory).font(.subheadline).foregroundColor(.gray)
                    }
                Spacer()
                    Text("$\(transaction.amount)").foregroundColor(transaction.catagory == "Income" ? .green : .red)
                }
            }
        }
        .navigationTitle("Transactions")
        .toolbar {
            NavigationLink(destination : TransactionFormView()){
                Text("Add")
            }
        }
    }
}

struct TransactionListView_Previews: PreviewProvider {
    static var previews: some View {
        TransactionListView()
    }
}
