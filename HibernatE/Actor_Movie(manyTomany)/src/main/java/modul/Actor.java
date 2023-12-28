package modul;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @ManyToMany
    @JoinTable(name = "actor_movie",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movie;


    public Actor() {
    }
    public Actor(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Actor(String name, int age, List<Movie> movie) {
        this.name = name;
        this.age = age;
        this.movie = movie;
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

    public List<Movie> getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "Actor{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", age=" + age +
               '}';
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }
}
//@Entity
//@Table(name = "actors")
//public class Actor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private int age;
//    @ManyToMany
//    @JoinTable(name = "actor_movie",
//            joinColumns = @JoinColumn(name = "actor_id"),
//            inverseJoinColumns = @JoinColumn(name = "movie_id"))
//    private List<Movie> movies;
//}
//
//@Entity
//@Table(name = "movies")
//public class Movie {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private int year_production;
//    @ManyToMany(mappedBy = "movies")
//    private List<Actor> actors;
//}