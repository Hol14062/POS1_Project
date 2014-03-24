package spg.pos.note.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spg.pos.note.model.DomainConfiguration;
import spg.pos.note.repositoryjpa.RepositoryJpaConfiguration;
import spg.pos.note.service.ServiceConfiguration;

@Configuration
@Import({DomainConfiguration.class, RepositoryJpaConfiguration.class, ServiceConfiguration.class})
public class ServiceTestConfiguration {

}
