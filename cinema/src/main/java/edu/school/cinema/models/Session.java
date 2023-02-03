package edu.school.cinema.models;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne()
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;



    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonIgnore
    @Column(name = "start_at")
    private LocalDateTime startAt;

    @ManyToOne()
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    @Column
    private int cost;

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", film=" + film +
                ", startAt=" + startAt.toString() +
                ", hall=" + hall +
                ", cost=" + cost +
                '}';
    }

//    public Session(Film film, LocalDateTime startAt, Hall hall, int cost) {
//        this.film = film;
//        this.startAt = startAt;
//        this.hall = hall;
//        this.cost = cost;
//    }

//    A page for working with movie shows. An administrator can create a session
//for a certain movie in a certain movie hall at a required time. An administrator
//    should be able to indicate a ticket cost. You should implement loading of all
//    movie and movie hall data as attributes onto the page for subsequent selection
//    by an administrator.




}
