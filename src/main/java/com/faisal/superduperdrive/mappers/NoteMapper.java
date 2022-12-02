package com.faisal.superduperdrive.mappers;

import com.faisal.superduperdrive.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES(#{title},#{description}, #{userId});")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "noteId")
    void insertNote(Note note);

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> getAllNotes(Integer userId);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId} AND userId = #{userId}")
    void deleteNote(Integer noteId, Integer userId);

    @Select("SELECT * FROM NOTES WHERE noteId = #{id}")
    Note getNote(Integer id);

    @Update("UPDATE NOTES SET noteTitle = #{note.title}, noteDescription = #{note.description} WHERE noteId = #{note.id} AND userId = #{userId}")
    void updateNote(Note note, Integer userId);

}
