package ru.netology.manager;


import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    protected ProductRepository repo;

    public ProductManager(ProductRepository repo) {

        this.repo = repo;
    }

    //Добавление продуктов
    public void add(Product product) {

        repo.save(product);
    }

    public Product[] getAll() {

        return repo.findAll();
    }

    //Нахождение по id
    public void findBiId(int id) {

        repo.removeBiId(id);
    }

    //Возвращение массива найденных товаров
    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repo.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    //Метод определения соответствия товаров product запросу search
    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
