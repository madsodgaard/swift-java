//===----------------------------------------------------------------------===//
//
// This source file is part of the Swift.org open source project
//
// Copyright (c) 2024 Apple Inc. and the Swift.org project authors
// Licensed under Apache License v2.0
//
// See LICENSE.txt for license information
// See CONTRIBUTORS.txt for the list of Swift.org project authors
//
// SPDX-License-Identifier: Apache-2.0
//
//===----------------------------------------------------------------------===//

import JavaKitConfigurationShared // TODO: this should become SwiftJavaConfigurationShared
import JavaTypes // TODO: this should become SwiftJavaConfigurationShared

extension Configuration {
  public var effectiveUnsignedNumericsMode: UnsignedNumericsMode {
    switch effectiveUnsignedNumbersMode {
    case .annotate: .ignoreSign
    case .wrapGuava: .wrapUnsignedGuava
    }
  }
}