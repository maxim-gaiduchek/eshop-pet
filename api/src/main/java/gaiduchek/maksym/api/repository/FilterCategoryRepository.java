package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.FilterCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterCategoryRepository extends JpaRepository<FilterCategory, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
