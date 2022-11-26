package com.faisal.superduperdrive.mappers;

import com.faisal.superduperdrive.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES(#{title},#{description}, #{userId});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "noteId")
    void insertNote(Note note);

    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Delete("DELETE FROM NOTES WHERE noteId = #{id}")
    void deleteNote(Integer noteId);

}
