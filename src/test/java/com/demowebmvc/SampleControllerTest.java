package com.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {
//        get, post, delete, put 등등 방법별로 test 가능
        mockMvc.perform(get("/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void hiTest() throws Exception {
        mockMvc.perform(get("/hi")
                .header(HttpHeaders.AUTHORIZATION, "localhost")
                .param("name", "lee")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    public void byeTest() throws Exception {
        mockMvc.perform(get("/bye")
                        )
                .andDo(print())
                .andExpect(status().isOk())
                ;
    }

//    1. GET /events
    @Test
    public void getEventsTest() throws Exception {
        mockMvc.perform(get("/events"))
                .andExpect(status().isOk());
    }

//    2. GET /events/1,
//      GET /events/2,
//      GET /events/3
    @Test
    public void getEventsWithIdTest() throws Exception {
        mockMvc.perform(get("/events/1"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/events/2"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/events/3"))
                .andExpect(status().isOk());
    }

//    3. POST /events CONTENT-TYPE: application/json ACCEPT: application/json
    @Test
    public void postEventsTest() throws Exception {
        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

//    4. DELETE /events/1,
//      DELETE /events/2,
//      DELETE /events/3
    @Test
    public void deleteEventsTest() throws Exception {
        mockMvc.perform(delete("/events/1"))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/events/2"))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/events/3"))
                .andExpect(status().isOk());
    }

//    5. PUT /events/1 CONTENT-TYPE: application/json ACCEPT: application/json,
//      PUT /events/2 CONTENT-TYPE: application/json ACCEPT: application/json
    @Test
    public void putEventsTest() throws Exception {
        mockMvc.perform(put("/events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(put("/events/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

}