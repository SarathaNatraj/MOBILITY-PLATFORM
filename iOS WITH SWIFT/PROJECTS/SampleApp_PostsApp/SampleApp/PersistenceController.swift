//
//  PersistenceController.swift
//  SampleApp
//
//  Created by admin on 29/01/25.
//

import CoreData
struct PersistenceController {
    //
     static  let shared = PersistenceController()
    
    let container : NSPersistentContainer
    
    
    //init
    init(inMemory : Bool = false) {
        //initialize the container with your app name -> AppDatabase
        container = NSPersistentContainer(name: "SampleApp")
        
       // container = NSPersistentCloudKitContainer(name: <#T##String#>)
        if inMemory{
            container.persistentStoreDescriptions.first?.url = URL(fileURLWithPath: "/dev/null")
        }
        
        container.loadPersistentStores{_, error in
            if let error  = error as NSError? {
                fatalError("Unresolved Error: \(error), \(error.userInfo)")
            }
        }
          
        
    }
    
    func saveContext(){
        let context = container.viewContext
        if context.hasChanges{
            try? context.save()
        }
    }
}
