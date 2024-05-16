package capstone.bookitty.domain.repository;

import capstone.bookitty.domain.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByMemberIdAndIsbn(Long memberId, String isbn);
    Page<Comment> findByMemberId(Long memberId, Pageable pageable);
    Page<Comment> findByIsbn(String isbn, Pageable pageable);
}
