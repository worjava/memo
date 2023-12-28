package org.example;

import cinema.Director;
import cinema.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            Director director = session.get(Director.class, 7);
            Movie movie = session.get(Movie.class, 12);
            movie.setDirectorMovie(director);
            session.saveOrUpdate(movie);


            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
         /*    Movie movie = session.get(Movie.class, 3)
          System.out.println(  movie.getName()); // получаем название фильма
          System.out.println(movie.getDirectorMovie()); // получаем имя режисера
          Director director = session.get(Director.class,3); // добавляем фильм
            Movie movie = new Movie("Film for Guy Ritchie",2020,director); для режиссера
            director.getMovieList().add(movie); обновили хеш
            session.save(movie);
             Director director = new Director("Fedor Bondurchuck",55);
            Movie movie = new Movie("9 company",2005,director); создаем два объекта и сохраняем в базе данных
            director.setMovieList(new ArrayList<Movie>(Collections.singleton(movie)));
            session.save(director);
            session.save(movie);
               Director director = session.get(Director.class, 7);
            Movie movie = session.get(Movie.class, 12);
            movie.setDirectorMovie(director);
            session.saveOrUpdate(movie); сменяем режиссера
*/

            session.getTransaction().commit();

        } finally {
            session.close();
        }


    }
}
