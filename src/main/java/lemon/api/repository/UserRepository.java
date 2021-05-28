package lemon.api.repository;

import lemon.api.model.User;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from users u where u.alias = ?1 or u.email = ?2")
    @UniqueElements
    public List<User> getUsersByAliasOrEmail(String alias, String email);
}
