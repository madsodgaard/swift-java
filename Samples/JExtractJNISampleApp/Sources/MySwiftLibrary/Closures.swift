//===----------------------------------------------------------------------===//
//
// This source file is part of the Swift.org open source project
//
// Copyright (c) 2025 Apple Inc. and the Swift.org project authors
// Licensed under Apache License v2.0
//
// See LICENSE.txt for license information
// See CONTRIBUTORS.txt for the list of Swift.org project authors
//
// SPDX-License-Identifier: Apache-2.0
//
//===----------------------------------------------------------------------===//

public func emptyClosure(closure: () -> ()) {
  closure()
}

public func closureWithInt(input: Int64, closure: (Int64) -> Int64) -> Int64 {
  return closure(input)
}

public func closureMultipleArguments(
  input1: Int64,
  input2: Int64,
  closure: (Int64, Int64) -> Int64
) -> Int64 {
  return closure(input1, input2)
}


