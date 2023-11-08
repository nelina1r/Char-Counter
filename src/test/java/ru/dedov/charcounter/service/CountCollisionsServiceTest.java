package ru.dedov.charcounter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dedov.charcounter.service.implement.CountCollisionsServiceImpl;
import ru.dedov.charcounter.service.interfaces.CountCollisionsService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CountCollisionsServiceTest {

    private CountCollisionsService countCollisionsService;

    @BeforeEach
    void setUp() {
        countCollisionsService = new CountCollisionsServiceImpl();
    }

    @Test
    void testCountCharCollisions() {
        String inputString = "hello";
        Map<String, Integer> result = countCollisionsService.countCharCollisions(inputString);

        assertEquals(2, result.get("l"));
        assertEquals(1, result.get("h"));
        assertEquals(1, result.get("e"));
        assertEquals(1, result.get("o"));
    }


    @Test
    void testCountCharCollisionsWithSpecialCharacters() {
        String inputString = "123!@#$";
        Map<String, Integer> result = countCollisionsService.countCharCollisions(inputString);

        assertEquals(1, result.get("1"));
        assertEquals(1, result.get("2"));
        assertEquals(1, result.get("3"));
        assertEquals(1, result.get("!"));
        assertEquals(1, result.get("@"));
        assertEquals(1, result.get("#"));
        assertEquals(1, result.get("$"));
    }
}
