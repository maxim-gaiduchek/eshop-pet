package gaiduchek.maksym.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "filters")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Filter extends BaseEntity {

    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private Administrator responsible;
    @ManyToOne
    @JoinColumn(name = "filter_category_id")
    private FilterCategory filterCategory;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "exclude")
    private Boolean exclude;

    @Transient
    private Long productsCount;

    public Filter(Long id, String name, FilterCategory filterCategory, Long productsCount) {
        this.name = name;
        this.filterCategory = filterCategory;
        this.productsCount = productsCount;
        setId(id);
    }
}
