package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class KommuneControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetKommunerByRegion() throws Exception {
        String regionskode = "1084";
        mockMvc.perform(get("/kommuner")
                        .param("regionskode", regionskode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].region.kode", everyItem(equalTo(regionskode))));

    }

}
