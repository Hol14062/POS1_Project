package spg.pos.note.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spg.pos.note.model.Note;

@Repository
public interface NoteRepository extends NoteRepositoryCustom, CrudRepository<Note, Long>
{
  
}
