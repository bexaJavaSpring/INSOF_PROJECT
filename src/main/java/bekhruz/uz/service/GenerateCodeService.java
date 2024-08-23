package bekhruz.uz.service;

import bekhruz.uz.domain.User;
import bekhruz.uz.domain.UserClicksCount;
import bekhruz.uz.dtos.CountResponseDto;
import bekhruz.uz.dtos.UserClickRequest;
import bekhruz.uz.repository.UserClickCountRepository;
import bekhruz.uz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        User user = userRepository.findByUsername(request.getUsername());
        UserClicksCount count = userClickCountRepository.findByUserId(user.getId());
        if (request.getX_count() != null) {
            if (count == null) {
                UserClicksCount userClicksCount = new UserClicksCount();
                userClicksCount.setXCount(Integer.valueOf(request.getX_count()));
                userClicksCount.setCreationOfClickX(LocalDateTime.now());
                userClicksCount.setUserId(user.getId());
                save = userClickCountRepository.save(userClicksCount);
            } else {
                count.setXCount(Integer.valueOf(count.getXCount() + request.getX_count()));
                save = userClickCountRepository.save(count);
            }
        }
        if (request.getY_count() != null) {
            if (count == null) {
                UserClicksCount userClicksCount = new UserClicksCount();
                userClicksCount.setYCount(Integer.valueOf(request.getY_count()));
                userClicksCount.setCreationOfClickY(LocalDateTime.now());
                userClicksCount.setUserId(user.getId());
                save = userClickCountRepository.save(userClicksCount);
            } else {
                count.setYCount(Integer.valueOf(count.getYCount() + request.getY_count()));
                save = userClickCountRepository.save(count);
            }
        }
        if (request.getX_count() != null) {
            return CountResponseDto.builder()
                    .x_count(save.getXCount())
                    .dateOfXCount(String.valueOf(save.getCreationOfClickX()))
                    .build();
        } else {
            return CountResponseDto.builder()
                    .y_count(save.getYCount())
                    .dateOfYCount(String.valueOf(save.getCreationOfClickY()))
                    .build();
        }
    }
}
