package edu.school.cinema.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.school.cinema.filters.MessageType;
import edu.school.cinema.models.Film;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User sender;

//    @Lob
    @Column(name = "message")
    private String content;


//    @JsonIgnore
    @Column(name = "created_at", nullable = false)
    private Date time;


    public Message(User newUser) {
        this.setSender(newUser);
    }


//    public Message(String sender, String content, MessageType type, String time) {
//        this.sender = sender;
//        this.content = content;
//        this.time = time;
//        this.type = type;
//    }


}
