package spg.pos.note.repositoryjpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spg.pos.note.model.User;

@Transactional
@Repository
public class UserJpaRepository extends AbstractJpaRepository<User>
{
  public User findById(Long id)
  {
    return entityManager().find(User.class, id);
  }

  public List<User> findAll()
  {
    TypedQuery<User> query = entityManager().createQuery("SELECT e FROM users e",
        User.class);

    return query.getResultList();
  }

  public void removeById(Long id)
  {
    entityManager().remove(findById(id));
  }
}