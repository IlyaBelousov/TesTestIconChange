//
//  CustomModule.m
//  TestIconChange
//
//  Created by Илья Белоусов on 07.06.2023.
//
#import "React/RCTBridgeModule.h"
#import <Foundation/Foundation.h>

@interface RCT_EXTERN_MODULE(CustomMethods, NSObject)
  RCT_EXTERN_METHOD(simpleMethod)
  RCT_EXTERN_METHOD(simpleMethodReturns:
    (RCTResponseSenderBlock) callback
  )
  RCT_EXTERN_METHOD(simpleMethodWithParams:
    (NSString *) param
    callback: (RCTResponseSenderBlock)callback
  )
  RCT_EXTERN_METHOD(switchStartUI:
  (NSString *) iconName
  )
  RCT_EXTERN_METHOD(
    resolvePromise: (RCTPromiseResolveBlock) resolve
    rejecter: (RCTPromiseRejectBlock) reject
  )
  RCT_EXTERN_METHOD(rejectPromise:
    (RCTPromiseResolveBlock) resolve
    rejecter: (RCTPromiseRejectBlock) reject
  )
@end
