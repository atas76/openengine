package org.mpn;

import java.util.ArrayList;
import java.util.List;

public class Lexan {

    public List<String> scan(String expression) throws Exception {

        List<String> tokens = new ArrayList<>();
        int index = 0;
        if (Character.isAlphabetic(expression.charAt(0))) {
            while (index < expression.length() && Character.isAlphabetic(expression.charAt(index))) {
                ++index;
            }
            tokens.add(expression.substring(0, index));
        }
        if (expression.charAt(0) == ':') {
            ++index;
            tokens.add(String.valueOf(expression.charAt(0)));
        }
        if (Character.isWhitespace(expression.charAt(0))) {
            while (Character.isWhitespace(expression.charAt(index))) {
                ++index;
            }
        }
        if (Character.isDigit(expression.charAt(0))) {
            while (Character.isDigit(expression.charAt(index))) {
                ++index;
            }
            tokens.add(expression.substring(0, index));
        }

        if (expression.charAt(0) == '-') {
            if (expression.charAt(1) == '>') {
                tokens.add("->");
                index += 2;
            } else {
                throw new RuntimeException("Syntax error: expected '->'");
            }
        }
        if (expression.charAt(0) == '=') {
            if (expression.charAt(1) == '>') {
                tokens.add("=>");
                index += 2;
            } else {
                throw new RuntimeException("Syntax error: expected '=>'");
            }
        }
        String tail = expression.substring(index);
        if (index > 0 && index < expression.length()) tokens.addAll(scan(tail));
        return tokens;
    }
}
