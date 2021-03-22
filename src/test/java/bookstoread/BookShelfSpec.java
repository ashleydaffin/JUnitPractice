package bookstoread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf")
public class BookShelfSpec {

    private BookShelf shelf;

    @BeforeEach
    void init() throws Exception{
        shelf = new BookShelf();
    }

    private BookShelfSpec(TestInfo testInfo) {
        System.out.println("From constructor...Working on test " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("is empty when no book is added to it")
    public void shelfEmptyWhenNoBookAdded(TestInfo testInfo) throws Exception {
        System.out.println("From test method...Working on test " + testInfo.getDisplayName());
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add("Effective Java", "Code Complete");
        List<String> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }

    @Test
    public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
        shelf.add();
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should empty");

    }

    @Test
    void booksReturnedFromBookShelfIsImmutableForClient() {
        shelf.add("Effective Java", "Code Complete");
        List<String> books = shelf.books();
        try {
            books.add("The Mythical Man-Month");
            fail(() -> "Should not be able to add book to books");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
        }
    }

    @Test
    void bookshelfArrangedByBookTitle(){
        shelf.add("Effective Java", "Code Complete","The Mythical Man-Month");
        List<String> books = shelf.arrange();
        assertEquals(Arrays.asList("Code Complete", "Effective Java", "The Mythical Man-Month"), books, () -> "Books in a bookshelf should be arranged lexicographically by book title");

    }
    @Test
    void booksInBookShelfAreInInsertionOrderAfterCallingArrange(){
        shelf.add("Effective Java", "Code Complete","The Mythical Man-Month");
        shelf.arrange();
        List<String> books = shelf.books();
        assertEquals(Arrays.asList("Effective Java", "Code Complete","The Mythical Man-Month"), books, () -> "Books in bookself are in insertion order");
    }
}