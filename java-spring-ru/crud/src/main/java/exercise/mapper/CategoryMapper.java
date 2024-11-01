package exercise.mapper;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

// BEGIN
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CategoryMapper {

    CategoryDTO map(Category entity);

    Category map(CategoryCreateDTO dto);
}
// END
