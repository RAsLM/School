package com.rasl.pojo;

public class Student {
    private int id;
    private String name;
    private int age;
    private int groupId;

    public Student(){

    }

    public Student(String name, int age, int groupId) {
        this.name = name;
        this.age = age;
        this.groupId = groupId;
    }

    public Student(int id, String name, int age, int groupId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id
                + ", username: " + name + ", age: " + age + ", groupId" + groupId +"}";
    }
}