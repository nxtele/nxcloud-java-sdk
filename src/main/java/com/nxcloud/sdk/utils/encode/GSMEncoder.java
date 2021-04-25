package com.nxcloud.sdk.utils.encode;

import java.io.ByteArrayOutputStream;

public class GSMEncoder {
    public static final int EXTENDED_ESCAPE = 27;
    public static final int PAGE_BREAK = 10;
    public static final char[] CHAR_TABLE = new char[]{'@', '£', '$', '¥', 'è', 'é', 'ù', 'ì', 'ò', 'Ç', '\n', 'Ø', 'ø', '\r', 'Å', 'å', 'Δ', '_', 'Φ', 'Γ', 'Λ', 'Ω', 'Π', 'Ψ', 'Σ', 'Θ', 'Ξ', ' ', 'Æ', 'æ', 'ß', 'É', ' ', '!', '"', '#', '¤', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '¡', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ä', 'Ö', 'Ñ', 'Ü', '§', '¿', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ä', 'ö', 'ñ', 'ü', 'à'};
    public static final char[] EXT_CHAR_TABLE = new char[]{'\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\f', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '^', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '{', '}', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\\', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '[', '~', ']', '\u0000', '|', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '€', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000', '\u0000'};

    public static boolean canRepresent(CharSequence str0) {
        if (str0 == null) {
            return true;
        } else {
            int len = str0.length();

            for (int i = 0; i < len; ++i) {
                char c = str0.charAt(i);
                if ((c < ' ' || c > '_') && (c < 'a' || c > '~')) {
                    switch (c) {
                        case '\n':
                        case '\f':
                        case '\r':
                        case '¡':
                        case '£':
                        case '¤':
                        case '¥':
                        case '§':
                        case '¿':
                        case 'Ä':
                        case 'Å':
                        case 'Æ':
                        case 'Ç':
                        case 'É':
                        case 'Ñ':
                        case 'Ö':
                        case 'Ø':
                        case 'Ü':
                        case 'ß':
                        case 'à':
                        case 'ä':
                        case 'å':
                        case 'æ':
                        case 'è':
                        case 'é':
                        case 'ì':
                        case 'ñ':
                        case 'ò':
                        case 'ö':
                        case 'ø':
                        case 'ù':
                        case 'ü':
                        case 'Γ':
                        case 'Δ':
                        case 'Θ':
                        case 'Λ':
                        case 'Ξ':
                        case 'Π':
                        case 'Σ':
                        case 'Φ':
                        case 'Ψ':
                        case 'Ω':
                        case '€':
                            break;
                        default:
                            return false;
                    }
                }
            }

            return true;
        }
    }

    public int estimateEncodeByteLength(CharSequence str0) {
        return str0 == null ? 0 : str0.length() + 10;
    }

    public byte[] encode(CharSequence str0) {
        if (str0 == null) {
            return null;
        } else {
            int estimatedByteLength = this.estimateEncodeByteLength(str0);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(estimatedByteLength);

            int len = str0.length();

            for (int i = 0; i < len; ++i) {
                int search = 0;

                for (char c = str0.charAt(i); search < CHAR_TABLE.length; ++search) {
                    if (search != 27) {
                        if (c == CHAR_TABLE[search]) {
                            baos.write(search);
                            break;
                        }

                        if (c == EXT_CHAR_TABLE[search]) {
                            baos.write(27);
                            baos.write(search);
                            break;
                        }
                    }
                }

                if (search == CHAR_TABLE.length) {
                    baos.write(63);
                }
            }

            return baos.toByteArray();
        }
    }

    public int estimateDecodeCharLength(byte[] bytes) {
        if (bytes == null) {
            return 0;
        } else {
            return bytes.length < 2 ? bytes.length : bytes.length + 10;
        }
    }

    public void decode(byte[] bytes, StringBuilder buffer) {
        if (bytes != null) {
            char[] table = CHAR_TABLE;

            for (int i = 0; i < bytes.length; ++i) {
                int code = bytes[i] & 255;
                if (code == 27) {
                    table = EXT_CHAR_TABLE;
                } else {
                    buffer.append(code >= table.length ? '?' : table[code]);
                    table = CHAR_TABLE;
                }
            }

        }
    }
}
