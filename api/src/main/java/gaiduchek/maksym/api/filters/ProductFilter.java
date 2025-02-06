package gaiduchek.maksym.api.filters;

import gaiduchek.maksym.api.model.Product;
import gaiduchek.maksym.api.security.services.interfaces.AccessService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ProductFilter extends BaseFilter<Product> {

    private AccessService accessService;

    private String name;
    private BigDecimal costMin;
    private BigDecimal costMax;
    private List<Long> companyIds;
    private List<Long> productCategoryIds;
    private List<Long> filterIds;
    private List<Boolean> deleted;

    {
        sortVariants.put("name", List.of("name"));
        sortVariants.put("cost", List.of("cost"));
        sortVariants.put("type", List.of("type"));
        sortVariants.put("company", List.of("company.name"));
        sortVariants.put("productCategory", List.of("productCategory.name"));
        sortVariants.put("deleted", List.of("deleted"));
    }

    @Override
    protected List<Predicate> getSpecificationPredicates(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        var predicates = super.getSpecificationPredicates(root, query, builder);

        if (accessService != null && accessService.isCustomer()) {
            deleted = List.of(false);
        }

        if (StringUtils.isNoneEmpty(name)) {
            var pattern = "%%%s%%".formatted(name.toLowerCase());
            var lower = builder.lower(root.get("name"));
            predicates.add(builder.like(lower, pattern));
        }

        if (Objects.nonNull(costMin)) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("cost"), costMin));
        }

        if (Objects.nonNull(costMax)) {
            predicates.add(builder.lessThanOrEqualTo(root.get("cost"), costMax));
        }

        if (CollectionUtils.isNotEmpty(companyIds)) {
            predicates.add(root.join("company").get("id").in(companyIds));
        }

        if (CollectionUtils.isNotEmpty(productCategoryIds)) {
            predicates.add(root.join("productCategory").get("id").in(productCategoryIds));
        }

        if (CollectionUtils.isNotEmpty(filterIds)) {
            predicates.add(root.join("filters").get("id").in(filterIds));
        }

        if (CollectionUtils.isNotEmpty(deleted)) {
            predicates.add(root.get("deleted").in(deleted));
        }

        return predicates;
    }
}
