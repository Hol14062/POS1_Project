package spg.pos.note.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spg.pos.note.model.*;
import spg.pos.note.repository.NoteRepository;

@Service
public class MultiTimeNoteService
{
  @Autowired
  private NoteRepository noteRepository;
  
  public void createNewUser(User creator, String title, MultiTimeNote.RepeatType repeat)
  {
    MultiTimeNote note = new MultiTimeNote(null, creator, null, title, Note.NoteStatus.Open, repeat, new Date());
    noteRepository.save(note);
  }
}
