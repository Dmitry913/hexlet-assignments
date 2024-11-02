package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAllBook() {
        return bookRepository.findAll().stream().map(bookMapper::map).toList();
    }

    public BookDTO show(Long id) {
        return bookMapper.map(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bla")));
    }

    public BookDTO create(BookCreateDTO dto) {
        return bookMapper.map(bookRepository.save(bookMapper.map(dto)));
    }

    public BookDTO update(BookUpdateDTO dto, Long id) {
        Book entity = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bla"));
        bookMapper.update(dto, entity);
        bookRepository.save(entity);
        return bookMapper.map(entity);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
