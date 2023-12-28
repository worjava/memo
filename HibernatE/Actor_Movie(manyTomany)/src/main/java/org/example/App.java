package org.example;

import modul.Actor;
import modul.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
//            Movie movie = new Movie("Cathe me if You cah", 2002);
//            Actor actor1 = new Actor("Leonardo DiCapri o", 48);
//            Actor actor2 = new Actor("Tom Hanks", 66);
//            movie.setActor(new ArrayList<>(List.of(actor1,actor2)));
//            actor1.setMovie(new ArrayList<>(List.of(movie)));
//            actor2.setMovie(new ArrayList<>(List.of(movie)));

            Actor actor = session.get(Actor.class, 10);
//удаляем взаимоссвязи с базы данных
            Movie movieRemove = actor.getMovie().remove(0);
            movieRemove.getActor().remove(actor);
            session.getTransaction().commit();

        }

//test cascade


    }
}
