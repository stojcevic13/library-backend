package demo.simple.library.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookGenreId implements Serializable {
    private Integer bookId;
    private Integer genreId;

    // equals i hashCode OBAVEZNO
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookGenreId)) return false;
        BookGenreId that = (BookGenreId) o;
        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(genreId, that.genreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, genreId);
    }
}
