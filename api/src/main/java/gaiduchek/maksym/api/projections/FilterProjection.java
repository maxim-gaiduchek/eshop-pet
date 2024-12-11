package gaiduchek.maksym.api.projections;

public interface FilterProjection {

    Long getId();

    String getName();

    Long getFilterCategoryId();

    String getFilterCategoryName();

    Long getProductsCount();
}
