package spg.pos.note.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import spg.pos.note.model.MultiTimeNote;
import spg.pos.note.model.User;
import spg.pos.note.model.MultiTimeNote.RepeatType;
import spg.pos.note.model.Note.NoteStatus;
import spg.pos.note.repositoryjpa.MultiTimeNoteJpaRepository;
import spg.pos.note.repositoryjpa.UserJpaRepository;

public class WhenUsingMultiTimeNoteService
{
  private EntityManagerFactory factory;
  private EntityManager manager;

  @Before
  public void before()
  {
    factory = Persistence.createEntityManagerFactory("BaseService");

    if (factory != null)
      manager = factory.createEntityManager();
  }

  @After
  public void after()
  {
    if (manager != null)
      manager.close();
    if (factory != null)
      factory.close();
  }

  @Test
  public void ensureThatFindAllOnEmptyDBReturnsNoResult()
  {
    MultiTimeNoteJpaRepository service = new MultiTimeNoteJpaRepository();
    service.setEntityManager(manager);
    
    List<MultiTimeNote> notes = service.findAll();
    assertThat(notes, notNullValue());
    assertThat(notes.size(), is(0));
  }

  @Test
  public void ensureThatAfterInsertingANoteHeIsStored()
  {
    User owner = generateAndInsertValidUser();
    Collection<User> worker = new ArrayList<User>();
    worker.add(owner);

    MultiTimeNoteJpaRepository service = new MultiTimeNoteJpaRepository();
    service.setEntityManager(manager);
    
    MultiTimeNote note = new MultiTimeNote(null, owner, worker,
        "Wochenmeeting", NoteStatus.Open, RepeatType.Weekly, new Date());
    service.persist(note);

    MultiTimeNote fromDb = service.findById(note.getId());

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.getCreator(), is(owner));
  }

  @Test
  public void ensureThatAfterInsertingOneElementFindAllReturnsAListWithOneElement()
  {
    User owner = generateAndInsertValidUser();
    Collection<User> worker = new ArrayList<User>();
    worker.add(owner);

    MultiTimeNoteJpaRepository service = new MultiTimeNoteJpaRepository();
    service.setEntityManager(manager);
    
    MultiTimeNote note = new MultiTimeNote(null, owner, worker,
        "Wochenmeeting", NoteStatus.Open, RepeatType.Weekly, new Date());
    service.persist(note);

    List<MultiTimeNote> fromDb = service.findAll();

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.size(), is(1));
  }

  @Test
  public void entureThatAfterInsertingANoteAndDeletingHimHeIsNotStored()
  {
    User owner = generateAndInsertValidUser();
    Collection<User> worker = new ArrayList<User>();
    worker.add(owner);

    MultiTimeNoteJpaRepository service = new MultiTimeNoteJpaRepository();
    service.setEntityManager(manager);
    
    MultiTimeNote note = new MultiTimeNote(null, owner, worker,
        "Wochenmeeting", NoteStatus.Open, RepeatType.Weekly, new Date());
    service.persist(note);
    service.removeById(note.getId());

    MultiTimeNote fromDb = service.findById(note.getId());

    assertThat(fromDb, is(nullValue()));
  }

  private User generateAndInsertValidUser()
  {
    UserJpaRepository service = new UserJpaRepository();
    service.setEntityManager(manager);
    
    User newUser = new User("Fritz", "fritz@box.com", "Fitz", "Box",
        new Date(), "123456");
    service.persist(newUser);
    
    return newUser;
  }
}
