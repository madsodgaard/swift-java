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

package org.swift.swiftkit.ffm;

import org.swift.swiftkit.core.SwiftInstance;
import org.swift.swiftkit.core.SwiftInstanceCleanup;

import java.lang.foreign.MemorySegment;

public abstract class FFMSwiftInstance extends SwiftInstance {
    private final MemorySegment memorySegment;

    /**
     * The designated constructor of any imported Swift types.
     *
     * @param segment the memory segment.
     * @param arena the arena this object belongs to. When the arena goes out of scope, this value is destroyed.
     */
    protected FFMSwiftInstance(MemorySegment segment, AllocatingSwiftArena arena) {
        this.memorySegment = segment;

        // Only register once we have fully initialized the object since this will need the object pointer.
        arena.register(this);
    }

    /**
     * The pointer to the instance in memory. I.e. the {@code self} of the Swift object or value.
     */
    public final MemorySegment $memorySegment() {
        return this.memorySegment;
    }

    @Override
    public long $memoryAddress() {
        return $memorySegment().address();
    }

    /**
     * The Swift type metadata of this type.
     */
    public abstract SwiftAnyType $swiftType();


    @Override
    public SwiftInstanceCleanup $createCleanup() {
        var statusDestroyedFlag = $statusDestroyedFlag();
        Runnable markAsDestroyed = () -> statusDestroyedFlag.set(true);

        return new FFMSwiftInstanceCleanup(
                $memorySegment(),
                $swiftType(),
                markAsDestroyed
        );
    }


    /**
     * Returns `true` if this swift instance is a reference type, i.e. a `class` or (`distributed`) `actor`.
     *
     * @return `true` if this instance is a reference type, `false` otherwise.
     */
    public boolean isReferenceType() {
        return this instanceof SwiftHeapObject;
    }
}
