package com.faisal.superduperdrive.services;

import com.faisal.superduperdrive.mappers.FileMapper;
import com.faisal.superduperdrive.models.File;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public int uploadFile(MultipartFile file, Authentication auth) throws IOException {

        if(!file.isEmpty() && auth.isAuthenticated()){

            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            String filesize = String.valueOf(file.getSize());
            int userId = userService.getUserByUsername(auth.getName()).getId();
            return fileMapper.insertFile(new File(null,filename, file.getContentType(), filesize, userId, file.getBytes()));
        }
        return -1;
    }

    public File getFile(Integer id){
        return fileMapper.getFile(id);
    }

    public List<File> getAllFiles(){
        return fileMapper.getAllFiles();
    }

    public void deleteFile(Integer id){
        fileMapper.deleteFile(id);
    }
}
