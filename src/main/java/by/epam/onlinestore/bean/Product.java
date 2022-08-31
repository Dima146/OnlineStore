package by.epam.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class Product extends Bean implements Serializable {
    private String productName;
    private String productDescription;
    private double productPrice;
    private boolean productStatus;
    private String productPhotoReference;
    private long availableQuantity;
    private long productCategoryId;

    public Product() {
    }

    public Product(long id, String name, String description, double price,
                   boolean status, String photoReference, long availableQuantity,
                   long categoryId) {

        super(id);
        this.productName = name;
        this.productDescription = description;
        this.productPrice = price;
        this.productStatus = status;
        this.productPhotoReference = photoReference;
        this.availableQuantity = availableQuantity;
        this.productCategoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductPhotoReference() {
        return productPhotoReference;
    }

    public void setProductPhotoReference(String productPhotoReference) {
        this.productPhotoReference = productPhotoReference;
    }

    public long getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(long availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return this.getId() == product.getId() && productPrice == product.productPrice
                && productStatus == product.productStatus && availableQuantity == product.availableQuantity
                && productCategoryId == product.productCategoryId && productName.equals(product.productName)
                && productDescription.equals(product.productDescription)
                && productPhotoReference.equals(product.productPhotoReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), productName, productDescription, productPrice, productStatus,
                productPhotoReference, availableQuantity, productCategoryId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + this.getId() +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productStatus=" + productStatus +
                ", productPhotoReference='" + productPhotoReference + '\'' +
                ", availableQuantity=" + availableQuantity +
                ", productCategoryId=" + productCategoryId +
                '}';
    }
}

