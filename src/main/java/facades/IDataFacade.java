package facades;

import dtos.PersonDTO;

import java.util.List;

public interface IDataFacade<T> {

    public PersonDTO createPerson(PersonDTO personDTO);
    public PersonDTO deletePerson(int id) throws Exception;
    public PersonDTO getPersonById(int id) throws Exception;
    public PersonDTO getAllPersons();
    public PersonDTO updatePerson(PersonDTO p) throws Exception ;

}
