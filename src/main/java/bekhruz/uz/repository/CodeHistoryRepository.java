package bekhruz.uz.repository;

import bekhruz.uz.domain.CodeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeHistoryRepository extends JpaRepository<CodeHistory, Long> {
    CodeHistory findByUserIdAndCode(Long userId,String code);

    List<CodeHistory> findByUserId(Long userId);
}
