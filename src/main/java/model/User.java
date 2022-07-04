package model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name="User.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name="User.findByName", query = "SELECT u FROM User u WHERE u.username = :username"),
        @NamedQuery(name="User.findByMail", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 1000)
    private byte[] passwordHash;

    @Column(name = "password_salt", nullable = false, length = 1000)
    private byte[] passwordSalt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Rating> ratings = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public byte[] getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(byte[] passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

}