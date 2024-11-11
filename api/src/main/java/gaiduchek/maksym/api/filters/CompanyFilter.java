package gaiduchek.maksym.api.filters;

import gaiduchek.maksym.api.model.Company;
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

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CompanyFilter extends BaseFilter<Company> {

    private String name;
    private List<Long> sellerIds;

    {
        sortVariants.put("name", List.of("name"));
        sortVariants.put("seller", List.of("seller.surname", "seller.name", "seller.middleName"));
    }

    @Override
    protected List<Predicate> getSpecificationPredicates(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        var predicates = super.getSpecificationPredicates(root, query, builder);

        if (StringUtils.isNoneEmpty(name)) {
            var pattern = "%%%s%%".formatted(name.toLowerCase());
            var lower = builder.lower(root.get("name"));
            predicates.add(builder.like(lower, pattern));
        }

        if (CollectionUtils.isNotEmpty(sellerIds)) {
            predicates.add(root.join("seller").get("id").in(sellerIds));
        }

        return predicates;
    }
}
