package capstone.bookitty.domain.repository;

import capstone.bookitty.domain.entity.BookState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStateRepository extends JpaRepository<BookState,Long> {

}
