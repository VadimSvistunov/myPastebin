package by.svistunovvv.spring.mypastebin.minio;

import io.minio.errors.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(MinioExceptionHandler.class);

    public static void handle(Exception e) {
        if (e instanceof ErrorResponseException) {
            log.error("Error response from MinIO: {}", e.getMessage());
        } else if (e instanceof InsufficientDataException) {
            log.error("Insufficient data to complete the request: {}", e.getMessage());
        } else if (e instanceof InternalException) {
            log.error("Internal error in MinIO client: {}", e.getMessage());
        } else if (e instanceof InvalidKeyException) {
            log.error("Invalid key for authorization: {}", e.getMessage());
        } else if (e instanceof InvalidResponseException) {
            log.error("Invalid response from MinIO server: {}", e.getMessage());
        } else if (e instanceof IOException) {
            log.error("Input/Output error: {}", e.getMessage());
        } else if (e instanceof NoSuchAlgorithmException) {
            log.error("Encryption algorithm not found: {}", e.getMessage());
        } else if (e instanceof ServerException) {
            log.error("Server error in MinIO: {}", e.getMessage());
        } else if (e instanceof XmlParserException) {
            log.error("Error parsing XML response: {}", e.getMessage());
        } else {
            log.error("Unknown error: {}", e.getMessage());
        }
    }
}
