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
@Table(name = "t_accounts")
public class Account {
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 36)
    private String id; // use UUID

    public Account() {}

    public Account(String username, float balance) {
        super();
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Column(unique = true, nullable = false)
    private String username;
    private float balance;


}
