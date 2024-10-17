package gaiduchek.maksym.api.filters;

import gaiduchek.maksym.api.model.BaseEntity;
import gaiduchek.maksym.api.utils.DateTimeUtils;
import io.vavr.control.Try;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder(builderMethodName = "baseBuilder")
public class BaseFilter<T extends BaseEntity> {

    protected String createdAtMinDate;
    protected String createdAtMaxDate;
    @Builder.Default
    protected Integer page = 1;
    @Builder.Default
    protected Integer pageSize = 20;
    protected String sortBy;
    protected String sortDirection;

    protected final Map<String, List<String>> sortVariants = new HashMap<>();
    protected static final String DEFAULT_SORT_BY = "createdAt";
    protected static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.DESC;

    {
        sortVariants.put("id", List.of("id"));
        sortVariants.put("createdAt", List.of("createdAt"));
    }

    protected List<Predicate> getSpecificationPredicates(Root<T> root, CriteriaQuery<?> query,
                                                         CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNoneEmpty(createdAtMinDate)) {
            var startDay = DateTimeUtils.convertDateStartDay(createdAtMinDate);
            predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), startDay));
        }

        if (StringUtils.isNoneEmpty(createdAtMaxDate)) {
            var endDay = DateTimeUtils.convertDateEndDay(createdAtMaxDate);
            predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), endDay));
        }

        return predicates;
    }

    public Specification<T> buildSpecification() {
        return (root, query, builder) -> {
            var predicates = getSpecificationPredicates(root, query, builder);
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Pageable buildPageable() {
        if (pageSize == 0) {
            return PageRequest.of(0, Integer.MAX_VALUE, buildSort());
        }
        return PageRequest.of(page - 1, pageSize, buildSort());
    }

    protected Sort buildSort() {
        var direction = getSortDirection();
        var property = sortVariants.getOrDefault(sortBy, List.of(DEFAULT_SORT_BY)).toArray(new String[0]);
        return Sort.by(direction, property);
    }

    protected Sort.Direction getSortDirection() {
        return Try.of(() -> Sort.Direction.fromString(sortDirection))
                .getOrElse(DEFAULT_SORT_DIRECTION);
    }
}
