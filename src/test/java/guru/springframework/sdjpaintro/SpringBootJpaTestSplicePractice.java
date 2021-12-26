package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringBootJpaTestSplicePractice {

    @Autowired
    BookRepository bookRepository;



    private ArrayList<Book> makeTestData(){
        ArrayList<Book> bookList = new ArrayList<Book>();

        Book testBook1 = new Book("Angels and Demons", "SEQ2020123101", "Penguin Press");
        Book testBook2 = new Book("Inferno", "SEQ2020123102", "Penguin Press");
        Book testBook3 = new Book("Da Vinci Code", "SEQ2020123103", "Penguin Press");

        bookList.add(testBook1);
        bookList.add(testBook2);
        bookList.add(testBook3);
        return bookList;
    }

    void test1(ArrayList<Book> bookList){
        for (Book book: bookList){
            bookRepository.save(book);
        }

    }
}
