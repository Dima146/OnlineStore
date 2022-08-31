package by.epam.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class ProductCategory extends Bean implements Serializable {
    private String productCategoryName;

    public ProductCategory() {
    }

    public ProductCategory(long id, String productCategoryName) {
        super(id);
        this.productCategoryName = productCategoryName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory productCategory = (ProductCategory) o;
        return this.getId() == productCategory.getId() && productCategoryName.equals(productCategory.productCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), productCategoryName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + this.getId() +
                ", productCategoryName='" + productCategoryName + '\'' +
                '}';
    }
}
