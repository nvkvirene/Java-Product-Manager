package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;


public class ProductManagerTest {

    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product item1 = new Book(150, "Книга 1", 300, "Автор 1");
    Product item2 = new Book(250, "Книга 2", 600, "Автор 2");
    Product item3 = new Book(350, "Книга 3", 1_000, "Автор 3");
    Product item4 = new Book(450, "Книга 4", 1_460, "Автор 4");
    Product item5 = new Smartphone(550, "Телефон 1", 7_500, "Производитель 1");
    Product item6 = new Smartphone(650, "Телефон 2", 10_700, "Производитель 2");
    Product item7 = new Smartphone(750, "Телефон 3", 80_900, "Производитель 3");
    Product item8 = new Smartphone(850, "Телефон 4", 100_000, "Производитель 4");

    @BeforeEach
    public void setup() {
        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);
        manager.add(item5);
        manager.add(item6);
        manager.add(item7);
        manager.add(item8);
    }


    @Test
    public void shouldAddProducts() {
        Product[] expected = new Product[]{item1, item2, item3, item4, item5, item6, item7, item8};
        Product[] actual = manager.getAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByName() {
        Product[] expected = {item4};
        Product[] actual = manager.searchBy("Книга 4");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldMultipleNameSearch() {
        Product[] expected = {item5, item6, item7, item8};
        Product[] actual = manager.searchBy("Телефон");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldEmptySearch() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("0");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFound() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("ABC");

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldSetIdNamePrice() {
        Product item = new Product(2, "Product", 90);
        item.setId(1);
        item.setName("Продукт");
        item.setPrice(900);

        Product[] expected = new Product[]{item1, item2, item3, item4, item5, item6, item7, item8};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetIdNamePrice() {
        Product item = new Product(19, "Продукт", 8_900);

        Assertions.assertEquals(19, item.getId());
        Assertions.assertEquals("Продукт", item.getName());
        Assertions.assertEquals(8_900, item.getPrice());
    }

    @Test
    public void shouldGetIdNamePriceAuthor() {
        Book item = new Book(70, "Книга", 1_050, "Автор");

        Assertions.assertEquals(70, item.getId());
        Assertions.assertEquals("Книга", item.getName());
        Assertions.assertEquals(1_050, item.getPrice());
        Assertions.assertEquals("Автор", item.getAuthor());
    }

    @Test
    public void shouldSetIdNamePriceAuthor() {
        Book item = new Book(2, "Book", 90, "Author");
        item.setId(70);
        item.setName("Книга");
        item.setPrice(90);
        item.setAuthor("Автор");

        Product[] expected = new Product[]{item1, item2, item3, item4, item5, item6, item7, item8};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGetIdNamePriceManufacturer() {
        Smartphone item = new Smartphone(102030, "Телефон", 100_900, "Производитель");

        Assertions.assertEquals(102030, item.getId());
        Assertions.assertEquals("Телефон", item.getName());
        Assertions.assertEquals(100_900, item.getPrice());
        Assertions.assertEquals("Производитель", item.getManufacturer());
    }

    @Test
    public void shouldSetIdNamePriceManufacturer() {
        Smartphone item = new Smartphone(3, "Smartphone", 9, "Manufacturer");
        item.setId(4);
        item.setName("Телефон");
        item.setPrice(58);
        item.setManufacturer("Производитель");

        Product[] expected = new Product[]{item1, item2, item3, item4, item5, item6, item7, item8};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void matchesTrue() {
        manager.searchBy("1_460");

        Assertions.assertFalse(manager.matches(item4, "1_460"));
    }

    @Test
    public void matchesFalse() {
        manager.searchBy("Товар");

        Assertions.assertFalse(manager.matches(item7, "Производитель 3"));
    }
}
