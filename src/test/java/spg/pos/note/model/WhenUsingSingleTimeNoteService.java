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
import spg.pos.note.model.SingleTimeNote;
import spg.pos.note.model.User;
import spg.pos.note.model.Note.NoteStatus;
import spg.pos.note.repositoryjpa.SingleTimeNoteJpaRepository;
import spg.pos.note.repositoryjpa.UserJpaRepository;

public class WhenUsingSingleTimeNoteService
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
    SingleTimeNoteJpaRepository service = new SingleTimeNoteJpaRepository();
    service.setEntityManager(manager);
    
    List<SingleTimeNote> notes = service.findAll();
    assertThat(notes, notNullValue());
    assertThat(notes.size(), is(0));
  }

  @Test
  public void ensureThatAfterInsertingANoteHeIsStored()
  {
    User owner = generateAndInsertValidUser();
    Collection<User> worker = new ArrayList<User>();
    worker.add(owner);

    SingleTimeNoteJpaRepository service = new SingleTimeNoteJpaRepository();
    service.setEntityManager(manager);
    
    SingleTimeNote note = new SingleTimeNote(null, owner, worker,
        "Wochenmeeting", NoteStatus.Open, new Date());
    service.persist(note);

    SingleTimeNote fromDb = service.findById(note.getId());

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.getCreator(), is(owner));
  }

  @Test
  public void ensureThatAfterInsertingOneElementFindAllReturnsAListWithOneElement()
  {
    User owner = generateAndInsertValidUser();
    Collection<User> worker = new ArrayList<User>();
    worker.add(owner);

    SingleTimeNoteJpaRepository service = new SingleTimeNoteJpaRepository();
    service.setEntityManager(manager);
    
    SingleTimeNote note = new SingleTimeNote(null, owner, worker,
        "Wochenmeeting", NoteStatus.Open, new Date());
    service.persist(note);

    List<SingleTimeNote> fromDb = service.findAll();

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.size(), is(1));
  }

  @Test
  public void ensureThatAfterInsertingANoteAndDeletingHimHeIsNotStored()
  {
    User owner = generateAndInsertValidUser();
    Collection<User> worker = new ArrayList<User>();
    worker.add(owner);

    SingleTimeNoteJpaRepository service = new SingleTimeNoteJpaRepository();
    service.setEntityManager(manager);
    
    SingleTimeNote note = new SingleTimeNote(null, owner, worker,
        "Wochenmeeting", NoteStatus.Open, new Date());
    service.persist(note);
    
    service.removeById(note.getId());

    SingleTimeNote fromDb = service.findById(note.getId());

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
