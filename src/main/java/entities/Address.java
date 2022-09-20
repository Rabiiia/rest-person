package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Integer id;

    @OneToMany(mappedBy = "address")
    private Set<Person> people = new LinkedHashSet<>();

    @Size(max = 45)
    @NotNull
    @Column(name = "street", nullable = false, length = 45)
    private String street;

    @Size(max = 45)
    @NotNull
    @Column(name = "zip", nullable = false, length = 45)
    private String zip;

    @Size(max = 45)
    @NotNull
    @Column(name = "city", nullable = false, length = 45)
    private String city;

    public Address() {
    }

    public Address(String street, String zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }
}
