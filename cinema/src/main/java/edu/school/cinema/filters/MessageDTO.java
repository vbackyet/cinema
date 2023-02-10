package edu.school.cinema.filters;




import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {

    private Long film_id;

    private String user_name;

    private String message;

    private String type;
    private Date date;

    public MessageDTO(MessageType disconnect, String username) {
        user_name = username;
        type = String.valueOf(disconnect);
    }


//    public Message toEntity() {
//        Film film = new Film();
//        film.setId(this.film_id);
//
//        return new Message(film, this.username, this.message, new Date());
//    }
}
