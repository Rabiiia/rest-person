package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.Person;

import errorhandling.EntityNotFoundException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PersonResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    Person p1,p2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    //private Person p;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            p1 = new Person("Birgitte", "Gaard", "77665544");
            p2 = new Person("Kasper", "Møller", "11549988");

            em.persist(p1);
            em.persist(p2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void postTest() {
        Person person =  new Person("TestPerson","Testperson", "TestNumber");
        PersonDTO personDTO = new PersonDTO(person);
        String requestBody = GSON.toJson(personDTO);

        given()
                .header("Content-type", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/person")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("firstName", equalTo("TestPerson"))
                .body("lastName", equalTo("Testperson"))
                .body("phoneNumber", equalTo("TestNumber"));
    }

    @Test
    public void getAll() throws Exception {
        List<PersonDTO> parentDTOs;

        parentDTOs = given()
                .contentType("application/json")
                .when()
                .get("/person/getAll")
                .then()
                .extract().body().jsonPath().getList("", PersonDTO.class);

        PersonDTO p1DTO = new PersonDTO(p1);
        PersonDTO p2DTO = new PersonDTO(p2);
        assertThat(parentDTOs, containsInAnyOrder(p1DTO, p2DTO));

    }

    @Test
    public void updatePerson() {
       // p2.addChild(c2);
        p1.setFirstName("TestRestAssuredName");
        PersonDTO persondto = new PersonDTO(p1);
        String requestBody = GSON.toJson(persondto);

        given()
                .header("Content-type", ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/person/"+p1.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(p1.getId()))
                .body("firstName", equalTo("TestRestAssuredName"));
                //.body("age", equalTo(23))
                //.body("children", hasItems(hasEntry("name","Alberta")));
    }

    @Test
    public void testGetById() throws EntityNotFoundException {
        given()
                .contentType(ContentType.JSON)
//                .pathParam("id", p1.getId()).when()
                .get("/person/{id}",p1.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("id", equalTo(p1.getId()))
                .body("firstName", equalTo(p1.getFirstName()));
                //.body("children", hasItems(hasEntry("name","Joseph"),hasEntry("name","Alberta")));
    }

    @Test
    public void testDeletePerson() throws EntityNotFoundException {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", p1.getId())
                .delete("/person/{id}")
                .then()
                .statusCode(200)
                .body("id",equalTo(p1.getId()));
    }



}
