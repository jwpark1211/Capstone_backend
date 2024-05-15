package capstone.bookitty.domain.repository;

import capstone.bookitty.domain.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StarRepository extends JpaRepository<Star, Long> {
    boolean existsByMemberIdAndIsbn(Long memberId, String isbn);
    List<Star> findByIsbn(String isbn);
    List<Star> findByMemberId(Long memberId);
}
