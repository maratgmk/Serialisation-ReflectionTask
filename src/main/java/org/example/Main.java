package org.example;


import org.example.character.SuperHero;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 1 Создайте класс с 10 методами и 4 приватными полями. Геттеры и сеттеры добавлять не
 * нужно. Создайте объект Вашего класса. Выведите в консоль информацию об объекте.
 * Затем пользователь вводит имя поля или метода. Программа определяет, имя какого
 * члена класса было введено. Если введено имя метода, то этот метод немедленно
 * вызывается. Если введено имя поля, то программа запрашивает у пользователя новое
 * значение и устанавливает его новым значением этого поля.
 * 2 Создайте собственный класс, который будет сериализовать объект из задания 1 в JSON.
 * Используйте Reflection API для анализа полей объекта и их значений, чтобы динамически
 * создавать JSON-представление объекта.
 */
public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        SuperHero warrior = new SuperHero("Batman", 8, 3, true);
        System.out.println(warrior);

        Class<SuperHero> cls = SuperHero.class;

        List<String> listMethod = Arrays.stream(cls.getDeclaredMethods()).map(Method::getName).toList();
        List<String> listField = Arrays.stream(cls.getDeclaredFields()).map(Field::getName).toList();

        List<String> listMembers = new ArrayList<>(listMethod);
        listMembers.addAll(listField);

        System.out.println("Enter any word from list of class members : " + listMembers);
        String userWord = scanner.next();
        Optional<String> memberOfClass = (listMembers.stream().filter(f -> f.equalsIgnoreCase(userWord)).findFirst());
        System.out.println(memberOfClass);

        if (memberOfClass.isPresent()) {
            if (listMethod.contains(memberOfClass.get())) {
                try {
                    Method method = cls.getMethod(memberOfClass.get());
                    method.invoke(warrior);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (listField.contains(memberOfClass.get())) {
                Field field = cls.getDeclaredField(memberOfClass.get());
                System.out.println("Set new value field for  " + memberOfClass.get() + " : " );
                String newValue = scanner.next();
                field.setAccessible(true);
                if(field.getType() == int.class) {
                    try {
                        field.set(warrior,Integer.parseInt(newValue));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (field.getType() == boolean.class) {
                    try {
                        field.set(warrior,Boolean.parseBoolean(newValue));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (field.getType() == String.class) {
                    try {
                        field.set(warrior,newValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                field.setAccessible(false);
            }
        }
        System.out.println(warrior); //TODO данные полей не сохраняются?
    }
}