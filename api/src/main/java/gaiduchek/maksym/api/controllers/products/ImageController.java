package gaiduchek.maksym.api.controllers.products;

import gaiduchek.maksym.api.services.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "/{id}/files", produces = "image/*")
    public ResponseEntity<byte[]> getImageFile(@PathVariable Long id) throws IOException {
        var image = imageService.getByIdOrThrow(id);
        var imageSource = imageService.readImageFile(image.getUrl());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageSource);
    }
}
