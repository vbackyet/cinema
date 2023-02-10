package edu.school.cinema.filters;




import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class MessageDTO {

    private Long film_id;

    private String user_name;

    private String message;

    private String type;
    private String date;

//    public Message toEntity() {
//        Film film = new Film();
//        film.setId(this.film_id);
//
//        return new Message(film, this.username, this.message, new Date());
//    }
}
