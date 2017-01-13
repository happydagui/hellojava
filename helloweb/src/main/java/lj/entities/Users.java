package lj.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by min on 17-1-7.
 */
@Entity
@Table(name = "t_users")
public class Users {
    @Id
    @Column(name = "id", unique = false, nullable = false, length = 36)
    private String id; // use UUID
    private String name;
    private String password;

    public Users() {}

    public Users(String name, String password) {
        super();
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
