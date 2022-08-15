package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Address;
import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driven Design", "123451");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book1);

        Author rod = new Author("Rod", "Johnson");
        Book book2 = new Book("J2EE development without EJB", "123452");

        authorRepository.save(rod);
        bookRepository.save(book2);

        Publisher pub1 = new Publisher("O Rielly", "1234 ABCD Lane", "San Jose", "CA", "95134");
        book1.setPublisher(pub1);
        book2.setPublisher(pub1);
        pub1.getBooks().add(book1);
        pub1.getBooks().add(book2);

        publisherRepository.save(pub1);



        System.out.println("Started in BootStrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publishers has books: " + pub1.getBooks().size());

    }
}
