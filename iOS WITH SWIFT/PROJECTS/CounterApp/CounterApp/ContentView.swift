//
//  ContentView.swift
//  CounterApp
//
//  Created by admin on 25/01/25.
//

import SwiftUI

struct ContentView: View {
    @State private var count = 0 // State variable to track clicks

    var body: some View {
        VStack(spacing: 20) {
            Text("Click Counter")
                .font(.largeTitle)
                .fontWeight(.bold)

            Text("Count: \(count)")
                .font(.title)

            Button(action: {
                count += 1 // Increment count on button click
            }) {
                Text("Tap Me")
                    .font(.title2)
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }

            Button(action: {
                count = 0 // Reset count
            }) {
                Text("Reset")
                    .font(.title2)
                    .padding()
                    .background(Color.red)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
