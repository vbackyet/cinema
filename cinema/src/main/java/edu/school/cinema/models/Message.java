package edu.school.cinema.models;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Getter
@Setter
@AllArgsConstructor
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
    private String username;

    @Lob
    @Column(name = "message")
    private String message;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    private MessageType type;

    public Message( MessageType type, String username) {
        this.username = username;
        this.type = type;
    }
}
