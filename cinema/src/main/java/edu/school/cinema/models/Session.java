package edu.school.cinema.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Session")
public class Session {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @Column(name = "start_at")
    private Date startAt;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @Column
    private int cost;

//    A page for working with movie shows. An administrator can create a session
//for a certain movie in a certain movie hall at a required time. An administrator
//    should be able to indicate a ticket cost. You should implement loading of all
//    movie and movie hall data as attributes onto the page for subsequent selection
//    by an administrator.




}
