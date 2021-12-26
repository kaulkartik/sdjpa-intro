package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private  final  BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        bookRepository.deleteAll();
        /* make an object for the domain driven design book*/
        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse");

        /* print the ID for the domain driven design book*/
        System.out.println("Id: " +bookDDD.getId() );

        /*
            1. Since the bookRepository inherits
              the JPA repository the save method
              comes automatically.
           2. Save the Domain driven design book
           3. Print the ID
         */
        Book savedDDD = bookRepository.save(bookDDD);
        System.out.println("Id: " +bookDDD.getId() );

        Book bookSIA = new Book("Spring In Action", "456", "Oriley");
        System.out.println("Id: " +bookSIA.getId() );

        Book savedSIA = bookRepository.save(bookSIA);
        System.out.println("Id: " +bookSIA.getId() );

        bookRepository.findAll().forEach( book -> {
            System.out.println(" Book ID is " + book.getId());
            System.out.println(" Book Title is " + book.getTitle());
        });

    }
}
