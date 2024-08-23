package bekhruz.uz.dtos;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserClickRequest implements Serializable {
    private String x_count;
    private String y_count;
    private String username;
}
