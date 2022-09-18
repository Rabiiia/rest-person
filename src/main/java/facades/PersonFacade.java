package facades;



import dtos.PersonDTO;
import entities.Person;
import errorhandling.EntityNotFoundException;
import errorhandling.InternalErrorException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

//public class PersonFacade implements IDataFacade<Person> hvis du beslutter dig med en datafacade
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //public static IDataFacade<Person> getFacade( EntityManagerFactory _emf){}
    public static PersonFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }


    public PersonDTO create(PersonDTO personDTO) {
        Person personEntity = new Person(personDTO.getFirstName(), personDTO.getLastName(), personDTO.getPhoneNumber());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(personEntity);
    }


    public PersonDTO getById(int id) throws EntityNotFoundException {
            EntityManager em = getEntityManager();
            Person fromDB = em.find(Person.class, id);
            if (fromDB == null)
                throw new EntityNotFoundException("No person with provided id " + id + " was found");
            return new PersonDTO(fromDB);

    }


    public List<PersonDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return PersonDTO.getDtos(persons);
    }


    public PersonDTO update(PersonDTO personDTO){
        EntityManager em = getEntityManager();
//        Person fromDB = em.find(Person.class, personDTO.getId());
//        if(fromDB == null)
//            throw new EntityNotFoundException("No such Person with id: "+personDTO.getId());

        Person personEntity = new Person(personDTO.getId(), personDTO.getFirstName(), personDTO.getLastName(), personDTO.getPhoneNumber());
        try {
            em.getTransaction().begin();
            em.merge(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(personEntity);
    }

    //siden ingen address DTO, så man kan lave delete med person og address her?
    //men jeg skal vel create en address først? kan man lave en create address herinde i delet method? (:
    // (PersonDTO personDTO) i stedet for (int id)
    public PersonDTO delete(int id) throws EntityNotFoundException, InternalErrorException {
        EntityManager em = getEntityManager();
        Person fromDB = em.find(Person.class, id);
        if (fromDB == null)
            //throw new EntityNotFoundException("Could not delete, provided id " + id + " does not exist");
        throw new InternalErrorException("Internal Server Problem. We are sorry for the inconvenience");
        em.getTransaction().begin();
        em.remove(fromDB);
        em.getTransaction().commit();
        return new PersonDTO(fromDB);
    }


}