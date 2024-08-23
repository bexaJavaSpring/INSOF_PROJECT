package bekhruz.uz.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CodeHistoryDto {
    private String username;
    private String code;
    private String creationOfCode;
}
