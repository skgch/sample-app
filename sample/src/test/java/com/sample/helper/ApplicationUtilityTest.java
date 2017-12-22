package com.sample.helper;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ApplicationUtilityTest {

    @Test
    public void testFullTitle() {
        ApplicationUtility appUtil = new ApplicationUtility();
        assertThat(appUtil.fullTitle(""), is("Spring Boot Sample App"));
        assertThat(appUtil.fullTitle("Help"), is("Help | Spring Boot Sample App"));
    }

}
