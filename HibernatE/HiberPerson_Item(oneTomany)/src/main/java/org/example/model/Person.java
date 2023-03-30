package org.example.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;


@Entity
@Table (name = "Person")
public class Person {
   @Id
   @Column(name = "id")
   @GeneratedValue(strategy = IDENTITY)

    private int id;
@Column(name = "name")
   String name;
@Column(name = "age")
   int age;
@OneToMany(mappedBy = "owner")

public List<Item> itemList ;

    public Person() {

    }

    public int getId() {
        return id;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Person{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               '}';
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

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
