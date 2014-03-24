package spg.pos.note.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@Entity(name = "notes")
@Table(name = "notes")
public abstract class Note extends Modelbase
{
  private static final long serialVersionUID = 1L;

  @Column(name = "title", length = 50, nullable = false)
  private String title;

  @Column(name = "status", length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private NoteStatus status;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "creator")
  private User creator;

  @ManyToMany(targetEntity = User.class)
  @JoinTable(name = "userHasNote", joinColumns =
  { @JoinColumn(name = "worker", referencedColumnName = "id") }, inverseJoinColumns =
  { @JoinColumn(name = "note", referencedColumnName = "id") })
  private Collection<User> worker;

  public Note()
  {
    status = NoteStatus.Open;
  }

  public Note(Long id, User creator, Collection<User> worker,
      String title, NoteStatus status)
  {
    super(id);
    this.title = title;
    this.status = status;
    this.creator = creator;
    this.worker = worker;
  }

  public abstract Date getDate();

  public String getTitle()
  {
    return title;
  }

  public NoteStatus getStatus()
  {
    return status;
  }

  public User getCreator()
  {
    return creator;
  }

  public Collection<User> getWorker()
  {
    return worker;
  }

  public void setWorker(Collection<User> worker)
  {
    this.worker = worker;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public void accomplished()
  {
    status = NoteStatus.Accomplished;
  }

  public void delete()
  {
    status = NoteStatus.Deleted;
  }

  public enum NoteStatus
  {
    Open, Accomplished, Deleted;
  }
}
