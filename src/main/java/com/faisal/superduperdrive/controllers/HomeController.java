package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.models.Note;
import com.faisal.superduperdrive.services.FileService;
import com.faisal.superduperdrive.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;

    public HomeController(FileService fileService, NoteService noteService) {
        this.fileService = fileService;
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String getHomePage(Note note, Model model){
        model.addAttribute("files",fileService.getAllFiles());
        model.addAttribute("notes",noteService.getAllNotes());
        return "home";
    }
}
