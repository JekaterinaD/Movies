package be.vdab.movies.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMovie_IdOrderByMomentDesc(Long movieId);
}