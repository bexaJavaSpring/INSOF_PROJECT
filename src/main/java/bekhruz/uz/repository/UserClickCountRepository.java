package bekhruz.uz.repository;

import bekhruz.uz.domain.UserClicksCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClickCountRepository extends JpaRepository<UserClicksCount,Long> {
    UserClicksCount findByUserId(Long userId);
}
