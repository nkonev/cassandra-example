package org.example.utils;

import java.nio.ByteBuffer;

public abstract class PageUtils {
    private static final char[] HEX_CODE = "0123456789ABCDEF".toCharArray();

    public static String toString(ByteBuffer buffer) {
        final StringBuilder r = new StringBuilder(buffer.remaining() * 2);
        while (buffer.hasRemaining()) {
            final byte b = buffer.get();
            r.append(HEX_CODE[(b >> 4) & 0xF]);
            r.append(HEX_CODE[(b & 0xF)]);
        }
        return r.toString();
    }

    public static ByteBuffer fromString(String pageState) {
        int len = pageState.length();
        ByteBuffer bytes = ByteBuffer.allocate(len / 2);

        for (int i = 0; i < len; i += 2) {
            bytes.put((byte) ((Character.digit(pageState.charAt(i), 16) << 4)
                    + Character.digit(pageState.charAt(i+1), 16)));
        }

        bytes.position(0);
        return bytes;
    }
}
