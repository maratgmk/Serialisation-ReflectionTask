package org.example.getjson;
/**
 *  2 Создайте собственный класс, который будет сериализовать объект из задания 1 в JSON.
 *  * Используйте Reflection API для анализа полей объекта и их значений, чтобы динамически
 *  * создавать JSON-представление объекта.
 */

import org.example.character.SuperHero;

import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Metamorphose {
    public static void main(String[] args) {
        SuperHero hero = new SuperHero("Wolverine",17,18,true);
        System.out.println(hero);
        SerializationByReflection serialization = new SerializationByReflection();
        Map<String,Object> nameToValue = serialization.transformClassToJson(hero);

        try {
            Files.write(Paths.get("hero.txt"), nameToValue.entrySet().stream()
                    .map(entry ->  entry.getKey() + " : " + entry.getValue())
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
