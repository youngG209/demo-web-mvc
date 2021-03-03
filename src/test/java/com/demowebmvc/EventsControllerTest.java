package com.demowebmvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EventsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getEvent() throws Exception {
        mockMvc.perform(get("/event/1;name=lee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
        ;
    }

    @Test
    public void getEvent2() throws Exception {
        mockMvc.perform(post("/event2")
                        .param("name", "lee")
                        .param("limit", "20")
                        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("lee"))
        ;
    }

    @Test
    public void eventForm() throws Exception {
        mockMvc.perform(get("/event/form"))
                .andDo(print())
                .andExpect(view().name("event/form"))
                .andExpect(model().attributeExists("event"))
        ;
    }

    @Test
    public void getEvent3() throws Exception {
        mockMvc.perform(post("/event3")
                .param("name", "lee")
                .param("limit", "-10")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("lee"))
        ;
    }

}