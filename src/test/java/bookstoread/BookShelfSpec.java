package bookstoread;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("A bookshelf")
public class BookShelfSpec {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;

    @BeforeEach
    void init() throws Exception{
        shelf = new BookShelf();
        effectiveJava = new Book("Effective Java", "Joshua Bloch", LocalDate.of(2008, Month.MAY, 8));
        codeComplete = new Book("Code Complete", "Steve McConnel", LocalDate.of(2004, Month.JUNE, 9));
        mythicalManMonth = new Book("The Mythical Man-Month", "Frederick Phillips Brooks", LocalDate.of(1975, Month.JANUARY, 1));
        cleanCode = new Book("Clean Code", "Robert Martin", LocalDate.of(2008, Month.AUGUST, 1));
    }

    private BookShelfSpec(TestInfo testInfo) {
        System.out.println("From constructor...Working on test " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("is empty when no book is added to it")
    public void shelfEmptyWhenNoBookAdded(TestInfo testInfo) throws Exception {
        System.out.println("From test method...Working on test " + testInfo.getDisplayName());
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    @Test
    @DisplayName("bookshelf contains two books when two books are added")
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }

    @Test
    @DisplayName("empty bookshelf remains empty when add is called without books")
    public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        shelf.add();
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should empty");

    }

    @Test
    @DisplayName("bookshelf returns an immutable books collection to client")
    void booksReturnedFromBookShelfIsImmutableForClient() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        try {
            books.add(mythicalManMonth);
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }
    @Disabled ("Needs to implement Comparator")
    @Test
    @DisplayName("bookshelf is arranged lexicographically by book title")
    void bookshelfArrangedByBookTitle(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        assertEquals(Arrays.asList(codeComplete, effectiveJava, mythicalManMonth), books, () -> "Books in a bookshelf should be arranged lexicographically by book title");

    }
    @Test
    @DisplayName("bookshelf remains in insertion order after calling arrange mehtod")
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        shelf.arrange();
        List<Book> books = shelf.books();
        assertEquals(Arrays.asList(effectiveJava, codeComplete, mythicalManMonth), books, () -> "Books in bookshelf are in insertion order");
    }
    @Test
    @DisplayName("bookshelf is arrange by user provided criteria(by book title reversed lexicographically")
    void bookshelfArrangedByUserProvidedCriteria(){
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
        List<Book> books = shelf.arrange(reversed);
        assertThat(books).isSortedAccordingTo(reversed);
    }
    @Test
    @DisplayName("books inside bookshelf are grouped by publication year")
    void groupBooksInsideBookShelfByPublicationYear(){
        shelf.add(effectiveJava,codeComplete, mythicalManMonth, cleanCode);

        Map<Year, List<Book>> booksByPublicationYear = shelf.groupByPublicationYear();

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2008))
                .containsValues(Arrays.asList(effectiveJava, cleanCode));

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(2004))
                .containsValues(singletonList(codeComplete));

        assertThat(booksByPublicationYear)
                .containsKey(Year.of(1975))
                .containsValues(singletonList(mythicalManMonth));


    }
}