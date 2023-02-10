package edu.school.cinema.models;
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

    @Column(name = "username", nullable = false)
    private String sender;

//    @Lob
    @Column(name = "message")
    private String content;

    @Column(name = "created_at", nullable = false)
    private Date time;


    public Message( String username) {
        this.sender = username;
    }


//    public Message(String sender, String content, MessageType type, String time) {
//        this.sender = sender;
//        this.content = content;
//        this.time = time;
//        this.type = type;
//    }


}
