package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper mapper;

    // BEGIN
    @GetMapping
    public List<ProductDTO> index() {
        return productRepository.findAll().stream().map(entity -> mapper.map(entity)).toList();
    }

    @GetMapping("/{id}")
    public ProductDTO show(@PathVariable Long id) {
        Product productFromDB = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        return mapper.map(productFromDB);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO dto) {
        return mapper.map(productRepository.save(mapper.map(dto)));
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductUpdateDTO dto, @PathVariable Long id) {
        Product productFromDB = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        mapper.update(dto, productFromDB);
        return mapper.map(productRepository.save(productFromDB));
    }
    // END
}
