package by.epam.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class OrderFromUser extends Bean implements Serializable {

    private int purchaseQuantity;
    private long userId;
    private long productId;
    private long orderInformationId;

    public OrderFromUser() {
    }

    public OrderFromUser(long id, int quantity, long userId, long productId, long orderInformationId) {
        super(id);
        this.purchaseQuantity = quantity;
        this.userId = userId;
        this.productId = productId;
        this.orderInformationId = orderInformationId;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderInformationId() {
        return orderInformationId;
    }

    public void setOrderInformationId(long orderInformationId) {
        this.orderInformationId = orderInformationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFromUser orderFromUser = (OrderFromUser) o;
        return this.getId() == orderFromUser.getId() && purchaseQuantity == orderFromUser.purchaseQuantity
                && userId == orderFromUser.userId && productId == orderFromUser.productId
                && orderInformationId == orderFromUser.orderInformationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), purchaseQuantity, userId, productId, orderInformationId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + this.getId() +
                ", purchaseQuantity=" + purchaseQuantity +
                ", userId=" + userId +
                ", productId=" + productId +
                ", orderInformationId=" + orderInformationId +
                '}';
    }
}
