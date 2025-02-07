package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.exceptions.FileException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.ImageExceptionCodes;
import gaiduchek.maksym.api.model.Image;
import gaiduchek.maksym.api.services.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private static final String URL_TEMPLATE = "%s/%d/%s";

    @Override
    public Image saveFileAndBuildImage(MultipartFile imageFile, String baseUrl) {
        var timeMillis = new Date().getTime();
        var url = URL_TEMPLATE.formatted(baseUrl, timeMillis, imageFile.getOriginalFilename());
        var path = Path.of(url);
        saveImageFile(imageFile, path);
        return Image.builder()
                .url(path.toAbsolutePath().toString())
                .build();
    }

    private void saveImageFile(MultipartFile imageFile, Path path) {
        try {
            Files.createDirectories(path.getParent());
            Files.copy(imageFile.getInputStream(), path);
        } catch (IOException e) {
            log.error("Image cannot be loaded", e);
            throw new FileException(ImageExceptionCodes.IMAGE_CANNOT_BE_UPLOADED);
        }
    }

    @Override
    public void delete(Image image) {
    }
}
