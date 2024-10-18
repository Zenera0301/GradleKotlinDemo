package org.example;

import org.springframework.context.ApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context;
        Gril gril = new Gril();
        System.out.println(gril.greeting());

        Student student = new Student("小明", 18);
        System.out.println(student);
    }
}
