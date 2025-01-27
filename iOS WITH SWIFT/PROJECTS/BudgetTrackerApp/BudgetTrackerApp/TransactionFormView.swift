//
//  TransactionFormView.swift
//  BudgetTrackerApp
//
//  Created by admin on 27/01/25.
//

import SwiftUI

struct TransactionFormView: View {
    @EnvironmentObject var transactionStore : TransactionStore
    @Environment(\.presentationMode) var presentationMode
    
    @State private var amount : String = ""
    @State private var catagory : String = "Income"
    @State private  var description : String = ""
    
    let catagories = ["Income", "Expense"]
    
    var body: some View {
        
            Form {
                Section(header: Text("Transaction Details")){
                    TextField("Amount", text:$amount).keyboardType(.decimalPad)
                    TextField("Description", text:$description)
                    Picker("Catagory", selection: $catagory){
                        ForEach(catagories, id:\.self) { cat in
                            Text(cat)
                        }
                    }
                    Button (action:{
                        saveTransaction()
                    }){
                        Text("Save Transaction")
                            .fontWeight(.bold)
                            .padding()
                            .background(Color.blue)
                            .foregroundColor(Color.white)
                    }
                }
                
                .navigationTitle("New Transaction")
                .navigationBarBackButtonHidden(false)
            }
        }
             func saveTransaction(){
                
                guard let transactionAmount = Double(amount) else{
                    print("Invalid amount")
                    return
                }
                let transaction = Transaction (amount: transactionAmount, catagory: catagory, description: description)
                print("Saved Transaction :\(transaction)")
                 print("Before saving : \(transactionStore.transactions)")
                 //Later this code can be changed to with db insert
                 transactionStore.transactions.append(transaction)
                 print("after saving : \(transactionStore.transactions)")
                 presentationMode.wrappedValue.dismiss()
                
            }
        }
    




struct TransactionFormView_Previews: PreviewProvider {
    static var previews: some View {
        TransactionFormView()
    }
}
