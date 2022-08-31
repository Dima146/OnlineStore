package by.epam.onlinestore.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class OrderInformation extends Bean implements Serializable {
    private String deliveryAddress;
    private Date orderDate;
    private Date deliveryDate;
    private String orderStatus;

    public OrderInformation() {
    }

    public OrderInformation(long id, String address, Date orderDate,
                            Date deliveryDate, String status) {

        super(id);
        this.deliveryAddress = address;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.orderStatus = status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInformation userOrder = (OrderInformation) o;
        return this.getId() == userOrder.getId() && deliveryAddress.equals(userOrder.deliveryAddress)
                && orderDate.equals(userOrder.orderDate) && deliveryDate.equals(userOrder.deliveryDate)
                && orderStatus.equals(userOrder.orderStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), deliveryAddress, orderDate, deliveryDate, orderStatus);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + this.getId() +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
