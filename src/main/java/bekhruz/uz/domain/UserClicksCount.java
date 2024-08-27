package bekhruz.uz.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users_clicks_count")
public class UserClicksCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_of_click_x")
    private String creationOfClickX;

    @Column(name = "creation_of_click_y")
    private String creationOfClickY;

    @Column(name = "x_count")
    private Integer xCount;

    @Column(name = "y_count")
    private Integer yCount;

    @Column(name = "user_id")
    private Long userId;
}
