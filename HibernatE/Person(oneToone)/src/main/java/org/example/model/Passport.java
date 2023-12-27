package org.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Passport")

public class Passport implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id")
    private Person person; // id ссылается на id


    @Column(name = "passport_number")
    private int passport_number;

    public Passport(Person person, int passport_number) {
        this.person = person;
        this.passport_number = passport_number;
    }

    public Passport() {

    }

    public Passport(int passport_number) {
        this.passport_number = passport_number;
    }



    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(int passport_number) {
        this.passport_number = passport_number;
    }




}

