package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CovidController.class)
@AutoConfigureMockMvc
class CovidControllerTest {

    @MockBean
    private CovidService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(service).isNotNull();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() throws Exception {
        Covid covid = new Covid(1L, 12, 2, 23, "Moldova");

        mockMvc.perform(post("/covid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(covid)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void findById() throws Exception {


        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/covid/2")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void top5by() {


    }

    @Test
    void totalBy() {



    }
}