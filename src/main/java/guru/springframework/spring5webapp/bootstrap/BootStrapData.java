package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric","Evans");
        Book book = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(book);
        book.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(book);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "34343434");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
//        System.out.println("Number of books " + bookRepository.count());

        Publisher publisher = new Publisher("PubHouse","123 Main", "New York", "NY", "10101");
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        publisherRepository.save(publisher);
        System.out.println("Publisher = " + publisher);
        System.out.println("Publisher count = " + publisherRepository.count());

        System.out.println("Number of books:" + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}
