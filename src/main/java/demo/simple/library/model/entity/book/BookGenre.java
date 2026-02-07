package demo.simple.library.model.entity.book;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BookGenre {
    @EmbeddedId
    private BookGenreId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genre_id")
    private Genre genre;

}
