package am.itspace.authorbook.controller;

import am.itspace.authorbook.entity.Author;
import am.itspace.authorbook.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public String authorPage(ModelMap modelMap){
        List<Author> all = authorRepository.findAll();
        modelMap.put("authors",all);
        return "author";
    }

    @GetMapping("/author/add")
    public String addAuthorPage(){
        return "addAuthor";
    }

    @PostMapping("/author/add")
    public String addAuthor(@ModelAttribute Author author){
        authorRepository.save(author);
        return "redirect:/author";
    }

    @GetMapping("/author/delete")
    public String deleteAuthor(@RequestParam("id") int id){
        authorRepository.deleteById(id);
        return "redirect:/author";
    }
}
