package mk.ukim.finki.wp.eimt_lab.listener;

import mk.ukim.finki.wp.eimt_lab.event.BookRentedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookRentedListener {

    @EventListener
    public void onBookRented(BookRentedEvent event) {
        System.out.println("--- НОВ НАСТАН: ИЗНАЈМУВАЊЕ ---");
        System.out.println("Корисникот " + event.userEmail() +
                " ја изнајми книгата: " + event.book().getName());
        System.out.println("---------------------------------");
    }
}