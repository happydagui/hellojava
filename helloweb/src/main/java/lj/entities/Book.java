package lj.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by min on 17-1-8.
 */
@Entity
@Table(name = "t_books")
public class Book {
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    private String id; // use UUID

    @Column(unique = true, nullable = false)
    private String isbn;

    private String name;

    private float price;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    private int stock = 0;

    // default constructor for entity is required
    public Book() {

    }

    public Book(String name, String isbn, float price, int stock) {
        super();
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
