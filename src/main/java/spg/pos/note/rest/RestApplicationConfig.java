package spg.pos.note.rest;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import spg.pos.note.model.DomainConfiguration;
import spg.pos.note.repository.RepositoryConfiguration;
import spg.pos.note.repositoryjpa.RepositoryJpaConfiguration;
import spg.pos.note.service.ServiceConfiguration;
import spg.pos.note.servicejpa.ServiceJpaConfiguration;

@Import(value = {DomainConfiguration.class, RepositoryJpaConfiguration.class, ServiceJpaConfiguration.class})
@Configuration
@ComponentScan(basePackageClasses = RestPackage.class)
public class RestApplicationConfig {
	@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }
}
