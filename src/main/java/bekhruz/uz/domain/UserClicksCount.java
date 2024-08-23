package bekhruz.uz.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    @Column(name = "creation_of_click_x", columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime creationOfClickX;
    @Column(name = "creation_of_click_y", columnDefinition = "TIMESTAMP DEFAULT now()")
    private LocalDateTime creationOfClickY;
    @Column(name = "x_count")
    private Integer xCount;
    @Column(name = "y_count")
    private Integer yCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;
}
