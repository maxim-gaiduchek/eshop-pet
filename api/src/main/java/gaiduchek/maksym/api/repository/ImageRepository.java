package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
