package com.amrit.springBoot.service.impl;

import com.amrit.springBoot.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${file.upload-dir}")
    private String uploadDirectory;

    public String uploadFile(MultipartFile file) throws IOException {
        // Clean the filename to remove any unsafe characters
        String originalFileName  = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new IOException("Provided file is empty or has no name.");
        }
        // Validate a file path to prevent directory traversal
        if (originalFileName .contains("..")) {
            throw new IOException("Invalid file name: " + originalFileName );
        }

        // Extract file extension (if any)
        String fileExtension = "";
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < originalFileName.length() - 1) {
            fileExtension = originalFileName.substring(dotIndex);
        }

        // Generate a new filename using UUID + original extension
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        // Define the file path (where to save the file)
        String filePathForStudent = "KIST";
        Path studentDir = Paths.get(uploadDirectory, filePathForStudent);
        if (!Files.exists(studentDir)) {
            Files.createDirectories(studentDir);  // create folder if missing
        }
        Path filePath = studentDir.resolve(newFileName);

        // Save the file to the target location
        Files.copy(file.getInputStream(), filePath);

        // Return the path of the saved file
        return "/" + filePathForStudent + "/" + newFileName;
    }
}
