package spg.pos.note.repositoryjpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spg.pos.note.model.MultiTimeNote;
import spg.pos.note.model.Note;

@Transactional
@Repository
public class MultiTimeNoteJpaRepository extends AbstractJpaRepository<MultiTimeNote>
{
  @Override
  public MultiTimeNote findById(Long id)
  {
    return (MultiTimeNote)entityManager().find(Note.class, id);
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public List<MultiTimeNote> findAll()
  {
    TypedQuery<Note> query = entityManager().createQuery("SELECT e FROM notes e WHERE type = 'M'", Note.class);

    return (List)query.getResultList();
  }

  public void removeById(Long id)
  {
    entityManager().remove(findById(id));
  }
}
