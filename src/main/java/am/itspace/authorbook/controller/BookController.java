package am.itspace.authorbook.controller;

import am.itspace.authorbook.entity.Author;
import am.itspace.authorbook.entity.Book;
import am.itspace.authorbook.repository.AuthorRepository;
import am.itspace.authorbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public String bookPage(ModelMap modelMap){
        List<Book> books = bookRepository.findAll();
        modelMap.put("books",books);
        return "book/books";
    }

    @GetMapping("/add")
    public String addBookPage(ModelMap modelMap){
        List<Author> authors = authorRepository.findAll();
        modelMap.put("authors",authors);
        return "book/addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book ){
        book.setCreatedAt(new Date());
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") int id, ModelMap modelMap){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            modelMap.put("book",book);
            return "book/editBook";
        }
        return "redirect:/books";
    }

    @PostMapping("/edit")
    public String editAuthor(@ModelAttribute Book book){
        book.setCreatedAt(new Date());
        bookRepository.save(book);
        return "redirect:/books";
    }
}
