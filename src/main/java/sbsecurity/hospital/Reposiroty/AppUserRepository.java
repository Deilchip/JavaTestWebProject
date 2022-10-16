package sbsecurity.hospital.Reposiroty;

import org.springframework.data.repository.CrudRepository;
import sbsecurity.hospital.entity.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
}
