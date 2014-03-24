package spg.pos.note.model;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import spg.pos.note.model.User;
import spg.pos.note.repositoryjpa.UserJpaRepository;

public class WhenUsingUserService
{
  private EntityManagerFactory factory;
  private EntityManager manager;

  @Before
  public void before()
  {
    factory = Persistence.createEntityManagerFactory("BaseService");
    
    if(factory != null)
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
    UserJpaRepository service = new UserJpaRepository();
    service.setEntityManager(manager);
    
    List<User> user = service.findAll();
    assertThat(user, notNullValue());
    assertThat(user.size(), is(0));
  }

  @Test
  public void ensureThatAfterInsertingAUserHeIsStored()
  {
    UserJpaRepository service = new UserJpaRepository();
    service.setEntityManager(manager);
    
    User newUser = new User("Fritz", "fritz@box.com", "Fitz", "Box",
        new Date(), "123456");
    service.persist(newUser);

    User fromDb = service.findById(newUser.getId());

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.getUsername(), is(newUser.getUsername()));
  }
  
  @Test
  public void ensureThatAfterInsertingOneElementFindAllReturnsAListWithOneElement()
  {
    UserJpaRepository service = new UserJpaRepository();
    service.setEntityManager(manager);
    
    User newUser = new User("Fritz", "fritz@box.com", "Fitz", "Box",
        new Date(), "123456");
    service.persist(newUser);

    List<User> fromDb = service.findAll();

    assertThat(fromDb, notNullValue());
    assertThat(fromDb.size(), is(1));
  }
  
  @Test
  public void ensureThatAfterInsertingAUserAndDeletingHimHeIsNotStored()
  {
    UserJpaRepository service = new UserJpaRepository();
    service.setEntityManager(manager);
    
    User newUser = new User("Fritz", "fritz@box.com", "Fitz", "Box",
        new Date(), "123456");
    service.persist(newUser);
    service.removeById(newUser.getId());
    
    User fromDb = service.findById(newUser.getId());

    assertThat(fromDb, is(nullValue()));
  }
}
