package sbsecurity.hospital.Reposiroty;


import org.springframework.data.repository.CrudRepository;
import sbsecurity.hospital.entity.UserRole;

public interface RoleRepository extends CrudRepository<UserRole, Long> {
}
