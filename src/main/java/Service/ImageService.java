package Service;

import Model.ImageModel;
import Repository.ImageRepo;
import Util.ImageUtil;
import Exception.ImageServiceException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepo imageRepository;

    public String uploadImage(MultipartFile imageFile) throws IOException {
        var imageToSave = ImageModel.builder()
                .name(imageFile.getOriginalFilename())
                .type(imageFile.getContentType())
                .imageData(ImageUtil.compressImage(imageFile.getBytes()))
                .build();
        imageRepository.save(imageToSave);
        return "File uploaded successfully: " + imageFile.getOriginalFilename();
    }

    public byte[] downloadImage(String imageName) {
        Optional<ImageModel> dbImage = imageRepository.findByName(imageName);

        if (dbImage.isPresent()) {
            try {
                return ImageUtil.decompressImage(dbImage.get().getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ImageServiceException("Error downloading image", exception,
                        "Image ID: " + dbImage.get().getId() + ", Image name: " + imageName);
            }
        } else {
            throw new ImageServiceException("Image not found", null, "Image name: " + imageName);
        }
    }
}


