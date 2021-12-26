package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSplice {
    @Autowired
    BookRepository bookRepository;

    List<Book> makeTestBookList(){
        Book testBook1 = new Book("Angels and Demons", "SEQ2020123101", "Penguin Press");
        Book testBook2 = new Book("Inferno", "SEQ2020123102", "Penguin Press");
        Book testBook3 = new Book("Da Vinci Code", "SEQ2020123103", "Penguin Press");

        ArrayList <Book>testBookList = new ArrayList<>();

        testBookList.add(testBook1);
        testBookList.add(testBook2);
        testBookList.add(testBook3);

        return testBookList;
    }

    @Commit
    @Order(1)
    @Test
    void testDataSetupWithSavingTransactionalContext(){
        long countBooksBeforeSetup = bookRepository.count();
        assertThat(countBooksBeforeSetup).isEqualTo(0L);

        for (Book book : this.makeTestBookList()){
            bookRepository.save(book);
        }

        long countBooksAfterSetup = bookRepository.count();
        assertThat(countBooksAfterSetup).isEqualTo(3L);
    }

    @Order(2)
    @Test
    void testDataSetupWithoutSavingTransactionalContext(){
        long countBooksBeforeSetup = bookRepository.count();
        assertThat(countBooksBeforeSetup).isEqualTo(3L);
        for (Book book : this.makeTestBookList()){
            bookRepository.save(book);
        }
        long countBooksAfterSetup = bookRepository.count();
        assertThat(countBooksAfterSetup).isEqualTo(6L);


    }

    @Test
    @Order(3)
    void testJpaTestSliceUsageMethod1() {

        long initialCount = bookRepository.count();
        assertThat(initialCount).isEqualTo(3l);

        Book testBook4 = new Book("Guns and Germs", "SEQ2020123104", "Oxford house London");
        bookRepository.save(testBook4);

        long countAfterUpdate = bookRepository.count();
        assertThat(countAfterUpdate).isEqualTo(4l);


    }
}
