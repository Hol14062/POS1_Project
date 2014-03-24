package spg.pos.note.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysema.query.jpa.impl.JPAQuery;

import spg.pos.note.model.MultiTimeNote;
import spg.pos.note.model.QMultiTimeNote;
import spg.pos.note.model.QSingleTimeNote;
import spg.pos.note.model.SingleTimeNote;
import spg.pos.note.repository.NoteRepositoryCustom;

public class NoteRepositoryImpl implements NoteRepositoryCustom
{
  @PersistenceContext
  EntityManager entityManager;

  public List<SingleTimeNote> findAllSingleTimeNotes()
  {
    JPAQuery query = new JPAQuery(entityManager);

    QSingleTimeNote note = QSingleTimeNote.singleTimeNote;

    query.from(note).orderBy(note.title.asc());
    return query.list(note);
  }

  public SingleTimeNote findSingleNoteByName(String name)
  {
    JPAQuery query = new JPAQuery(entityManager);

    QSingleTimeNote note = QSingleTimeNote.singleTimeNote;

    query.from(note).where(note.title.eq(name));
    return query.list(note).get(0);
  }

  public List<MultiTimeNote> findAllMultiTimeNotes()
  {
    JPAQuery query = new JPAQuery(entityManager);

    QMultiTimeNote note = QMultiTimeNote.multiTimeNote;

    query.from(note).orderBy(note.title.asc());
    return query.list(note);
  }

  public MultiTimeNote findMultiNoteByName(String name)
  {
    JPAQuery query = new JPAQuery(entityManager);

    QMultiTimeNote note = QMultiTimeNote.multiTimeNote;

    query.from(note).where(note.title.eq(name));
    return query.list(note).get(0);
  }
}
