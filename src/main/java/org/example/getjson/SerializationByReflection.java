package org.example.getjson;

import org.example.character.SuperHero;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SerializationByReflection {
    private SuperHero superHero;

    public SerializationByReflection() {
    }

    public Map<String,Object> transformClassToJson(SuperHero superHero) {
        Class<SuperHero> clazz = SuperHero.class;
        Map<String, Object> nameToValue = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                System.out.println(field.getName() + " : " + field.get(superHero));
                if (field.getType() == String.class) {
                    nameToValue.put(field.getName(), field.get(superHero));
                }
                if (field.getType() == int.class) {
                    nameToValue.put(field.getName(), field.get(superHero));
                }
                if (field.getType() == boolean.class) {
                    nameToValue.put(field.getName(), field.get(superHero));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return nameToValue;
    }
}
