//
//  CustomModule.swift
//  TestIconChange
//
//  Created by Илья Белоусов on 07.06.2023.
//

import Foundation;

@objc(CustomMethods) class CustomMethods: NSObject {
  @objc static func requiresMainQueueSetup() -> Bool { return true }
  
  
  
  @objc public  func switchStartUI(
    _ iconName: String
  ){
    
    UIApplication.shared.setAlternateIconName(iconName)
    
  }
}
 
