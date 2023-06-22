package com.techendear.mislinius;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

public class JsonExampleVertx {
    public static void main(String[] args) {

//        Json Object
        JsonObject obj = jsonObject();
        System.out.println(obj);
//        Get map of json object
        System.out.println(obj.getMap());

//        Json Array
        JsonArray array = jsonArray();
        System.out.println(array);
//        Get map of json object
        System.out.println(array.getList());


        //        Json Object to java
        System.out.println(json2JavaObject());
    }

    static class Person {
        private Integer id;
        private String name;
        private Boolean active;

        public Person() {

        }

        public Person(Integer id, String name, Boolean active) {
            this.id = id;
            this.name = name;
            this.active = active;
        }
    }

    private static JsonObject jsonObject() {
        JsonObject obj = new JsonObject();
        obj.put("id", 1);
        obj.put("name", "Dharmendra Awasthi");
        obj.put("active", true);
        return obj;
    }

    private static JsonArray jsonArray() {
        JsonArray array = new JsonArray();
        array.add(jsonObject());
        JsonObject obj1 = jsonObject().put("id", 2);
        array.add(obj1);
        JsonObject obj2 = jsonObject().put("id", 3);
        array.add(obj2);
        return array;
    }

    private static Person json2JavaObject() {
        Person p = new Person(1, "Dharmendra", true);
        JsonObject obj = JsonObject.mapFrom(p);
        Person per = obj.mapTo(Person.class);
        return per;
    }
}
