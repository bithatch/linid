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
 * struct argp_option {
 *     const char *name;
 *     int key;
 *     const char *arg;
 *     int flags;
 *     const char *doc;
 *     int group;
 * }
 * }
 */
public class argp_option {

    argp_option() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        nss_proto_h.C_POINTER.withName("name"),
        nss_proto_h.C_INT.withName("key"),
        MemoryLayout.paddingLayout(4),
        nss_proto_h.C_POINTER.withName("arg"),
        nss_proto_h.C_INT.withName("flags"),
        MemoryLayout.paddingLayout(4),
        nss_proto_h.C_POINTER.withName("doc"),
        nss_proto_h.C_INT.withName("group"),
        MemoryLayout.paddingLayout(4)
    ).withName("argp_option");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final AddressLayout name$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("name"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *name
     * }
     */
    public static final AddressLayout name$layout() {
        return name$LAYOUT;
    }

    private static final long name$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *name
     * }
     */
    public static final long name$offset() {
        return name$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *name
     * }
     */
    public static MemorySegment name(MemorySegment struct) {
        return struct.get(name$LAYOUT, name$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *name
     * }
     */
    public static void name(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(name$LAYOUT, name$OFFSET, fieldValue);
    }

    private static final OfInt key$LAYOUT = (OfInt)$LAYOUT.select(groupElement("key"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int key
     * }
     */
    public static final OfInt key$layout() {
        return key$LAYOUT;
    }

    private static final long key$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int key
     * }
     */
    public static final long key$offset() {
        return key$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int key
     * }
     */
    public static int key(MemorySegment struct) {
        return struct.get(key$LAYOUT, key$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int key
     * }
     */
    public static void key(MemorySegment struct, int fieldValue) {
        struct.set(key$LAYOUT, key$OFFSET, fieldValue);
    }

    private static final AddressLayout arg$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("arg"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *arg
     * }
     */
    public static final AddressLayout arg$layout() {
        return arg$LAYOUT;
    }

    private static final long arg$OFFSET = 16;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *arg
     * }
     */
    public static final long arg$offset() {
        return arg$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *arg
     * }
     */
    public static MemorySegment arg(MemorySegment struct) {
        return struct.get(arg$LAYOUT, arg$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *arg
     * }
     */
    public static void arg(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(arg$LAYOUT, arg$OFFSET, fieldValue);
    }

    private static final OfInt flags$LAYOUT = (OfInt)$LAYOUT.select(groupElement("flags"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int flags
     * }
     */
    public static final OfInt flags$layout() {
        return flags$LAYOUT;
    }

    private static final long flags$OFFSET = 24;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int flags
     * }
     */
    public static final long flags$offset() {
        return flags$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int flags
     * }
     */
    public static int flags(MemorySegment struct) {
        return struct.get(flags$LAYOUT, flags$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int flags
     * }
     */
    public static void flags(MemorySegment struct, int fieldValue) {
        struct.set(flags$LAYOUT, flags$OFFSET, fieldValue);
    }

    private static final AddressLayout doc$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("doc"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * const char *doc
     * }
     */
    public static final AddressLayout doc$layout() {
        return doc$LAYOUT;
    }

    private static final long doc$OFFSET = 32;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * const char *doc
     * }
     */
    public static final long doc$offset() {
        return doc$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * const char *doc
     * }
     */
    public static MemorySegment doc(MemorySegment struct) {
        return struct.get(doc$LAYOUT, doc$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * const char *doc
     * }
     */
    public static void doc(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(doc$LAYOUT, doc$OFFSET, fieldValue);
    }

    private static final OfInt group$LAYOUT = (OfInt)$LAYOUT.select(groupElement("group"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int group
     * }
     */
    public static final OfInt group$layout() {
        return group$LAYOUT;
    }

    private static final long group$OFFSET = 40;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int group
     * }
     */
    public static final long group$offset() {
        return group$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int group
     * }
     */
    public static int group(MemorySegment struct) {
        return struct.get(group$LAYOUT, group$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int group
     * }
     */
    public static void group(MemorySegment struct, int fieldValue) {
        struct.set(group$LAYOUT, group$OFFSET, fieldValue);
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

