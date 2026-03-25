package mk.ukim.finki.wp.eimt_lab.listener;

import mk.ukim.finki.wp.eimt_lab.event.BookRentedEvent;
import mk.ukim.finki.wp.eimt_lab.model.domain.ActivityLog;
import mk.ukim.finki.wp.eimt_lab.repository.ActivityLogRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class BookRentedListener {
    private final ActivityLogRepository activityLogRepository;

    public BookRentedListener(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onBookRented(BookRentedEvent event) {
        ActivityLog log = new ActivityLog(
                event.book().get(),
                "BOOK_RENTED"
        );
        activityLogRepository.save(log);
    }
}