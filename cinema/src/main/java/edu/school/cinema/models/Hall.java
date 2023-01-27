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
@Table(name = "Halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "serial")
    private int serial_number;

    @Column(name = "seats")
    private int number_of_seats;

    @OneToMany(mappedBy = "hall")
    private List<Session> sessions;

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", serial_number=" + serial_number +
                ", number_of_seats=" + number_of_seats +
                ", sessions=" + sessions +
                '}';
    }
}
//    The page for working with movie halls contains a list of all movie halls created
//        by an administrator. The administrator can create a movie hall with a certain
//        configuration. To each movie hall, a serial number and number of seats are
//        assigned.