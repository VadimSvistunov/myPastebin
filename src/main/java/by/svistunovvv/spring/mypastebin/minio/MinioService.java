package by.svistunovvv.spring.mypastebin.minio;

import by.svistunovvv.spring.mypastebin.model.dto.PostRequest;
import io.minio.*;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class MinioService {
    private MinioClient minioClient;

    public boolean isBucketExist(String email) {
        boolean found = false;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(email).build());
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }

        return found;
    }

    public void makeBucket(String email) {
        try {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(email)
                            .build());
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }
    }

    public void removeBucket(String email) {
        try {
            minioClient.removeBucket(
                    RemoveBucketArgs.builder()
                            .bucket(email)
                            .build());
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }
    }

    public String getPost(String email, String hash) {
        String text = null;
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(email)
                        .object(hash)
                        .build())) {
            byte[] bytes = stream.readAllBytes();
            text = new String(bytes);
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }

        return text;
    }

    public void putPost(String email, File file) {
        try {
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(email)
                    .object(file.getName())
                    .stream(new FileInputStream(file), file.length(), -1)
                    .build());
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }
    }

    public void loadPostDataToMinio(PostRequest postRequest, int hash) {
        File file;
        try {
            file = Files.createTempFile(Integer.toString(hash), "").toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(postRequest.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        putPost(postRequest.getEmail(), file);
    }
}
