package pl.saqie.SaQieBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.saqie.SaQieBlog.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("update User u set u.locked=true where u.id=?1")
    void blockUser(Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.locked=false where u.id=?1")
    void unlockUser(Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.firstName=?1, u.lastName=?2 where u.id=?3")
    void updateUserData(String firstName, String lastName, Long Id);

    @Transactional
    @Modifying
    @Query("update User u set u.writedComments=0, u.writedPosts=0 where u.id=?1")
    void resetUserStatistics(Long id);

    @Transactional
    @Modifying
    @Query("update User u set u.role='ADMIN' where u.id = ?1")
    void setAdminRole(Long id);

    Optional<User> findByUsername(String username);

    @Override
    List<User> findAll();

    @Override
    User getById(Long aLong);

    @Override
    <S extends User> S save(S s);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Transactional
    @Modifying
    @Query("update User u set u.role='USER' where u.id=?1")
    void setUserRole(Long id);
}

