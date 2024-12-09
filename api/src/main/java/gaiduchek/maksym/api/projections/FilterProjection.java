package gaiduchek.maksym.api.projections;

import gaiduchek.maksym.api.model.FilterCategory;

public interface FilterProjection {

    Long getId();

    String getName();

    FilterCategory getFilterCategory();

    Long getProductsCount();
}
