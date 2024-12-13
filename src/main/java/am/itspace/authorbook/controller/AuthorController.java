package am.itspace.authorbook.controller;

import am.itspace.authorbook.entity.Author;
import am.itspace.authorbook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public String authorPage(ModelMap modelMap){
        List<Author> all = authorRepository.findAll();
        modelMap.put("authors",all);
        return "author";
    }

    @GetMapping("/add")
    public String addAuthorPage(){
        return "addAuthor";
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author){
        authorRepository.save(author);
        return "redirect:/author";
    }

    @GetMapping("/delete")
    public String deleteAuthor(@RequestParam("id") int id){
        authorRepository.deleteById(id);
        return "redirect:/author";
    }

    @GetMapping("/edit")
    public String editAuthorPage(@RequestParam("id") int id, ModelMap modelMap){
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()){
            Author author = authorOptional.get();
            modelMap.put("author",author);
            return "author/editAuthor";
        }
        return "redirect:/author";
    }

    @PostMapping("/edit")
    public String editAuthor(@ModelAttribute Author author){
        authorRepository.save(author);
        return "redirect:/author";
    }
}
