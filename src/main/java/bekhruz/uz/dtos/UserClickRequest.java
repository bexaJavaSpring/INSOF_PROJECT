package bekhruz.uz.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserClickRequest {
    private String x_count;
    private String y_count;
    private String username;
}
