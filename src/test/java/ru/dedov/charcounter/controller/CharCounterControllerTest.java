package ru.dedov.charcounter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.dedov.charcounter.dto.InputStringRequestDto;
import ru.dedov.charcounter.service.interfaces.CountCollisionsService;

import java.util.Map;
import java.util.TreeMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharCounterController.class)
@AutoConfigureMockMvc
public class CharCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CountCollisionsService countCollisionsService;

    @Test
    void shouldReturnBadRequestWhenInputStringIsNull() throws Exception {
        InputStringRequestDto request = new InputStringRequestDto(null);
        mockMvc.perform(post("/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenInputStringIsEmpty() throws Exception {
        InputStringRequestDto request = new InputStringRequestDto("");
        mockMvc.perform(post("/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCheckBracketsWithTooLongInput() throws Exception {
        InputStringRequestDto request = new InputStringRequestDto("эта строка содержит больше чем 32 символа, должен быть bad request");
        mockMvc.perform(post("/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkWhenInputStringIsNotEmpty() throws Exception {
        String inputText = "test";

        Map<String, Integer> charCount = new TreeMap<>();
        charCount.put("t", 2);
        charCount.put("e", 1);
        charCount.put("s", 1);

        InputStringRequestDto request = new InputStringRequestDto(inputText);
        when(countCollisionsService.countCharCollisions(anyString())).thenReturn(charCount);

        String expectedJson = new ObjectMapper().writeValueAsString(charCount);

        mockMvc.perform(post("/count")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
