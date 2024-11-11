package gaiduchek.maksym.api.filters;

import gaiduchek.maksym.api.model.Seller;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SellerFilter extends BaseFilter<Seller> {

    private String name;

    {
        sortVariants.put("name", List.of("surname", "name"));
    }

    @Override
    protected List<Predicate> getSpecificationPredicates(Root<Seller> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        var predicates = super.getSpecificationPredicates(root, query, builder);

        if (StringUtils.isNoneEmpty(name)) {
            var pattern = "%%%s%%".formatted(name.toLowerCase());
            var lower = builder.lower(root.get("name"));
            predicates.add(builder.like(lower, pattern));
        }

        return predicates;
    }
}
