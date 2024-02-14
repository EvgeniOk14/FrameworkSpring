package com.example.dz_6.services;

import com.example.dz_6.models.Note;
import com.example.dz_6.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteService
{
    //region Fields
    @Autowired
    private  final NoteRepository noteRepository;
    //endRegion


    /** вывод всех заметок **/
    public List<Note> getAllNotes()
    {
        return noteRepository.findAll();
    }

    /** нахождение заметки по id **/
    public Note findNoteById(Long id)
    {

        return noteRepository.findNoteById(id);
    }

    /** удаление заметки **/
    public void deleteNote(Note note)
    {
        noteRepository.delete(note);
    }

    /** редактирование заметки **/
    public Note updateNote(Long id, Note note)
    {
        Note existingNote = noteRepository.findNoteById(id);

        if(existingNote !=null)
            {
                existingNote.setTitle(note.getTitle());
                existingNote.setContent(note.getContent());
                return noteRepository.save(existingNote);
            }
         else
            {
                throw new RuntimeException("заметки с таким id: " + id + "не найдено! ");
            }
    }

    /** создание новой заметки **/
    public Note createNode(Note note)
    {
      return noteRepository.save(note);
    }

    /** сохраниение новой заметки **/
    public Note saveNote(Note note)
    {
       return noteRepository.save(note);
    }
}
