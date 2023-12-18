package Library.Util;

import Library.DAO.BookDAO;
import Library.Models.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookDAO.show(book.getTitle()) != null)
            errors.rejectValue("title", "", "This title ia already taken");
    }
}
