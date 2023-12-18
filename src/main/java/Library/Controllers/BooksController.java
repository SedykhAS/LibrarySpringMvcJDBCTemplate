package Library.Controllers;

import Library.DAO.BookDAO;
import Library.DAO.PersonDAO;
import Library.Models.Book;
import Library.Models.Person;
import Library.Util.BookValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final BookValidator bookValidator;

    public BooksController(PersonDAO personDAO, BookDAO bookDAO, BookValidator bookValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "Books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        model.addAttribute("owner", bookDAO.personOwner(id));
        return "Books/show";
    }

    @PatchMapping("/{id}")
    public String setPerson(@ModelAttribute("person") Person person,
                            @PathVariable("id") int id) {
        bookDAO.setPerson(person.getId(), id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "Books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors())
            return "Books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/unset")
    public String unsetPerson(@PathVariable("id") int id) {
        bookDAO.unsetPerson(id);
        return "redirect:/books";
    }

}
