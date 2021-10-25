package mr.doctorim.demo.repository;

import mr.doctorim.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>  {
        User findUserByPhoneNumber(String phoneNumber);
        User findUserByUsername(String username);
}
