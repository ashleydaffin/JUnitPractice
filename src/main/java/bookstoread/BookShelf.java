package bookstoread;


import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }
    public void add(Book... booksToAdd) {
        Arrays.stream(booksToAdd).forEach(books::add);
    }

    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(Comparator<Book> criteria){
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }

    public Map<Year, List<Book>> groupByPublicationYear(){
        return books.stream()
                .collect(groupingBy(book -> Year.of(book.getPublishedOn().getYear())));
    }

    public <K> Map<K, List<Book>> groupBy(Function<Book, K> fx){
        return books
                .stream()
                .collect(groupingBy(fx));

    }
}
