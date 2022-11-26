package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.models.Note;
import com.faisal.superduperdrive.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/note/create")
    public String createNote(Note note, Authentication auth){
        noteService.insertNote(note,auth);
        return "redirect:/";
    }

    @RequestMapping(value = "/note/delete/{noteId}" , method = RequestMethod.DELETE)
    public String deleteNote(@PathVariable Integer noteId){
        noteService.deleteNote(noteId);
        return "redirect:/";
    }
}
