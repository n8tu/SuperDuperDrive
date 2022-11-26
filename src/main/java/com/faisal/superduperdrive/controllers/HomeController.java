package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.models.Credential;
import com.faisal.superduperdrive.models.Note;
import com.faisal.superduperdrive.services.CredentialService;
import com.faisal.superduperdrive.services.EncryptionService;
import com.faisal.superduperdrive.services.FileService;
import com.faisal.superduperdrive.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String getHomePage(Note note, Credential credential, Model model){
        model.addAttribute("files",fileService.getAllFiles());
        model.addAttribute("notes",noteService.getAllNotes());
        model.addAttribute("credentials",credentialService.getAllCredentials());
        model.addAttribute("encryption", encryptionService);
        return "home";
    }
}
