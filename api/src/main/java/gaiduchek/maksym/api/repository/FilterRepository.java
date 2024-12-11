package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Filter;
import gaiduchek.maksym.api.projections.FilterProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    boolean existsByNameAndFilterCategoryId(String name, Long filterCategoryId);

    boolean existsByNameAndIdNotAndFilterCategoryId(String name, Long id, Long filterCategoryId);

    @Query("""
            select f.id as id, f.name as name, fc as filterCategory, false as exclude, count(p) as productsCount
            from Filter f
                join FilterCategory fc on fc = f.filterCategory
                left join Product p on f in elements(p.filters) and not exists (select 1
                                                                                from Product p1
                                                                                where p1 = p
                                                                                  and exists (select 1
                                                                                              from Filter pf
                                                                                              where pf in elements(p1.filters)
                                                                                                and pf.id not in (:selectedFilterIds)))
            where f.deleted = false
            group by f.id, f.name, fc.id
            order by f.name
            """)
    List<FilterProjection> getAllWithCounts(List<Long> selectedFilterIds);
}
