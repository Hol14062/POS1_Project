package spg.pos.note.servicejpa;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spg.pos.note.model.*;
import spg.pos.note.repositoryjpa.MultiTimeNoteJpaRepository;

@Service
public class MultiTimeNoteServiceJpa
{
  @Autowired
  private MultiTimeNoteJpaRepository multiTimeNoteJpaRepository;
  
  public void createNewUser(User creator, String title, MultiTimeNote.RepeatType repeat)
  {
    MultiTimeNote note = new MultiTimeNote(null, creator, null, title, Note.NoteStatus.Open, repeat, new Date());
    multiTimeNoteJpaRepository.persist(note);
  }
  
  public void setMultiTimeNoteJpaRepository(MultiTimeNoteJpaRepository multiTimeNoteJpaRepository) {
	  this.multiTimeNoteJpaRepository = multiTimeNoteJpaRepository;
  }
}
