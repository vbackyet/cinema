package edu.school.cinema.models;


import lombok.*;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Films")
public class Film {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "title")
    private String title;

    @Column(name = "year_of_release")
    private int year_of_release;


    @Column
    private String age_restrictions;

    @Column
    private String description;

    @Column
    private String poster_for_a_movie;


    @OneToMany(mappedBy = "film")
    private List<Session> sessions;
    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year_of_release=" + year_of_release +
                ", age_restrictions='" + age_restrictions + '\'' +
                ", description='" + description + '\'' +
                ", poster_for_a_movie='" + poster_for_a_movie + '\'' +
                '}';
    }
}
