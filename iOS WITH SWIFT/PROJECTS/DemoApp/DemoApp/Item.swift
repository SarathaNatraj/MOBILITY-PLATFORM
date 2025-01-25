//
//  Item.swift
//  DemoApp
//
//  Created by admin on 22/01/25.
//

import Foundation

struct Task: Identifiable {
    var id = UUID()
    var title: String
    var isCompleted: Bool = false
}
