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
 * struct in_pktinfo {
 *     int ipi_ifindex;
 *     struct in_addr ipi_spec_dst;
 *     struct in_addr ipi_addr;
 * }
 * }
 */
public class in_pktinfo {

    in_pktinfo() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        nss_proto_h.C_INT.withName("ipi_ifindex"),
        in_addr.layout().withName("ipi_spec_dst"),
        in_addr.layout().withName("ipi_addr")
    ).withName("in_pktinfo");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    private static final OfInt ipi_ifindex$LAYOUT = (OfInt)$LAYOUT.select(groupElement("ipi_ifindex"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int ipi_ifindex
     * }
     */
    public static final OfInt ipi_ifindex$layout() {
        return ipi_ifindex$LAYOUT;
    }

    private static final long ipi_ifindex$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int ipi_ifindex
     * }
     */
    public static final long ipi_ifindex$offset() {
        return ipi_ifindex$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int ipi_ifindex
     * }
     */
    public static int ipi_ifindex(MemorySegment struct) {
        return struct.get(ipi_ifindex$LAYOUT, ipi_ifindex$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int ipi_ifindex
     * }
     */
    public static void ipi_ifindex(MemorySegment struct, int fieldValue) {
        struct.set(ipi_ifindex$LAYOUT, ipi_ifindex$OFFSET, fieldValue);
    }

    private static final GroupLayout ipi_spec_dst$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("ipi_spec_dst"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * struct in_addr ipi_spec_dst
     * }
     */
    public static final GroupLayout ipi_spec_dst$layout() {
        return ipi_spec_dst$LAYOUT;
    }

    private static final long ipi_spec_dst$OFFSET = 4;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * struct in_addr ipi_spec_dst
     * }
     */
    public static final long ipi_spec_dst$offset() {
        return ipi_spec_dst$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * struct in_addr ipi_spec_dst
     * }
     */
    public static MemorySegment ipi_spec_dst(MemorySegment struct) {
        return struct.asSlice(ipi_spec_dst$OFFSET, ipi_spec_dst$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * struct in_addr ipi_spec_dst
     * }
     */
    public static void ipi_spec_dst(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, ipi_spec_dst$OFFSET, ipi_spec_dst$LAYOUT.byteSize());
    }

    private static final GroupLayout ipi_addr$LAYOUT = (GroupLayout)$LAYOUT.select(groupElement("ipi_addr"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * struct in_addr ipi_addr
     * }
     */
    public static final GroupLayout ipi_addr$layout() {
        return ipi_addr$LAYOUT;
    }

    private static final long ipi_addr$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * struct in_addr ipi_addr
     * }
     */
    public static final long ipi_addr$offset() {
        return ipi_addr$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * struct in_addr ipi_addr
     * }
     */
    public static MemorySegment ipi_addr(MemorySegment struct) {
        return struct.asSlice(ipi_addr$OFFSET, ipi_addr$LAYOUT.byteSize());
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * struct in_addr ipi_addr
     * }
     */
    public static void ipi_addr(MemorySegment struct, MemorySegment fieldValue) {
        MemorySegment.copy(fieldValue, 0L, struct, ipi_addr$OFFSET, ipi_addr$LAYOUT.byteSize());
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

