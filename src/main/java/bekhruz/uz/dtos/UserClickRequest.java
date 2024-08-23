package bekhruz.uz.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserClickRequest {
    private Integer x_count;
    private Integer y_count;
}
