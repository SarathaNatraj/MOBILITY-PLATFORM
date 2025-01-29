//
//  AddPostView.swift
//  SampleApp
//
//  Created by admin on 29/01/25.
//

import SwiftUI
struct AddPostView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @Environment(\.dismiss) private var dismiss
    @State private var title = ""
    @State private var desc = ""
    

    var body: some View {
        NavigationView {
            Form {
                Section(header: Text("Post Title")) {
                    TextField("Enter post title", text: $title)
                    TextField("Enter post desc", text:$desc)
                    
                }
            }
            .navigationBarTitle("Add New Post", displayMode: .inline)
            .navigationBarItems(
                leading: Button("Cancel") {
                    dismiss()
                },
                trailing: Button("Save") {
                    addPost()
                    dismiss()
                }.disabled(title.isEmpty)
            )
        }
    }

    private func addPost() {
        let newPost = Posts(context: viewContext)
    
        newPost.title = title
        newPost.desc = desc

        do {
            try viewContext.save()
        } catch {
            print("Error saving post: \(error)")
        }
    }
}

struct AddPostView_Previews: PreviewProvider {
    static var previews: some View {
        AddPostView()
    }
}
