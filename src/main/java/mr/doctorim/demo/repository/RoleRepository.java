package mr.doctorim.demo.repository;

import mr.doctorim.demo.model.Role;
import mr.doctorim.demo.model.enumeration.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>  {
        Role findRoleByName(RolesEnum name);
}
