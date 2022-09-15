package com.galvanize.crudapp;


import com.galvanize.crudapp.dao.LessonRepository;
import com.galvanize.crudapp.model.Lesson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTest {
    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception{
        MockHttpServletRequestBuilder request =  post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"testingLesson\", \"deliveredOn\": \"2012-01-14\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class)));

    }

    @Test
    @Transactional
    @Rollback
    public void testList() throws Exception{
        Lesson defenseAgainstTheDarkArts = new Lesson();
        defenseAgainstTheDarkArts.setDeliveredOn(LocalDate.parse("2022-09-02"));
        defenseAgainstTheDarkArts.setTitle("Defense Against The Dark Arts 101");
        repository.save(defenseAgainstTheDarkArts);

        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(defenseAgainstTheDarkArts.getId().intValue())));


    }



}
