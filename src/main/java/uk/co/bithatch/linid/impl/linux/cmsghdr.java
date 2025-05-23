/*
 * Copyright © 2025 Bithatch (tanktarta@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the “Software”), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
// Generated by jextract

package uk.co.bithatch.linid.impl.linux;

import java.lang.invoke.*;
import java.lang.foreign.*;
import java.nio.ByteOrder;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.MemoryLayout.PathElement.*;

/**
 * {@snippet lang=c :
 * struct cmsghdr {
 *     size_t cmsg_len;
 *     int cmsg_level;
 *     int cmsg_type;
 *     unsigned char __cmsg_data[];
 * }
 * }
 */
public class cmsghdr {

    cmsghdr() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        nss_proto_h.C_LONG.withName("cmsg_len"),
        nss_proto_h.C_INT.withName("cmsg_level"),
        nss_proto_h.C_INT.withName("cmsg_type"),
        MemoryLayout.sequenceLayout(0, nss_proto_h.C_CHAR).withName("__cmsg_data")
    ).withName("cmsghdr");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfLong cmsg_len$LAYOUT = (OfLong)$LAYOUT.select(groupElement("cmsg_len"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * size_t cmsg_len
     * }
     */
    public static final OfLong cmsg_len$layout() {
        return cmsg_len$LAYOUT;
    }

    private static final long cmsg_len$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * size_t cmsg_len
     * }
     */
    public static final long cmsg_len$offset() {
        return cmsg_len$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * size_t cmsg_len
     * }
     */
    public static long cmsg_len(MemorySegment struct) {
        return struct.get(cmsg_len$LAYOUT, cmsg_len$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * size_t cmsg_len
     * }
     */
    public static void cmsg_len(MemorySegment struct, long fieldValue) {
        struct.set(cmsg_len$LAYOUT, cmsg_len$OFFSET, fieldValue);
    }

    private static final OfInt cmsg_level$LAYOUT = (OfInt)$LAYOUT.select(groupElement("cmsg_level"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int cmsg_level
     * }
     */
    public static final OfInt cmsg_level$layout() {
        return cmsg_level$LAYOUT;
    }

    private static final long cmsg_level$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int cmsg_level
     * }
     */
    public static final long cmsg_level$offset() {
        return cmsg_level$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int cmsg_level
     * }
     */
    public static int cmsg_level(MemorySegment struct) {
        return struct.get(cmsg_level$LAYOUT, cmsg_level$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int cmsg_level
     * }
     */
    public static void cmsg_level(MemorySegment struct, int fieldValue) {
        struct.set(cmsg_level$LAYOUT, cmsg_level$OFFSET, fieldValue);
    }

    private static final OfInt cmsg_type$LAYOUT = (OfInt)$LAYOUT.select(groupElement("cmsg_type"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int cmsg_type
     * }
     */
    public static final OfInt cmsg_type$layout() {
        return cmsg_type$LAYOUT;
    }

    private static final long cmsg_type$OFFSET = 12;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int cmsg_type
     * }
     */
    public static final long cmsg_type$offset() {
        return cmsg_type$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int cmsg_type
     * }
     */
    public static int cmsg_type(MemorySegment struct) {
        return struct.get(cmsg_type$LAYOUT, cmsg_type$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int cmsg_type
     * }
     */
    public static void cmsg_type(MemorySegment struct, int fieldValue) {
        struct.set(cmsg_type$LAYOUT, cmsg_type$OFFSET, fieldValue);
    }

    private static final SequenceLayout __cmsg_data$LAYOUT = (SequenceLayout)$LAYOUT.select(groupElement("__cmsg_data"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * unsigned char __cmsg_data[]
     * }
     */
    public static final SequenceLayout __cmsg_data$layout() {
        return __cmsg_data$LAYOUT;
    }

    private static final long __cmsg_data$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * unsigned char __cmsg_data[]
     * }
     */
    public static final long __cmsg_data$offset() {
        return __cmsg_data$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * unsigned char __cmsg_data[]
     * }
     */
    public static MemorySegment __cmsg_data(MemorySegment struct) {
        return struct.asSlice(__cmsg_data$OFFSET, __cmsg_data$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * unsigned char __cmsg_data[]
     * }
     */
    public static void __cmsg_data(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, __cmsg_data$OFFSET, __cmsg_data$LAYOUT.byteSize());
    }

    /**
     * Obtains a slice of {@code arrayParam} which selects the array element at {@code index}.
     * The returned segment has address {@code arrayParam.address() + index * layout().byteSize()}
     */
    public static MemorySegment asSlice(MemorySegment array, long index) {
        return array.asSlice(layout().byteSize() * index);
    }

    /**
     * The size (in bytes) of this struct
     */
    public static long sizeof() { return layout().byteSize(); }

    /**
     * Allocate a segment of size {@code layout().byteSize()} using {@code allocator}
     */
    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate(layout());
    }

    /**
     * Allocate an array of size {@code elementCount} using {@code allocator}.
     * The returned segment has size {@code elementCount * layout().byteSize()}.
     */
    public static MemorySegment allocateArray(long elementCount, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(elementCount, layout()));
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, Arena arena, Consumer<MemorySegment> cleanup) {
        return reinterpret(addr, 1, arena, cleanup);
    }

    /**
     * Reinterprets {@code addr} using target {@code arena} and {@code cleanupAction} (if any).
     * The returned segment has size {@code elementCount * layout().byteSize()}
     */
    public static MemorySegment reinterpret(MemorySegment addr, long elementCount, Arena arena, Consumer<MemorySegment> cleanup) {
        return addr.reinterpret(layout().byteSize() * elementCount, arena, cleanup);
    }
}

