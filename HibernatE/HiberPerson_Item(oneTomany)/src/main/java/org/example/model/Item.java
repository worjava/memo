package org.example.model;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Item")
public class Item {
    public Item(String itemName, Person owner) {
        this.itemName = itemName;
        this.owner = owner;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn (name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
               "id=" + id +
               ", itemName='" + itemName + '\'' +
               '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }
}


