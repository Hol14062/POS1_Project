package spg.pos.note.repositoryjpa;

public interface PersistenceFactory
{
  SingleTimeNoteJpaRepository singleTimeNoteRepository();

  MultiTimeNoteJpaRepository multiTimeNoteRepository();

  UserJpaRepository userRepository();
}
