package com.larztalk.backend.util.file;

import static com.larztalk.backend.aws.AwsClient.S3_CLIENT;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.larztalk.backend.util.FileUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by raghvendra.mishra on 10/08/20.
 */
/**
 * currently uploads to AWS s3 but other services like dropbox and google drive can
 * be integrated here
 **/
@Slf4j
public class FileUploadUtil {

    /**
     ** takes file as inputStream, uploads and returns object url
     **/
    public static String uploadToS3(String bucket, String key, InputStream inputStream) throws Exception {
        byte[] bytes = IOUtils.toByteArray(inputStream);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        S3_CLIENT.putObject(new PutObjectRequest(bucket, key, byteArrayInputStream, objectMetadata)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return S3_CLIENT.getUrl(bucket, key).toExternalForm();
    }

    /**
     ** takes file as inputStream, uploads video and its thumbnail and returns object of VideoData
     ** call this method only in case of videos
     **/
    public static VideoData uploadToS3WithThumbnail(String bucket, String key, String videoFileName,
        String thumbnailFileName, MultipartFile multipartFile) throws Exception {
        File videoFile = null;
        File thumbnailFile = null;
        String videoUrl;
        String thumbnailUrl;
        try {
            // todo check for validations
            // input stream must be a video file, it must have file format supported by grab library
            // its name must end with file format like 'test.mp4'

            // save file locally
            videoFile = FileUtil.saveFileLocally(multipartFile);

            // extract thumbnail from video
            Picture picture = FrameGrab.getFrameFromFile(videoFile, 1);
            BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
            String thumbnailFilePath = UUID.randomUUID().toString();
            thumbnailFile = new File("/tmp/" + thumbnailFilePath);
            ImageIO.write(bufferedImage, "png", thumbnailFile);

            // upload video
            String videoFileKey = key + "/" + videoFileName.replace(" ","").trim();
            videoUrl = uploadToS3(bucket, videoFileKey, new FileInputStream(videoFile));
            // upload thumbnail
            String thumnailFilekey = key + "/" + thumbnailFileName.replace(" ","").trim();
            thumbnailUrl = uploadToS3(bucket, thumnailFilekey, new FileInputStream(thumbnailFile));
        } finally {
            if(videoFile != null && videoFile.exists()) {
                videoFile.delete();
            }
            if(thumbnailFile != null && thumbnailFile.exists()) {
                thumbnailFile.delete();
            }
        }
        return VideoData.builder().thumbnailUrl(thumbnailUrl).videoUrl(videoUrl).build();
    }
}
