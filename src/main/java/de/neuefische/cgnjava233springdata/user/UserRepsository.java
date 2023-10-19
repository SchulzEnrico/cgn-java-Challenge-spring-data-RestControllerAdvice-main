package de.neuefische.cgnjava233springdata.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsository extends MongoRepository<User, String> {
}
