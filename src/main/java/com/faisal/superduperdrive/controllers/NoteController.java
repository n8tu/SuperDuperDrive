package com.faisal.superduperdrive.controllers;

import com.faisal.superduperdrive.models.Note;
import com.faisal.superduperdrive.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/note")
    public String createNote(Note note, Authentication auth){
        noteService.insertNote(note,auth);
        return "redirect:/";
    }

    @RequestMapping(value = "/note/delete/{noteId}" , method = RequestMethod.DELETE)
    public String deleteNote(@PathVariable Integer noteId, Authentication auth){
        noteService.deleteNote(noteId, auth);
        return "redirect:/";
    }

    @RequestMapping(value = "/note" , method = RequestMethod.PUT)
    public String updateNote(Note note, @RequestParam Integer noteId, Authentication auth){
        noteService.updateNote(note, noteId, auth);
        return "redirect:/";
    }

}
