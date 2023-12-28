package cinema;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Director")
public class Director {

@Id
@Column(name = "director_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int director_id;
@Column(name = "name")
    private String name;
@Column(name = "age")
    private int age;

@OneToMany(mappedBy = "directorMovie")
List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public Director(){}

    public Director(int id, String name, int age) {
        this.director_id = id;
        this.name = name;
        this.age = age;
    }

    public Director(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int id) {
        this.director_id = id;
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

    @Override
    public String toString() {
        return "Режиссер " +
                 "[" + director_id +"]"+
               " Имя " + name + '\'' +
               ",возраст " + age ;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
