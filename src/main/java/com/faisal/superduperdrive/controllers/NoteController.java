package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.models.Note;
import com.faisal.superduperdrive.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/createNote")
    public String createNote(Note note, Authentication auth){
        noteService.insertNote(note,auth);
        return "redirect:/";
    }
}
