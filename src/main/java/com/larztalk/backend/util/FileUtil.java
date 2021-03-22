package com.larztalk.backend.util;

import java.io.File;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by raghvendra.mishra on 10/08/20.
 */
@Slf4j
public class FileUtil {

    public static File saveFileLocally(MultipartFile file) throws Exception {
        String fileLoc = "/tmp/" + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File newFile = new File(fileLoc);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
            newFile.setWritable(true);
        }
        FileCopyUtils.copy(file.getBytes(), newFile);
        return newFile;
    }
}
