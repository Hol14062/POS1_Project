package spg.pos.note.app;

import java.util.HashMap;

import javax.persistence.EntityManager;

import spg.pos.note.repositoryjpa.*;

/**
 * The factory for all repositories...
 */
public class PersistenceFactoryImpl implements PersistenceFactory {

    private final HashMap<Class<?>, JpaRepository> repositories = new HashMap<>();

    public PersistenceFactoryImpl(EntityManager entityManager) {
        MultiTimeNoteJpaRepository multiTimeNoteRepository = new MultiTimeNoteJpaRepository();
        multiTimeNoteRepository.setEntityManager(entityManager);
        repositories.put(MultiTimeNoteJpaRepository.class, multiTimeNoteRepository);
        
        SingleTimeNoteJpaRepository singleTimeNoteRepository = new SingleTimeNoteJpaRepository();
        singleTimeNoteRepository.setEntityManager(entityManager);
        repositories.put(SingleTimeNoteJpaRepository.class, singleTimeNoteRepository);
    	
        UserJpaRepository userRepository = new UserJpaRepository();
        userRepository.setEntityManager(entityManager);
        repositories.put(UserJpaRepository.class, userRepository);
    }

    @Override
    public SingleTimeNoteJpaRepository singleTimeNoteRepository() {
    	return (SingleTimeNoteJpaRepository) repositories.get(SingleTimeNoteJpaRepository.class);
    }
    
    @Override
    public MultiTimeNoteJpaRepository multiTimeNoteRepository() {
    	return (MultiTimeNoteJpaRepository) repositories.get(MultiTimeNoteJpaRepository.class);
    }
    
    @Override
    public UserJpaRepository userRepository() {
    	return (UserJpaRepository) repositories.get(UserJpaRepository.class);
    }
}
