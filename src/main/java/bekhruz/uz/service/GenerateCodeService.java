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
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        UserClicksCount save = null;
        User user = userRepository.findByUsername(request.getUsername());
        UserClicksCount count = userClickCountRepository.findByUserId(user.getId());
        if (request.getX_count() != null) {
            if (count == null) {
                UserClicksCount userClicksCount = new UserClicksCount();
                userClicksCount.setXCount(1);
                userClicksCount.setCreationOfClickX(formattedDateTime);
                userClicksCount.setYCount(0);
                userClicksCount.setCreationOfClickY("");
                userClicksCount.setUserId(user.getId());
                save = userClickCountRepository.save(userClicksCount);
            } else {
                if (count.getXCount() == null) {
                    Optional<UserClicksCount> byId = userClickCountRepository.findById(count.getId());
                    UserClicksCount userClicksCount = byId.get();
                    userClicksCount.setXCount(1);
                    userClicksCount.setCreationOfClickX(formattedDateTime);
                    userClicksCount.setUserId(user.getId());
                    save = userClickCountRepository.save(userClicksCount);
                } else {
                    count.setXCount(count.getXCount() + 1);
                    count.setCreationOfClickX(formattedDateTime);
                    save = userClickCountRepository.save(count);
                }
            }
        }
        if (request.getY_count() != null) {
            if (count == null) {
                UserClicksCount userClicksCount = new UserClicksCount();
                userClicksCount.setYCount(1);
                userClicksCount.setXCount(0);
                userClicksCount.setCreationOfClickY("");
                userClicksCount.setCreationOfClickY(formattedDateTime);
                userClicksCount.setUserId(user.getId());
                save = userClickCountRepository.save(userClicksCount);
            } else {
                if (count.getYCount() == null) {
                    Optional<UserClicksCount> byId = userClickCountRepository.findById(count.getId());
                    UserClicksCount userClicksCount = byId.get();
                    userClicksCount.setYCount(1);
                    userClicksCount.setCreationOfClickY(formattedDateTime);
                    userClicksCount.setUserId(user.getId());
                    save = userClickCountRepository.save(userClicksCount);
                } else {
                    count.setYCount(count.getYCount() + 1);
                    count.setCreationOfClickY(formattedDateTime);
                    save = userClickCountRepository.save(count);
                }
            }
        }
        if (request.getX_count() != null) {
            return CountResponseDto.builder()
                    .x_count(save.getXCount())
                    .y_count(save.getYCount())
                    .dateOfYCount(save.getCreationOfClickY())
                    .dateOfXCount(String.valueOf(save.getCreationOfClickX()))
                    .build();
        } else {
            return CountResponseDto.builder()
                    .y_count(save.getYCount())
                    .x_count(save.getXCount())
                    .dateOfXCount(save.getCreationOfClickX())
                    .dateOfYCount(String.valueOf(save.getCreationOfClickY()))
                    .build();
        }
    }
}
