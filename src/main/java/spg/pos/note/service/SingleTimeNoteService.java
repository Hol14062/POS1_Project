package spg.pos.note.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spg.pos.note.model.*;
import spg.pos.note.repository.NoteRepository;

@Service
public class SingleTimeNoteService
{
  @Autowired
  private NoteRepository noteRepository;

  public void createNewUser(User creator, String title)
  {
    SingleTimeNote note = new SingleTimeNote(null, creator, null, title,
        Note.NoteStatus.Open, new Date());
    noteRepository.save(note);
  }
}
