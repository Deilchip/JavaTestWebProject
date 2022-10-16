package sbsecurity.hospital.Reposiroty;

import org.springframework.data.repository.CrudRepository;
import sbsecurity.hospital.entity.UserRole;

public interface AppRoleRepository extends CrudRepository<UserRole, Long> {
}
