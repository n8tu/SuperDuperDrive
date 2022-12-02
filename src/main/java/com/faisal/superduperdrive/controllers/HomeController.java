package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.models.Credential;
import com.faisal.superduperdrive.models.Note;
import com.faisal.superduperdrive.models.User;
import com.faisal.superduperdrive.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/")
    public String getHomePage(Note note, Credential credential, Model model, Authentication auth){
        model.addAttribute("files",fileService.getAllFiles(auth));
        model.addAttribute("notes",noteService.getAllNotes(auth));
        model.addAttribute("credentials",credentialService.getAllCredentials(auth));
        model.addAttribute("encryption", encryptionService);
        return "home";
    }



    @RequestMapping({"/*","/**","/***","/****","/*****"})
    public String redirectAll(){
        return "redirect:/";
    }

}
