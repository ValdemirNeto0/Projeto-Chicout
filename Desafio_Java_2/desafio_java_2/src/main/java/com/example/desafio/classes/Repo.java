package main.java.com.example.desafio.classes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//Remove @RepositoryRestResource below to disable auto REST api:
@RepositoryRestResource
public interface repositoryName extends CrudRepository<entityName, entityIdType>{}