package pl.saqie.SaQieBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.saqie.SaQieBlog.domain.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    @Transactional
    @Modifying
    @Query("update Category c set c.name=?1, c.description=?2 where c.id=?3")
    void updateCategory(String name, String description, Long id);

    @Override
    List<Category> findAll();

    @Override
    Category getById(Long id);

    @Override
    <S extends Category> S save(S s);

    @Override
    Optional<Category> findById(Long id);

    @Override
    boolean existsById(Long id);

    @Override
    void delete(Category category);
}
