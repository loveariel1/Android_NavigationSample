package com.ahsiu.navigationsample;

import android.content.SearchRecentSuggestionsProvider;

public class SuggestionProvider extends SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = "com.ahsiu.navigationsample.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }

}
