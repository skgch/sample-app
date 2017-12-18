package com.sample.helper;

public class ApplicationUtility {

    public String fullTitle(String title) {
        String baseTitle = "Spring Boot Sample App";
        if(title == null || title.length() == 0) {
            return baseTitle;
        } else {
            return title + " | " + baseTitle;
        }
    }

}
