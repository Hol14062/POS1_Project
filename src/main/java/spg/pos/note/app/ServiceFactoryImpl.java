package spg.pos.note.app;

import java.util.HashMap;

import spg.pos.note.repositoryjpa.PersistenceFactory;
import spg.pos.note.servicejpa.*;

/**
 * A factory to create the services...
 */
public class ServiceFactoryImpl implements ServiceJpaFactory {

    private final HashMap<Class<?>, ServiceJpa> services = new HashMap<>();

    private PersistenceFactory persistenceFactory;

    public ServiceFactoryImpl(PersistenceFactory persistenceFactory) {
        this.persistenceFactory = persistenceFactory;
    }

	@Override
	public MultiTimeNoteServiceJpa multiTimeNoteService() {
		MultiTimeNoteServiceJpa multiTimeNoteService = new MultiTimeNoteServiceJpa();
		multiTimeNoteService.setMultiTimeNoteJpaRepository(persistenceFactory.multiTimeNoteRepository());
		return multiTimeNoteService;
	}

	@Override
	public SingleTimeNoteServiceJpa singleTimeService() {
		SingleTimeNoteServiceJpa singleTimeNoteService = new SingleTimeNoteServiceJpa();
		singleTimeNoteService.setSingleTimeNoteJpaRepository(persistenceFactory.singleTimeNoteRepository());
		return singleTimeNoteService;
	}

	@Override
	public UserServiceJpa userService() {
		UserServiceJpa userService = new UserServiceJpa();
		userService.setUserJpaRepository(persistenceFactory.userRepository());
		return userService;
	}

    
}
