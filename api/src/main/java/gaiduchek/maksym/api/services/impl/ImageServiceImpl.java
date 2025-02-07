package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.FileException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.ImageExceptionCodes;
import gaiduchek.maksym.api.model.Image;
import gaiduchek.maksym.api.repository.ImageRepository;
import gaiduchek.maksym.api.services.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private static final String URL_TEMPLATE = "%s/%d/%s";

    private final ImageRepository imageRepository;

    @Override
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ImageExceptionCodes.IMAGE_DOES_NOT_EXIST, id));
    }

    @Override
    public byte[] readImageFile(String url) throws IOException {
        var path = Path.of(url);
        return Files.readAllBytes(path);
    }

    @Override
    public Image saveFileAndBuildImage(MultipartFile imageFile, String baseUrl) {
        var timeMillis = new Date().getTime();
        var url = URL_TEMPLATE.formatted(baseUrl, timeMillis, imageFile.getOriginalFilename());
        saveImageFile(imageFile, url);
        return Image.builder()
                .url(url)
                .filename(imageFile.getOriginalFilename())
                .fileSize(imageFile.getSize())
                .build();
    }

    private void saveImageFile(MultipartFile imageFile, String url) {
        try {
            var path = Path.of(url);
            Files.createDirectories(path.getParent());
            Files.copy(imageFile.getInputStream(), path);
        } catch (IOException e) {
            log.error("Image cannot be loaded", e);
            throw new FileException(ImageExceptionCodes.IMAGE_CANNOT_BE_UPLOADED);
        }
    }

    @Override
    public void delete(Image image) {
        if (image == null) {
            return;
        }
        try {
            var path = Path.of(image.getUrl());
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("Image cannot be deleted", e);
            throw new FileException(ImageExceptionCodes.IMAGE_CANNOT_BE_UPLOADED);
        }
    }
}
