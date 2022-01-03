package pl.saqie.SaQieBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.saqie.SaQieBlog.domain.Comments;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    @Override
    List<Comments> findAll();

    List<Comments> findByPostId(Long id);

    @Override
    Comments getById(Long aLong);

    @Override
    <S extends Comments> S save(S s);

    @Override
    Optional<Comments> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Comments comments);
}
