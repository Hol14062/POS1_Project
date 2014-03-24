package spg.pos.note.repository;

import java.util.List;

import spg.pos.note.model.MultiTimeNote;
import spg.pos.note.model.SingleTimeNote;

public interface NoteRepositoryCustom
{
  List<SingleTimeNote> findAllSingleTimeNotes();
  SingleTimeNote findSingleNoteByName(String name);
  
  List<MultiTimeNote> findAllMultiTimeNotes();
  MultiTimeNote findMultiNoteByName(String name);
}
