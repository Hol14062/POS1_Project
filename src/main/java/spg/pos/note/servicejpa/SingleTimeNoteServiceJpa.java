package spg.pos.note.servicejpa;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spg.pos.note.model.*;
import spg.pos.note.repositoryjpa.SingleTimeNoteJpaRepository;

@Service
public class SingleTimeNoteServiceJpa
{
  @Autowired
  private SingleTimeNoteJpaRepository singleTimeNoteJpaRepository;

  public void createNewUser(User creator, String title)
  {
    SingleTimeNote note = new SingleTimeNote(null, creator, null, title,
        Note.NoteStatus.Open, new Date());
    singleTimeNoteJpaRepository.persist(note);
  }
  
  public void setSingleTimeNoteJpaRepository(SingleTimeNoteJpaRepository singleTimeNoteJpaRepository) {
	  this.singleTimeNoteJpaRepository = singleTimeNoteJpaRepository;
  }
}
