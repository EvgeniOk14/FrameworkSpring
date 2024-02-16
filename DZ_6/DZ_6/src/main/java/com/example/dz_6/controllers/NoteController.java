package com.example.dz_6.controllers;

import com.example.dz_6.models.Note;
import com.example.dz_6.services.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class NoteController
{
    //region Fields
    @Autowired
    private NoteService noteService;
    //endregion

    /** Создание новой заметки **/
    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note)
    {
        return new ResponseEntity<>(noteService.createNode(note), HttpStatus.CREATED);
    }

    /** получить все заметки **/
    @GetMapping("/")
    public ResponseEntity<List<Note>> getAllNotes()
    {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /** обновить (редактировать) заметку **/
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note)
    {
       return new  ResponseEntity<> (noteService.updateNote(id, note), HttpStatus.OK);
    }

    /** Получить заметку по её id **/
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id)
    {
        Note findingNote = noteService.findNoteById(id);
        if(findingNote !=null)
        {
            return new ResponseEntity<>(noteService.findNoteById(id), HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
    }

    /**
     * удалить заметку по её id
     **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable("id") Long id)
    {
        Note deletingNote = noteService.findNoteById(id);
        if(deletingNote != null)
        {
            noteService.deleteNote(deletingNote);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
    }
}
