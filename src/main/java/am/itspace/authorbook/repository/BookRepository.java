package am.itspace.authorbook.repository;

import am.itspace.authorbook.entity.Author;
import am.itspace.authorbook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Integer> {

}
