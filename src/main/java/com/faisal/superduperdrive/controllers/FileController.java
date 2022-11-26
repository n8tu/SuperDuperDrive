package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file/upload")
    public String uploadFile(@RequestParam MultipartFile fileUpload, Authentication auth) throws IOException {
        fileService.uploadFile(fileUpload,auth);
        return "redirect:/";
    }

    @GetMapping("/file/view/{fileId}")
    public String viewFile(@PathVariable Integer fileId, Model model){
        model.addAttribute(fileService.getFile(fileId));
        return "view_file";
    }

    @RequestMapping(value = "/file/delete/{fileId}", method = RequestMethod.DELETE)
    public String deleteFile(@PathVariable Integer fileId){
        fileService.deleteFile(fileId);
        return "redirect:/";
    }
}
