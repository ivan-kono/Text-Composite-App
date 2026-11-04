package com.project.textapp.service;

import java.util.regex.Pattern;

public interface TextStatisticsService {
    long countSymbols(String text);
    long countCharacters(String text);
}
