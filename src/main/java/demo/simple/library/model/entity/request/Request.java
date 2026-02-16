package demo.simple.library.model.entity.request;

import demo.simple.library.model.entity.book.Book;
import demo.simple.library.model.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;


}
