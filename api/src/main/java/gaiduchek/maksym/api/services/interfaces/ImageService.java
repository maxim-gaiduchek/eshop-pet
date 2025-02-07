package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {

    Optional<Image> findById(Long id);

    Image getByIdOrThrow(Long id);

    byte[] readImageFile(String url) throws IOException;

    Image saveFileAndBuildImage(MultipartFile imageFile, String baseUrl);

    void delete(Image image);
}
