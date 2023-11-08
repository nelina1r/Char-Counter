package ru.dedov.charcounter.service.implement;

import org.springframework.stereotype.Service;
import ru.dedov.charcounter.service.interfaces.CountCollisionsService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountCollisionsServiceImpl implements CountCollisionsService {

    @Override
    public Map<String, Integer> countCharCollisions(String inputString) {
        String[] letters = inputString.split("");
        Map<String, Integer> lettersMap = new HashMap<>();
        for (String letter : letters) {
            if (lettersMap.containsKey(letter)) {
                Integer count = lettersMap.get(letter);
                lettersMap.put(letter, ++count);
            } else {
                lettersMap.put(letter, 1);
            }
        }
        return lettersMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
}
