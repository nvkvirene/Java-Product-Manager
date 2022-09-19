package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {

    protected Product[] items = new Product[0];


    //Сохранение продуктов
    public void save(Product item) {
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

}
