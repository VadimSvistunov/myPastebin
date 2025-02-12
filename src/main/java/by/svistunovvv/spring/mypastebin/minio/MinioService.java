package by.svistunovvv.spring.mypastebin.minio;

import by.svistunovvv.spring.mypastebin.model.dto.PostRequest;
import io.minio.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
@AllArgsConstructor
public class MinioService {
    private MinioClient minioClient;

    public boolean isBucketExist(String emailHash) {
        boolean found = false;
        try {
            found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(emailHash).build());
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }

        return found;
    }

    public void makeBucket(String emailHash) {
        try {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(emailHash)
                            .build());
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }
    }

    public void removeBucket(String emailHash) {
        try {
            minioClient.removeBucket(
                    RemoveBucketArgs.builder()
                            .bucket(emailHash)
                            .build());
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }
    }

    public String getPostText(String emailHash, String hash) {
        String text = null;
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(emailHash)
                        .object(hash)
                        .build())) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            text = finalFormatting(stringBuilder);
            reader.close();
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }

        return text;
    }

    private String finalFormatting(StringBuilder stringBuilder) {
        String text = stringBuilder.toString();
        if (text.endsWith("\n")) {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    public void putPost(String email, String hash , File file) {
        try {
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(Integer.toString(email.hashCode()))
                    .object(hash)
                    .stream(new FileInputStream(file), file.length(), -1)
                    .build());
        } catch (Exception e) {
            MinioExceptionHandler.handle(e);
        }
    }

    public void loadPostDataToMinio(PostRequest postRequest, String hash) {
        File file;
        try {
            file = Files.createTempFile(hash, "").toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(postRequest.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        putPost(postRequest.getEmail(), hash, file);
    }
}
