package ru.netology.repository;

import ru.netology.domain.Product;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class ProductRepository {

    protected Product[] items = new Product[0];


    //Сохранение продуктов
    public void save(Product item) {
        if (findBiId(item.getId()) != null) {
            throw new AlreadyExistsException(
                    "Element with id: " + item.getId() + " repeats");
        }
        Product[] tmp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = item;
        items = tmp;
    }

    //Получение всех продуктов
    public Product[] findAll() {

        return items;
    }

    //Удаление по id
    public Product[] removeBiId(int id) {
        if (findBiId(id) == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[items.length - 1];
        int copyToIndex = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[copyToIndex] = item;
                copyToIndex++;
            }
        }
        items = tmp;
        return tmp;
    }

    //Проверка есть ли элемент
    public Product findBiId(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
