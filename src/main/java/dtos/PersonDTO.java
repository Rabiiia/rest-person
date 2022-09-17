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

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        if(person.getId() != null){
            this.id = person.getId();
        }
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
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
}

