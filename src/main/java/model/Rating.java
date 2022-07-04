package model;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@NamedQueries({
        @NamedQuery(name="Rating.findByUser", query = "SELECT r FROM Rating r WHERE r.user.id = :userId"),
        @NamedQuery(name="Rating.findByOsm", query = "SELECT r FROM Rating r WHERE r.osm.id = :osmId"),
        @NamedQuery(name="Rating.findById", query = "SELECT r FROM Rating r WHERE r.id = :ratingId")
})
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "osm_id", nullable = false)
    private GeoPoint osm;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "txt", nullable = false, length = 2000)
    private String txt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GeoPoint getOsm() {
        return osm;
    }

    public void setOsm(GeoPoint osm) {
        this.osm = osm;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

}