package pl.saqie.SaQieBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.saqie.SaQieBlog.domain.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findByCategoryName(String name);

    @Override
    List<Post> findAll();

    @Override
    Post getById(Long aLong);

    @Override
    <S extends Post> S save(S s);

    @Override
    Optional<Post> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Post post);
}
