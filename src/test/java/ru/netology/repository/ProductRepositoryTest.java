package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;

public class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product product = new Product(1, "Product rich model", 10_999);
    Book book = new Book(2, "Book rich model", 89, "Author");
    Smartphone smartphone = new Smartphone(3, "Smartphone rich model", 4_789, "Manufacturer");

    @BeforeEach
    public void setup() {
        manager.add(product);
        manager.add(book);
        manager.add(smartphone);
    }


    @Test
    public void showSaveAll() {
        Product[] expected = {product, book, smartphone};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSaveAllLength() {
        int expected = 3;
        Product[] actual = repository.findAll();
        Assertions.assertEquals(expected, actual.length);
    }

    @Test
    public void shouldSave() {
        repository.save(new Book(45, "Книга", 7_900, "Author"));

        int expected = 45;
        Product[] actual = repository.findAll();
    }

    @Test
    public void shouldRemoveById() {
        repository.removeBiId(book.getId());

        Product[] expected = {product, smartphone};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindAll() {
        repository.findAll();

        Product[] expected = {product, book, smartphone};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBiUd() {
        manager.findBiId(2);

        Product[] expected = {product, smartphone};
        Product[] actual = {product, smartphone};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMatchesProductBookSmartphone() {
        Assertions.assertTrue(manager.matches(product, "rich model"));
        Assertions.assertTrue(manager.matches(book, "rich model"));
        Assertions.assertTrue(manager.matches(smartphone, "rich model"));
    }

    @Test
    public void shouldMatchesFalseProductBookSmartphone() {
        Assertions.assertFalse(manager.matches(product, "1"));
        Assertions.assertFalse(manager.matches(book, "2"));
        Assertions.assertFalse(manager.matches(smartphone, "3"));
    }
}
