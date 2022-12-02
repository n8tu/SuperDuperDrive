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
    private final AuthenticationService authenticationService;

    public FileService(FileMapper fileMapper, UserService userService, AuthenticationService authenticationService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
        this.authenticationService = authenticationService;
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

    public File getFile(Integer id, Authentication auth){
        return fileMapper.getFileById(id, userService.getUserId(auth));
    }

    public List<File> getAllFiles(Authentication auth){
        return fileMapper.getAllFiles(userService.getUserId(auth));
    }

    public void deleteFile(Integer id, Authentication auth){
        fileMapper.deleteFile(id, userService.getUserId(auth));
    }

    public boolean isUploaded(MultipartFile file, Authentication auth){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        return fileMapper.getFileByName(filename, userService.getUserId(auth)) != null;
    }
}
