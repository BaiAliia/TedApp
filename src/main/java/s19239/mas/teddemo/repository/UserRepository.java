package s19239.mas.teddemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import s19239.mas.teddemo.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
    @Query("from user as u where u.id=:id")
    User findUserById(@Param("id") Long id);
}
