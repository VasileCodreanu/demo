package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CovidController.class)
//@AutoConfigureMockMvc
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
    void shouldSaveCovid() throws Exception {
        Covid covid = new Covid(1L, 12, 2, 23, "Moldova");

        mockMvc.perform(
                post("/covid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(covid))
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnBadRequest() throws Exception {
        long id = -1L;

        when(service.findById(id)).thenThrow( new ResponseStatusException(HttpStatusCode.valueOf(404)) );

        mockMvc.perform(get("/covid/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldFindCovidById() throws Exception {
        Long id = 1L;
        Covid covid = new Covid(id, 12, 2, 23, "Japonia");

        when(service.findById(id)).thenReturn(covid);

        this.mockMvc.perform(
                get("/covid/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(covid.getId()))
                .andExpect(jsonPath("$.deaths").value(covid.getDeaths()))
                .andExpect(jsonPath("$.active").value(covid.getActive()))
                .andExpect(jsonPath("$.recovered").value(covid.getRecovered()))
                .andExpect(jsonPath("$.country").value(covid.getCountry()))

                .andDo(print());
    }

    @Test
    void top5by() {


    }

    @Test
    void totalBy() {



    }
}