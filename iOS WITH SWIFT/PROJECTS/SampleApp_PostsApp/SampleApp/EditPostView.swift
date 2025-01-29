//
//  EditPostView.swift
//  SampleApp
//
//  Created by admin on 29/01/25.
//

import SwiftUI

struct EditPostView: View {
    @Environment(\.managedObjectContext) private var viewContext
    @Environment(\.dismiss) private var dismiss

    @ObservedObject var post: Posts
    @State private var title = ""
    @State private var desc = ""

    var body: some View {
        NavigationView {
            Form {
                Section(header: Text("Edit Title")) {
                    TextField("Edit post title", text: $title)
                    TextField("Edit post desc", text: $desc)
                }
            }
            .navigationBarTitle("Edit Post", displayMode: .inline)
            .navigationBarItems(
                leading: Button("Cancel") {
                    dismiss()
                },
                trailing: Button("Update") {
                    post.title = title
                    post.desc = desc
                    saveContext()
                    dismiss()
                }.disabled(title.isEmpty)
            )
            .onAppear {
                title = post.title ?? ""
                desc = post.desc ?? ""
            }
        }
    }

    private func saveContext() {
        do {
            try viewContext.save()
        } catch {
            print("Error saving context: \(error)")
        }
    }
}
