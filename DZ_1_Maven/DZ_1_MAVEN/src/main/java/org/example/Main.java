package org.example;

import com.google.gson.Gson;
import org.example.domain.Person;

/**Создать проект с использованием Maven или Gradle,
 *  добавить в него несколько зависимостей и написать код,
 *  использующий эти зависимости.
 Пример решения:
 1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
 2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
 3. Создайте класс Person с полями firstName, lastName и age.
 4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
 5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.**/

public class Main
{
    public static void main(String[] args)
    {
        /** создание новой персоны **/
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAge(30);

        /** Сериализация в JSON **/
        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println("JSON: " + json);

        /** Десериализация из JSON **/
        Person deserializedPerson = gson.fromJson(json, Person.class);
        System.out.println("Deserialized Person: " + deserializedPerson);

    }
}