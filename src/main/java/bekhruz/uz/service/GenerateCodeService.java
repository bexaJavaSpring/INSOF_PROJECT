package bekhruz.uz.service;

import bekhruz.uz.domain.CodeHistory;
import bekhruz.uz.domain.User;
import bekhruz.uz.domain.UserClicksCount;
import bekhruz.uz.dtos.CodeHistoryDto;
import bekhruz.uz.dtos.CountResponseDto;
import bekhruz.uz.dtos.UserClickRequest;
import bekhruz.uz.repository.CodeHistoryRepository;
import bekhruz.uz.repository.UserClickCountRepository;
import bekhruz.uz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GenerateCodeService {

    private final UserClickCountRepository userClickCountRepository;
    private final UserRepository userRepository;

    public String generateCode() {
        Random random = new Random();
        int fourDigitNumber = 1000 + random.nextInt(9000);
        return String.valueOf(fourDigitNumber);
    }

    public CountResponseDto saveCountsOfClick(UserClickRequest request) {
        UserClicksCount save = null;
        if (request.getX_count() != null) {
            UserClicksCount userClicksCount = new UserClicksCount();
            userClicksCount.setXCount(request.getX_count());
            userClicksCount.setCreationOfClickX(LocalDateTime.now());
            save = userClickCountRepository.save(userClicksCount);
        }
        if (request.getY_count() != null) {
            UserClicksCount userClicksCount = new UserClicksCount();
            userClicksCount.setYCount(request.getY_count());
            userClicksCount.setCreationOfClickY(LocalDateTime.now());
            save = userClickCountRepository.save(userClicksCount);
        }
        return CountResponseDto.builder()
                .x_count(save.getXCount())
                .y_count(save.getYCount())
                .dateOfXCount(String.valueOf(save.getCreationOfClickX()))
                .dateOfYCount(String.valueOf(save.getCreationOfClickY()))
                .build();
    }
}
