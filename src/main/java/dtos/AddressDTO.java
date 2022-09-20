package dtos;

import entities.Address;

public class AddressDTO {
    private Integer id;
    public String street;
    public String zip;
    public String city;

    public AddressDTO(Address address) {
        if(address.getId() != null){
            this.id = address.getId();
        }
        this.street = address.getStreet();
        this.zip = address.getZip();
        this.city = address.getCity();
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
