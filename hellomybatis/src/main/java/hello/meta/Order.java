package hello.meta;

import java.sql.Date;

/**
 * Created by min on 17-1-10.
 */
public class Order {

    private Integer orderId;
    private Integer userId;
    private Date orderDate;

    public Order() {

    }

    public Order(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order#" + this.orderId;
    }
}
