package com.example.phonebook2;

/**
 *
 * Created by 圣麟 on 2015/5/23.
 */
public class Person {

    private String name;
    private String phone;

    public Person(String name,String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

}
