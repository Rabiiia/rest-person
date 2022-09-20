package dtos;

import entities.Person;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;

public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String street;
    private String zip;
    private String city;

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        if(person.getId() != null){
            this.id = person.getId();
        }
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
        this.street = person.getAddress().getStreet();
        this.zip = person.getAddress().getZip();
        this.city = person.getAddress().getCity();
    }

    public static List<PersonDTO> getDtos(List<Person> person){
        List<PersonDTO> persondtos = new ArrayList();
        person.forEach(p->persondtos.add(new PersonDTO(p)));
        return persondtos;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

