package capstone.bookitty.domain.repository;

import capstone.bookitty.domain.entity.BookState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStateRepository extends JpaRepository<BookState,Long> {
    List<BookState> findByIsbn(String isbn);
    List<BookState> findByMemberId(Long memberId);
    boolean existsByMemberIdAndIsbn(Long memberId, String isbn);

}