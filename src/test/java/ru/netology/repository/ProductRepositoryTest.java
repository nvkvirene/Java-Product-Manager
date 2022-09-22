package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.domain.Book;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;
import ru.netology.manager.ProductManager;

public class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product item1 = new Book(150, "Книга 1", 300,"Автор 1");
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
    public void showSaveAll() {
        Product[] expected = {item1, item2, item3, item4, item5, item6, item7, item8};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldSaveAllLength() {
        int expected = 8;
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
        repository.removeBiId(item3.getId());

        Product[] expected = {item1, item2, item4, item5, item6, item7, item8};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindAll() {
        repository.findAll();

        Product[] expected = {item1, item2, item3, item4, item5,item6, item7, item8};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBiUd() {
        manager.findBiId(650);

        Product[] expected = {item1, item2, item3, item4, item5, item7, item8};
        Product[] actual = {item1, item2, item3, item4, item5, item7, item8};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerationNotFoundException() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.removeBiId(-100);
        });
    }

    @Test
    public void shouldDeletingAnExistingElement() {
        repository.removeBiId(650);

        Product[] expected = new Product[] {item1, item2, item3, item4, item5, item7, item8};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}
