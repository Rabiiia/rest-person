//package facades;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import dtos.PersonDTO;
//import entities.Person;
//import errorhandling.EntityNotFoundException;
//import errorhandling.InternalErrorException;
//import org.junit.jupiter.api.*;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
////master 3
//@Disabled
//
//public class PersonTest {
//
//    private static EntityManagerFactory emf;
//    //private static IDataFacade<Person> facade;
//    private static PersonFacade facade;
//
//    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//
//    Person p1,p2, p3;
//
//
//
//    @BeforeAll
//    public static void setUpClass() {
//        emf = EMF_Creator.createEntityManagerFactoryForTest();
//        facade = PersonFacade.getFacade(emf);
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
////        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
//    }
//
//    // Setup the DataBase in a known state BEFORE EACH TEST
//    //TODO -- Make sure to change the code below to use YOUR OWN entity class
//    @BeforeEach
//    public void setUp() {
//        EntityManager em = emf.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
//            p1 = new Person("Birgitte", "Gaard", "77665544");
//            p2 = new Person("Kasper", "Møller", "11549988");
//            //p3 = new Person("Julie", "Vidder", "0990999");
//
//            em.persist(p1);
//            em.persist(p2);
//            //em.persist(p3);
//
//
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @AfterEach
//    public void tearDown() {
////        Remove any data after each test was run
//    }
//
//    // TODO: Delete or change this method
//    @Test
//    public void createPerson()  {
//        Person expected = new Person("TestPerson","Testperson", "TestNumber");
//        PersonDTO actual   = facade.create(new PersonDTO(expected));
//        assertTrue(actual.getId()!=0);
//        System.out.println(actual.getId());
//    }
//
//    @Test
//    void getPersonById() throws EntityNotFoundException {
//        System.out.println("Testing getbyid(id)");
//        int expected = p1.getId();
//        int actual = facade.getById(expected).getId();
////        assertThrows(EntityNotFoundException.class,() -> {
////            facade.getById(expected);
////                });
//        assertEquals(expected, actual);
//        System.out.println(actual);
//    }
//
////    @Test
////    void getPersonById() throws Exception {
////        int personId = 1;
////        PersonDTO personDTO = facade.getById(personId);
////        assertEquals(personId, personDTO.getId());
////    }
//
//    @Test
//    void getAllPersons() {
//        System.out.println("Testing getAll()");
//        int expected = 2;
//        int actual = facade.getAll().size();
//        assertEquals(expected,actual);
//        System.out.println(actual);
//    }
//
//    @Test
//    void updatePerson() throws EntityNotFoundException {
//        System.out.println("Testing Update(Person first name)");
//        p2.setFirstName("Pippa");
//        Person expected = p2;
//        PersonDTO actual = facade.update(new PersonDTO(expected));
//        assertEquals(expected.getFirstName(),actual.getFirstName());
//        System.out.println(actual.getFirstName());
//
//    }
//
//    @Test
//    void deletePerson() throws EntityNotFoundException, InternalErrorException {
//        System.out.println("Testing delete(id)");
//        PersonDTO person = facade.delete(p1.getId()); //når p1(birgitte) er slettet, så ->
//        int expected = 1; //forventer jeg der er 1 person tilbage, kasper.
//        int actual = facade.getAll().size(); //det tjekker om der faktisk er 1 person tilbage (size)
//        assertEquals(expected, actual); //korrekt: 1 tilbage.
//        assertEquals(person.getId(),p1.getId()); //tjekker om birgitte p1 er equals med p1 (kasper), svar: nej. Kasper p2 overtar Birgittes plads p1 og blir selv p1
//
//
//
//
//
//    }
//
//
//}
