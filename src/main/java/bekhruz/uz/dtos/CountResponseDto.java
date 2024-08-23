package bekhruz.uz.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CountResponseDto {
    private Integer x_count;
    private Integer y_count;
    private String dateOfXCount;
    private String dateOfYCount;

}
