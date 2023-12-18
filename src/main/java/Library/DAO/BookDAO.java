package Library.DAO;


import Library.Models.Book;
import Library.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return  jdbcTemplate.query("select * from book where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public Book show(String title) {
        return  jdbcTemplate.query("select * from book where title=?", new Object[]{title},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book(title, author, year) values (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id=?", id);
    }

    public List<Book> personBooks(int personId) {
        return jdbcTemplate.query("Select * from book where person_id=?", new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void setPerson(int personId, int id) {
        jdbcTemplate.update("UPDATE book SET person_id=? where id = ?", personId, id);
    }

    public void unsetPerson(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=? where id=?", (Object) null, id);
    }

    public Person personOwner(int id) {
        return jdbcTemplate.query("select name from person join book on person.id = book.person_id where book.id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}
