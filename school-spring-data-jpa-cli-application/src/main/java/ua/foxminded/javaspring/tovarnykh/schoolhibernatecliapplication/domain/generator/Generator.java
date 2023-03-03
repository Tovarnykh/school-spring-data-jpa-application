package ua.foxminded.javaspring.tovarnykh.schoolhibernatecliapplication.domain.generator;

import java.util.List;
import java.util.Random;

public interface Generator<T> {

    List<T> generate();

    default Random getRandom() {
        return new Random();
    }

}