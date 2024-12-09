package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Filter;
import gaiduchek.maksym.api.projections.FilterProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    @Query("""
            select f.id as id, f.name as name, fc as filterCategory, count(p) as productsCount
            from Filter f
                join Product p
                join p.filters pf
                join FilterCategory fc on fc = f.filterCategory
            where f.deleted = false
              and f in elements(p.filters)
              and pf.id in (:selectedFilterIds)
            group by f.id, f.name, fc.id
            order by f.name
            """)
    List<FilterProjection> getAllWithCounts(List<Long> selectedFilterIds);
}
