package mk.ukim.finki.wp.eimt_lab.service.domain.impl;

import mk.ukim.finki.wp.eimt_lab.model.domain.Author;
import mk.ukim.finki.wp.eimt_lab.model.domain.Book;
import mk.ukim.finki.wp.eimt_lab.repository.AuthorRepository;
import mk.ukim.finki.wp.eimt_lab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
               return authorRepository
                       .findById(id)
                    .map((existingAuthor) -> {
                    existingAuthor.setName(author.getName());
                    existingAuthor.setCountry(author.getCountry());
                    existingAuthor.setSurname(author.getSurname());
                    return authorRepository.save(existingAuthor);
                });

    }

    @Override
    public Optional<Author> deleteById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        author.ifPresent(authorRepository::delete);
        return author;
    }
}
