package cinema;

import javax.persistence.*;
@Entity
@Table(name = "Movie")
public class Movie {

    @Id // указываем свой свойство класса первичным ключом таблицы
    @Column(name = "movie_id") // указываем столбца в таблие базе данных
    @GeneratedValue(strategy = GenerationType.IDENTITY) // указываем стратегию(генерацию ключа
    private int id;  // создаем переменные для фильма id
    @Column(name = "name")
    private String name; // название фильм
    @Column(name = "year_of_production")
    int year_of_production; // год выпуска
    @ManyToOne // связываем сущности
    @JoinColumn(name = "director_id", referencedColumnName = "director_id")
    private Director directorMovie;

    public Movie(String name, int year_of_production, Director directorMovie) {
        this.name = name;
        this.year_of_production = year_of_production;
        this.directorMovie = directorMovie;
    }

    public Movie(String name, Director directorMovie) {
        this.name = name;
        this.directorMovie = directorMovie;
    }

    public Movie(String name) {
        this.name = name;
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

    public int getYear_of_production() {
        return year_of_production;
    }

    public void setYear_of_production(int year_of_production) {
        this.year_of_production = year_of_production;
    }

    public Director getDirectorMovie() {
        return directorMovie;
    }

    public void setDirectorMovie(Director directorMovie) {
        this.directorMovie = directorMovie;
    }

    public Movie() {


    }

    @Override
    public String toString() {
        return
                id +
               " название фильма " + name + '\'' +
               " год выпуска "  + year_of_production
               ;
    }

}



