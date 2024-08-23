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
import java.util.ArrayList;
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

    public List<CountResponseDto> saveCountsOfClick(UserClickRequest request) {
        List<CountResponseDto> list = new ArrayList<>();
        if (request.getX_count() != null) {
            UserClicksCount userClicksCount = new UserClicksCount();
            userClicksCount.setXCount(request.getX_count());
            userClicksCount.setCreationOfClickX(LocalDateTime.now());
            userClickCountRepository.save(userClicksCount);
        }
        if (request.getY_count() != null) {
            UserClicksCount userClicksCount = new UserClicksCount();
            userClicksCount.setYCount(request.getY_count());
            userClicksCount.setCreationOfClickY(LocalDateTime.now());
            userClickCountRepository.save(userClicksCount);
        }
        for (UserClicksCount clicksCount : userClickCountRepository.findAll()) {
            CountResponseDto dto = new CountResponseDto();
            dto.setX_count(clicksCount.getXCount());
            dto.setY_count(clicksCount.getYCount());
            dto.setDateOfXCount(String.valueOf(clicksCount.getCreationOfClickX()));
            dto.setDateOfYCount(clicksCount.getCreationOfClickY().toString());
            list.add(dto);
        }
        return list;
    }
}
