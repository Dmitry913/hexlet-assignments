package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAllAuthor() {
        return authorRepository.findAll().stream().map(authorMapper::map).toList();
    }

    public AuthorDTO getAuthor(Long id) {
        return authorMapper.map(authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bla")));
    }

    public AuthorDTO createAuthor(AuthorCreateDTO dto) {
        return authorMapper.map(authorRepository.save(authorMapper.map(dto)));
    }

    public AuthorDTO updateAuthor(AuthorUpdateDTO dto, Long id) {
        Author entity = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("bla"));
        authorMapper.update(dto, entity);
        authorRepository.save(entity);
        return authorMapper.map(entity);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    // END
}
