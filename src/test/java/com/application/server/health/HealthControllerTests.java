package com.application.server.health;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthController.class)
public class HealthControllerTests {

    private final String uri = "/api/health";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        System.out.println("before each tests");
    }

    @Test
    public void itShouldRespondWith200() throws Exception {
        this.mockMvc.perform(get(uri)).andExpect(status().isOk()).andExpect(content().string("success"));
    }
}
