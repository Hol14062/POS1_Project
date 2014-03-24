package spg.pos.note.repositoryjpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spg.pos.note.model.SingleTimeNote;
import spg.pos.note.model.Note;

@Transactional
@Repository
public class SingleTimeNoteJpaRepository extends AbstractJpaRepository<SingleTimeNote>
{
  @Override
  public SingleTimeNote findById(Long id)
  {
    return (SingleTimeNote)entityManager().find(Note.class, id);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<SingleTimeNote> findAll()
  {
    TypedQuery<Note> query = entityManager().createQuery("SELECT e FROM notes e where type = 'S'", Note.class);
    return (List)query.getResultList();
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<Note> yolo()
  {
    TypedQuery<Note> query = entityManager().createQuery("SELECT e FROM notes e", Note.class);
    return (List)query.getResultList();
  }

  public void removeById(Long id)
  {
    entityManager().remove(findById(id));
  }
}
