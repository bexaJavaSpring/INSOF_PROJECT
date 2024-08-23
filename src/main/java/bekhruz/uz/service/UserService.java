package bekhruz.uz.service;

import bekhruz.uz.domain.CodeHistory;
import bekhruz.uz.domain.User;
import bekhruz.uz.dtos.CodeHistoryDto;
import bekhruz.uz.dtos.UserClickRequest;
import bekhruz.uz.dtos.UserRegisterRequest;
import bekhruz.uz.repository.CodeHistoryRepository;
import bekhruz.uz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CodeHistoryRepository codeHistoryRepository;

    public String register(UserRegisterRequest request, Model model) {
        User userBuilder = User.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .build();
        userRepository.save(userBuilder);
        return "User successfully registered!";
    }

    public List<CodeHistoryDto> saveCode(String code, String username) {
        CodeHistory codeHistory = new CodeHistory();
        codeHistory.setCode(code);
        codeHistory.setCreatedAt(LocalDateTime.now());
        User user = userRepository.findByUsername(username);
        codeHistory.setUser(user);
        codeHistoryRepository.save(codeHistory);
        List<CodeHistoryDto> list = new ArrayList<>();
        for (CodeHistory history : codeHistoryRepository.findAll()) {
            User save = userRepository.findById(history.getUser().getId()).get();
            CodeHistoryDto codeHistoryDto = CodeHistoryDto.builder()
                    .username(save.getUsername())
                    .code(history.getCode())
                    .creationOfCode(String.valueOf(history.getCreatedAt()))
                    .build();
            list.add(codeHistoryDto);
        }
        return list;
    }

    public CodeHistory login(String code, String username) {
        User user = userRepository.findByUsername(username);
        CodeHistory codeHistory = codeHistoryRepository.findByUserIdAndCode(user.getId(), code);
        return codeHistory;
    }
}
