package com.project.textapp.service.impl;
import com.project.textapp.service.TextStatisticsService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class TextStatisticsServiceImpl implements TextStatisticsService {
    private static final Pattern LETTER = Pattern.compile("[aA]");
    public long countLetters(String text) {
        return count(LETTER, text);
    }
    public long countSymbols(String text) {
        return text.codePointCount(0, text.length());
    }
    private long count(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        long count = 0;
        while (matcher.find()) count++;
        return count;
    }
}
