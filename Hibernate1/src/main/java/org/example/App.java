package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {


        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).
                addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        List<String> list = new ArrayList<>();
        try {
            session.beginTransaction();
            Person person = session.get(Person.class, 9);
            Item item = session.get(Item.class, 3);
            System.out.println(item);



            session.getTransaction().commit();
        } finally {
            session.close();


            Properties prop = new Properties();
            prop.load(new FileInputStream("C:\\Users\\nedvi\\IdeaProjects\\HibernateLesson1\\src\\main\\resources\\hibernate-postgres.properties"));
            Configuration configuration1 = new Configuration().
                    addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
            configuration1.setProperties(prop);


            SessionFactory sessionFactory1 = configuration1.buildSessionFactory();
            Session session1 = sessionFactory1.getCurrentSession();
            try {
                session1.beginTransaction();
            Person person =    session1.get(Person.class,3);
                System.out.println(person.getItemList());
                System.out.println( person.getName());

                session1.getTransaction().commit();


            } finally {
                session1.close();
            }
        }
    }
}



