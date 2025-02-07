package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    Image saveFileAndBuildImage(MultipartFile imageFile, String baseUrl);

    void delete(Image image);
}
