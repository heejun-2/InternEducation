package com.example.upgrade.file;

import com.example.upgrade.entity.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir + filename;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = getStoreFilename(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return new UploadFile(originalFilename, storeFilename);
    }

    public String getStoreFilename(String originalFilename){
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        String uuid = UUID.randomUUID().toString();

        return uuid + "." + ext;
    }
}
