package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO dto) {
        return withTitleCont(dto.getTitleCont())
                .and(withCategoryId(dto.getCategoryId()))
                .and(withPrice(dto.getPriceLt(), dto.getPriceGt()))
                .and(withRatingGt(dto.getRatingGt()));
    }

    private Specification<Product> withTitleCont(String titleCont) {
        return (root, query, cb) -> titleCont == null? cb.conjunction() : cb.like(root.get("title"), titleCont.toLowerCase());
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withPrice(Integer priceLt, Integer priceGt) {
        return (root, query, cb) -> priceLt == null && priceGt == null ? cb.conjunction() : cb.between(root.get("price"), priceLt, priceGt);
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, cb) -> ratingGt == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), ratingGt);
    }
}
// END
