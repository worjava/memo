package modul;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "year_production")
    private int year_production;
    @ManyToMany(mappedBy = "movie")
    private List<Actor> actor;

    public int getId() {
        return id;
    }

    public Movie(String name, int year_production) {
        this.name = name;
        this.year_production = year_production;
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

    public int getYear_production() {
        return year_production;
    }

    public void setYear_production(int year_production) {
        this.year_production = year_production;
    }

    public List<Actor> getActor() {
        return actor;
    }

    public void setActor(List<Actor> actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "Movie{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", year_production=" + year_production ;
    }


    public Movie() {

    }
}
