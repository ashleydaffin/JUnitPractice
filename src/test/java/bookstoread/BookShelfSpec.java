package bookstoread;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("A bookshelf")
public class BookShelfSpec {

    private BookShelfSpec(TestInfo testInfo) {
        System.out.println("From constructor...Working on test " + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("is empty when no book is added to it")
    public void shelfEmptyWhenNoBookAdded(TestInfo testInfo) throws Exception {
        System.out.println("From test method...Working on test " + testInfo.getDisplayName());
        BookShelf shelf = new BookShelf();
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
    }

    @Test
    void bookshelfContainsTwoBooksWhenTwoBooksAdded(){
        BookShelf shelf = new BookShelf();
        shelf.add("Effective Java");
        shelf.add("Code Complete");
        List<String> books = shelf.books();
        assertEquals(2, books.size(), () -> "BookShelf should have two books.");
    }
}