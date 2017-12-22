package com.sample.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StaticPagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String baseTitle = "Spring Boot Sample App";

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("text/html;charset=UTF-8"))
        .andExpect(view().name("staticPages/home"))
        .andExpect(xpath("/html/head/title").string(baseTitle));
    }

    @Test
    public void testHelp() throws Exception {
        mockMvc.perform(get("/help")).andExpect(status().isOk())
        .andExpect(content().contentType("text/html;charset=UTF-8"))
        .andExpect(view().name("staticPages/help"))
        .andExpect(xpath("/html/head/title").string("Help | " + baseTitle));
    }

    @Test
    public void getAbout() throws Exception {
        mockMvc.perform(get("/about")).andExpect(status().isOk())
        .andExpect(content().contentType("text/html;charset=UTF-8"))
        .andExpect(view().name("staticPages/about"))
        .andExpect(xpath("/html/head/title").string("About | " + baseTitle));
    }

    @Test
    public void getContact() throws Exception {
        mockMvc.perform(get("/contact")).andExpect(status().isOk())
        .andExpect(content().contentType("text/html;charset=UTF-8"))
        .andExpect(view().name("staticPages/contact"))
        .andExpect(xpath("/html/head/title").string("Contact | " + baseTitle));
    }

}
