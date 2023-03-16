package ua.foxminded.javaspring.tovarnykh.schoolcliapplication.domain.generator;

import java.util.List;
import java.util.Random;

public interface Generator<T> {

    List<T> generate();

    default Random getRandom() {
        return new Random();
    }

}