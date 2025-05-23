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
 * struct pam_conv {
 *     int (*conv)(int, const struct pam_message **, struct pam_response **, void *);
 *     void *appdata_ptr;
 * }
 * }
 */
public class pam_conv {

    pam_conv() {
        // Should not be called directly
    }

    private static final GroupLayout $LAYOUT = MemoryLayout.structLayout(
        nss_proto_h.C_POINTER.withName("conv"),
        nss_proto_h.C_POINTER.withName("appdata_ptr")
    ).withName("pam_conv");

    /**
     * The layout of this struct
     */
    public static final GroupLayout layout() {
        return $LAYOUT;
    }

    /**
     * {@snippet lang=c :
     * int (*conv)(int, const struct pam_message **, struct pam_response **, void *)
     * }
     */
    public static class conv {

        conv() {
            // Should not be called directly
        }

        /**
         * The function pointer signature, expressed as a functional interface
         */
        public interface Function {
            int apply(int _x0, MemorySegment _x1, MemorySegment _x2, MemorySegment _x3);
        }

        private static final FunctionDescriptor $DESC = FunctionDescriptor.of(
            nss_proto_h.C_INT,
            nss_proto_h.C_INT,
            nss_proto_h.C_POINTER,
            nss_proto_h.C_POINTER,
            nss_proto_h.C_POINTER
        );

        /**
         * The descriptor of this function pointer
         */
        public static FunctionDescriptor descriptor() {
            return $DESC;
        }

        private static final MethodHandle UP$MH = nss_proto_h.upcallHandle(conv.Function.class, "apply", $DESC);

        /**
         * Allocates a new upcall stub, whose implementation is defined by {@code fi}.
         * The lifetime of the returned segment is managed by {@code arena}
         */
        public static MemorySegment allocate(conv.Function fi, Arena arena) {
            return Linker.nativeLinker().upcallStub(UP$MH.bindTo(fi), $DESC, arena);
        }

        private static final MethodHandle DOWN$MH = Linker.nativeLinker().downcallHandle($DESC);

        /**
         * Invoke the upcall stub {@code funcPtr}, with given parameters
         */
        public static int invoke(MemorySegment funcPtr,int _x0, MemorySegment _x1, MemorySegment _x2, MemorySegment _x3) {
            try {
                return (int) DOWN$MH.invokeExact(funcPtr, _x0, _x1, _x2, _x3);
            } catch (Throwable ex$) {
                throw new AssertionError("should not reach here", ex$);
            }
        }
    }

    private static final AddressLayout conv$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("conv"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * int (*conv)(int, const struct pam_message **, struct pam_response **, void *)
     * }
     */
    public static final AddressLayout conv$layout() {
        return conv$LAYOUT;
    }

    private static final long conv$OFFSET = 0;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * int (*conv)(int, const struct pam_message **, struct pam_response **, void *)
     * }
     */
    public static final long conv$offset() {
        return conv$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * int (*conv)(int, const struct pam_message **, struct pam_response **, void *)
     * }
     */
    public static MemorySegment conv(MemorySegment struct) {
        return struct.get(conv$LAYOUT, conv$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * int (*conv)(int, const struct pam_message **, struct pam_response **, void *)
     * }
     */
    public static void conv(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(conv$LAYOUT, conv$OFFSET, fieldValue);
    }

    private static final AddressLayout appdata_ptr$LAYOUT = (AddressLayout)$LAYOUT.select(groupElement("appdata_ptr"));

    /**
     * Layout for field:
     * {@snippet lang=c :
     * void *appdata_ptr
     * }
     */
    public static final AddressLayout appdata_ptr$layout() {
        return appdata_ptr$LAYOUT;
    }

    private static final long appdata_ptr$OFFSET = 8;

    /**
     * Offset for field:
     * {@snippet lang=c :
     * void *appdata_ptr
     * }
     */
    public static final long appdata_ptr$offset() {
        return appdata_ptr$OFFSET;
    }

    /**
     * Getter for field:
     * {@snippet lang=c :
     * void *appdata_ptr
     * }
     */
    public static MemorySegment appdata_ptr(MemorySegment struct) {
        return struct.get(appdata_ptr$LAYOUT, appdata_ptr$OFFSET);
    }

    /**
     * Setter for field:
     * {@snippet lang=c :
     * void *appdata_ptr
     * }
     */
    public static void appdata_ptr(MemorySegment struct, MemorySegment fieldValue) {
        struct.set(appdata_ptr$LAYOUT, appdata_ptr$OFFSET, fieldValue);
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

