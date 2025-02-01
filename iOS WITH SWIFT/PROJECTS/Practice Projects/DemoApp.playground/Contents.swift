import Foundation
import Combine


class BudgetTracker{
    private var budgetSubject = CurrentValueSubject<Double,Never>(0.0)
    
    private var cancellables = Set<AnyCancellable>()
    
    
    var currentBudget : Double{
        budgetSubject.value
    }
    
    init(){
        budgetSubject.sink{ updatedBudget in
            print("Updated budget: $\(updatedBudget)")
            
        }.store(in: &cancellables) //store the subscriptions
    }
    
    func addIncome(_ amount : Double){
        budgetSubject.value  += amount
        print("Income added : $\(amount)")
        
        //iterate set, cancel
    
    }
    
    func addExpense(_ amount : Double){
        guard budgetSubject.value >= amount else{
            print("Insufficent balance for this expense !")
            return
        }
        budgetSubject.value -= amount
        print("Expense recoreded: $\(amount)")
    }
    
}

//let tracker = BudgetTracker()


//tracker.addIncome(500.0)
//tracker.addExpense(100.0)
//tracker.addExpense(600.0)
//Codable - easy encoding / decoding of JSON data

//model class with Codable protocol
struct Post: Codable {
    let title: String
    let body : String
}

//contruct the url


let url = URL(string: "https://jsonplaceholder.typicode.com/posts/1")!

//create publisher with url,
//start the request, made
let publisher = URLSession.shared.dataTaskPublisher(for: url)
    .map(\.data) //fetch the response
    .decode(type: Post.self, decoder: JSONDecoder()) //decode in the form of Post type, using the JSONDecoder

//call the subcriber, fetching data from the pubhisher
let subscription = publisher.sink(receiveCompletion: { print($0) },
                                  receiveValue: { print($0.title + $0.body) })





