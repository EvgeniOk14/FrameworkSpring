package com.example.dz_6.repositories;

import com.example.dz_6.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long >
{
    /** создаём метод нахождения заметки по её id **/
    @Query("SELECT n FROM Note n WHERE n.id =:id")
    @Transactional
    Note findNoteById(@Param("id") Long id);

    /** создаём метод обновления заметки **/
    @Query("UPDATE Note n SET n.title =:title, n.content=:content WHERE n.id=:id")
    @Modifying
    @Transactional
    Note updateNoteById(@Param("id") Long id, @Param("title") String title, @Param("content") String content);
}
