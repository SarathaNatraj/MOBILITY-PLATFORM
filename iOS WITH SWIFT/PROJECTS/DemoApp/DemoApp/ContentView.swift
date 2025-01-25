//
//  ContentView.swift
//  DemoApp
//
//  Created by admin on 22/01/25.
//
import SwiftUI

struct ContentView: View {
    @ObservedObject var viewModel = TaskListViewModel()
    
    @State private var newTaskTitle: String = ""
    
    var body: some View {
        NavigationView {
            VStack {
                // Input to add new tasks
                HStack {
                    TextField("Enter task", text: $newTaskTitle)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding()
                    
                    Button(action: addTask) {
                        Text("Add")
                            .padding()
                            .background(Color.blue)
                            .foregroundColor(.white)
                            .cornerRadius(8)
                    }
                }
                
                // List of tasks
                List {
                    ForEach(viewModel.tasks) { task in
                        HStack {
                            Text(task.title)
                                .strikethrough(task.isCompleted, color: .gray)
                            
                            Spacer()
                            
                            // Mark task as completed
                            Button(action: { toggleCompletion(for: task) }) {
                                Image(systemName: task.isCompleted ? "checkmark.circle.fill" : "circle")
                                    .foregroundColor(.green)
                            }
                        }
                    }
                    .onDelete(perform: deleteTask)
                }
                .navigationTitle("To-Do List")
                .toolbar {
                    EditButton()
                }
            }
            .padding()
        }
    }
    
    // MARK: - Actions
    
    func addTask() {
        guard !newTaskTitle.isEmpty else { return }
        viewModel.addTask(title: newTaskTitle)
        newTaskTitle = ""
    }
    
    func deleteTask(at offsets: IndexSet) {
        viewModel.deleteTask(at: offsets)
    }
    
    func toggleCompletion(for task: Task) {
        viewModel.toggleCompletion(for: task)
    }
}	

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
