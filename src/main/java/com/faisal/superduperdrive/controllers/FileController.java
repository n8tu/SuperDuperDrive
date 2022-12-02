package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file/upload")
    public String uploadFile(@RequestParam MultipartFile fileUpload, Authentication auth, RedirectAttributes attr) throws IOException {
        if(fileService.isUploaded(fileUpload, auth)){
            attr.addFlashAttribute("hasError","There is file uploaded with the same name");
            return "redirect:/";
        }
        fileService.uploadFile(fileUpload,auth);
        attr.addFlashAttribute("success","Your file is uploaded successfully");
        return "redirect:/";
    }

    @GetMapping("/file/view/{fileId}")
    public String viewFile(@PathVariable Integer fileId, Model model, Authentication auth){
        model.addAttribute(fileService.getFile(fileId,auth));
        return "view_file";
    }

    @RequestMapping(value = "/file/delete/{fileId}", method = RequestMethod.DELETE)
    public String deleteFile(@PathVariable Integer fileId, Authentication auth){
        fileService.deleteFile(fileId, auth);
        return "redirect:/";
    }

}
