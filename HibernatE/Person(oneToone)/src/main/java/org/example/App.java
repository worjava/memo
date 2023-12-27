package org.example;

import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
* Hello world!
*/
public class App {
public static void main(String[] args) {
    Configuration configuration = new Configuration().addAnnotatedClass(Person.class).
            addAnnotatedClass(Passport.class);
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();

    try {
        session.beginTransaction();
//            Person person = new Person("Игорь Трутнев",38);
//            Passport passport = new Passport(3010);
        Person person = session.get(Person.class, 3);


        Passport passport = session.get(Passport.class, 2);


        session.remove(person);









            session.getTransaction().commit();

        } finally {
            session.close();
        }


    }
}
