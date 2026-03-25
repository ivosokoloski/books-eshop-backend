package mk.ukim.finki.wp.eimt_lab.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private LocalDateTime eventTime;

    private String eventType;

    public ActivityLog(Book bookName, String eventType) {
        this.bookName = String.valueOf(bookName);
        this.eventTime = LocalDateTime.now();
        this.eventType = eventType;
    }
}