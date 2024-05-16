package capstone.bookitty.domain.repository;

import capstone.bookitty.domain.entity.Star;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StarRepository extends JpaRepository<Star, Long> {
    boolean existsByMemberIdAndIsbn(Long memberId, String isbn);
    Page<Star> findByIsbn(String isbn, Pageable pageable);
    Page<Star> findByMemberId(Long memberId, Pageable pageable);
}
