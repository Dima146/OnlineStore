package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.Product;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.ProductDao;
import by.epam.onlinestore.dao.connectionpool.ConnectionPool;
import by.epam.onlinestore.dao.connectionpool.ConnectionPoolException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductDaoTest {

    ProductDao productDao = new ProductDaoImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().initPoolData();
    }

    @Test
    public void testFindingProductByCategoryId_ReturnList() throws DaoException {

        List<Product> expected = new ArrayList<>();

        Product product1 = new Product(1, "Lenovo IdeaPad 5 Pro 14ITL6 82L3004TRK", "14.0 2240 x 1400 IPS, 60 Гц, несенсорный, Intel Core i5 1135G7 2400 ГГц Tiger Lake, 16 ГБ, SSD 512 ГБ, дискретная видеокарта NVIDIA NVIDIA GeForce MX450 2 ГБ GDDR6, без ОС, цвет крышки серый", 3000, true, "laptops/Lenovo_IdeaPad_5Pro_14ITL6_82L3004TRK.jpg", 4, 1);
        Product product2 = new Product(2, "Honor MagicBook X14 NBR-WAI9 (5301AAPL)", "14.0 1920 x 1080 IPS, 60 Гц, несенсорный, Intel Core i3 10110U 2100 ГГц Comet Lake, 8 ГБ, SSD 256 ГБ, встроенная видеокарта UHD Graphics, Windows 10 Home, цвет крышки серый матовый", 2500, true, "laptops/Honor_MagicBook_X14_NBR-WAI9_(5301AAPL).jpg", 3, 1);

        expected.add(product1);
        expected.add(product2);

        List<Product> actual = productDao.findProductByCategoryId(1);
        assertEquals(expected, actual);

    }

    @Test
    public void testFindingProductById_ReturnProduct() throws DaoException {

        Optional<Product> actual = productDao.findProductById(1);
        actual.ifPresent(product -> System.out.println(actual));

    }

}
