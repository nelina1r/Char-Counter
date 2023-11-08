package ru.dedov.charcounter.service.interfaces;

import java.util.Map;

public interface CountCollisionsService {

    Map<String, Integer> countCharCollisions(String inputString);
}
