package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Filter;
import gaiduchek.maksym.api.projections.FilterProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    boolean existsByNameAndFilterCategoryId(String name, Long filterCategoryId);

    boolean existsByNameAndIdNotAndFilterCategoryId(String name, Long id, Long filterCategoryId);

    @Query(value = """
            select f.id as id, f.name as name, fc.id as filterCategoryId, fc.name as filterCategoryName, count(p.id) as productsCount
            from filters f
                join filter_categories fc on fc.id = f.filter_category_id
                left join products p on f.id in (select ptf.filter_id
                                                 from product_to_filter ptf
                                                 where p.id=ptf.product_id)
                                    and p.id in (select p1.id
                                                 from products p1
                                                        left join product_to_filter ptf1 on ptf1.filter_id in :selectedFilterIds
                                                 group by p1.id
                                                 having count(distinct ptf1.filter_id) = :#{#selectedFilterIds.size()})
            where f.deleted = false
            group by f.id, f.name, fc.id
            order by f.name
            """, nativeQuery = true)
    List<FilterProjection> getAllWithCounts(List<Long> selectedFilterIds);
}
