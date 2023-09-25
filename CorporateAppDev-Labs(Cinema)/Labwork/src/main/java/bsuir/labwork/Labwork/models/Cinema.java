package bsuir.labwork.Labwork.models;

import bsuir.labwork.Labwork.exceptions.InvalidCinemaException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.regex.Pattern;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String cinema_name;
    private String film;
    private String date;
    private String cost;


    private static final String CINEMA_NAME_PATTERN = "[а-яёА-ЯЁ\\s]+";//регулярные выражения
    private static final String FILM_PATTERN = "[а-яёА-ЯЁ\\s]+";
    private static final String DATE_PATTERN = "\\d{2}-\\d{2}-\\d{4}";

    public Cinema() {}

    public Cinema(String cinema_name, String film, String date, String cost) {
        this.cinema_name = cinema_name;
        this.film = film;
        this.date = date;
        this.cost = cost;
    }

    public void validate() throws InvalidCinemaException {
        if (!Pattern.matches(CINEMA_NAME_PATTERN, cinema_name)) {//Проверка через регулярное выражение
            throw new InvalidCinemaException("Ошибка cinemaName");
        }
        if (!Pattern.matches(FILM_PATTERN,film)) {
            throw new InvalidCinemaException("Ошибка film");
        }
        if (!Pattern.matches(DATE_PATTERN,date)) {
            throw new InvalidCinemaException("Ошибка date");
        }
        if (Integer.parseInt(cost)<0) {
            throw new InvalidCinemaException("Ошибка cost");
        }

    }
}
