package kr.co.wave.service.config;


import kr.co.wave.entity.config.Category;
import kr.co.wave.repository.config.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public List<Category> getCategoryAll() {
        return categoryRepository.findAll();
    }
}
