package matt.pas.movieclub.storage;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
    private final String fileStorageLocation;
    private final String imageStorageLocation;

    public FileStorageService(@Value("${app.storage.location}") String storageLocation) {
        this.fileStorageLocation = storageLocation + "/files/";
        this.imageStorageLocation = storageLocation + "/image/";
        Path fileStoragePath = Path.of(fileStorageLocation);
        Path imageStoragePath = Path.of(imageStorageLocation);
        prepareStorageDirectories(fileStoragePath, imageStoragePath);
    }

    private void prepareStorageDirectories(Path fileStoragePath, Path imageStoragePath) {
        try {
            if (Files.notExists(fileStoragePath)) {
                Files.createDirectories(fileStoragePath);
                logger.info("File storage directory created %s".formatted(fileStoragePath.toAbsolutePath().toString()));
            }
            if (Files.notExists(imageStoragePath)) {
                Files.createDirectories(imageStoragePath);
                logger.info("Image storage directory created %s".formatted(imageStoragePath.toAbsolutePath().toString()));
            }

        } catch (IOException e) {
            throw new UncheckedIOException("Creation of storage directories failed", e);
        }
    }
    public String saveImage(MultipartFile file) {
        return saveFile(file, imageStorageLocation);
    }

    public String saveFile(MultipartFile file) {
        return saveFile(file, fileStorageLocation);
    }

    private String saveFile(MultipartFile file, String storageLocation) {
        Path filePath = createFilePath(file, storageLocation);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.getFileName().toString();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private Path createFilePath(MultipartFile file, String storageLocation) {
        String oryginalFileName = file.getOriginalFilename();
        String fileBaseName = FilenameUtils.getBaseName(oryginalFileName);
        String fileExtention = FilenameUtils.getExtension(oryginalFileName);
        String completeFileName;
        Path filePath;
        int fileIndex = 0;
        do {
            completeFileName = fileBaseName + fileIndex + "." + fileExtention;
            filePath = Paths.get(storageLocation, completeFileName);
            fileIndex++;
        }while (Files.exists(filePath));
        return filePath;
    }
}
