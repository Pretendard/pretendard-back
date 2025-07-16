package sclass.pretendard.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${BUCKET_NAME}")
    private String bucket;

    public String uploadImage(String fileName, byte[] data) {
        validateImage(data);
        String uniqueFileName = UUID.randomUUID() + "_" + fileName;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(data.length);
        amazonS3.putObject(bucket, uniqueFileName, new ByteArrayInputStream(data), metadata);
        return amazonS3.getUrl(bucket, uniqueFileName).toString();
    }

    private void validateImage(byte[] data) {
        final long MAX_SIZE_BYTES = 3 * 1024 * 1024; // 3MB

        if (data.length > MAX_SIZE_BYTES) {
            throw new IllegalArgumentException("이미지 크기가 3MB를 초과합니다.");
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
            BufferedImage image = ImageIO.read(bais);
            if (image == null) {
                throw new IllegalArgumentException("유효하지 않은 이미지 파일입니다.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("이미지 검증 중 오류가 발생했습니다.", e);
        }
    }
}
