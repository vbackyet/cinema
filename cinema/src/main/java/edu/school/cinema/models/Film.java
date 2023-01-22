package edu.school.cinema.models;


import lombok.*;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Film")
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
    private int age_restriction;

    @Column
    private String description;

    @Column
    private String poster_for_a_movie;





}
