package ca.access.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: Lei Fu
 * @date: 2023/07/03
 * @description: Upload local files
 */
public class NativeFileUtil {
    /**
     * @param uploadFile
     * @param filePath
     * @return
     */
    public static String uploadUserIcon(MultipartFile uploadFile, String filePath){

        // Get original name
        String oldName = uploadFile.getOriginalFilename();
        // Get file size
        long pictureSize = uploadFile.getSize();
        // File extension
        String fileExtension = oldName.substring(oldName.lastIndexOf("."), oldName.length());
        if(fileExtension.equals(".png")||fileExtension.equals(".jpg")||fileExtension.equals(".gif")||fileExtension.equals(".jpeg")){
            File folder = new File(filePath);
            if(!folder.isDirectory()) {
                folder.mkdirs();
            }
            String newName = UUID.randomUUID().toString() +
                    oldName.substring(oldName.lastIndexOf("."), oldName.length());
            // File save operation
            try {
                uploadFile.transferTo(new File(folder, newName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newName;
        }else{
            return null;
        }
    }
}
}
