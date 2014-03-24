package spg.pos.note.servicejpa;

/**
 * Interface for the 'abstract factory' to be used for providing all services. This approach is required when not using
 * e.g. Spring.
 */
public interface ServiceJpaFactory {

    MultiTimeNoteServiceJpa multiTimeNoteService();
    SingleTimeNoteServiceJpa singleTimeService();
    UserServiceJpa userService();
}
