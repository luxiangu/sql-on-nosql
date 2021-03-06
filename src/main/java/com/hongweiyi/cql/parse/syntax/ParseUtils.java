package com.hongweiyi.cql.parse.syntax;

import com.hongweiyi.cql.exception.SyntaxException;

public class ParseUtils {
    public static String unescapeId(String id)
            throws SyntaxException {
        if (id == null) {
            throw new SyntaxException("ID is null.");
        }

        int len = id.length();
        if (len == 0) {
            throw new SyntaxException("ID is empty.");
        }

        if (id.charAt(0) == '`') {
            return id.substring(1, len - 1);
        } else {
            return id;
        }
    }

    public static String unescapeString(String str)
            throws SyntaxException {
        if (str == null) {
            throw new SyntaxException("String is null.");
        }

        int len = str.length();
        if (len < 2) {
            throw new SyntaxException("String is not quoted.");
        }

        char c;
        boolean escape = false;
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < len - 1; ++i) {
            c = str.charAt(i);
            switch (c) {
                case '\\':
                    if (escape) {
                        builder.append('\\');
                        escape = false;
                    } else {
                        escape = true;
                    }
                    break;
                case 'r':
                    if (escape) {
                        builder.append('\r');
                        escape = false;
                    } else {
                        builder.append(c);
                    }
                    break;
                case 'n':
                    if (escape) {
                        builder.append('\n');
                        escape = false;
                    } else {
                        builder.append(c);
                    }
                    break;
                case 't':
                    if (escape) {
                        builder.append('\t');
                        escape = false;
                    } else {
                        builder.append(c);
                    }
                    break;
                default:
                    if (escape) {
                        throw new SyntaxException("Unknown escape sequence: " + c);
                    } else {
                        builder.append(c);
                    }
                    break;
            }
        }

        return builder.toString();
    }
}
