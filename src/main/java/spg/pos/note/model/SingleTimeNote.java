package spg.pos.note.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A Note that occurrs a single time and is removed after then.
 * 
 * @author Michael
 */
@Entity
@DiscriminatorValue("S")
public class SingleTimeNote extends Note
{
  private static final long serialVersionUID = 1L;

  @Column(name = "date", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  public SingleTimeNote()
  {
    date = new Date();
  }

  public SingleTimeNote(Long id, User creator,
      Collection<User> worker, String title, NoteStatus status, Date date)
  {
    super(id, creator, worker, title, status);
    this.date = date;
  }

  @Override
  public Date getDate()
  {
    return date;
  }
}
