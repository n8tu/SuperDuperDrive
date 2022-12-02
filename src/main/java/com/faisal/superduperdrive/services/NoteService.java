package com.faisal.superduperdrive.services;

import com.faisal.superduperdrive.models.Note;
import com.faisal.superduperdrive.mappers.NoteMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public void insertNote(Note note, Authentication auth){
        Integer userId = userService.getUserByUsername(auth.getName()).getId();
        noteMapper.insertNote(new Note(null, note.getTitle(), note.getDescription(),userId));
    }

    public List<Note> getAllNotes(Authentication auth){
        return noteMapper.getAllNotes(userService.getUserId(auth));
    }

    public void deleteNote(Integer id, Authentication auth){
        noteMapper.deleteNote(id, userService.getUserId(auth));
    }

    public Note getNote(Integer id){
        return noteMapper.getNote(id);
    }

    public void updateNote(Note note, Integer noteId, Authentication auth){
        note.setId(noteId);
        noteMapper.updateNote(note, userService.getUserId(auth));
    }
}
