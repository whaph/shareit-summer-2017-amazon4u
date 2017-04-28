package edu.hm.shareit.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import static org.junit.Assert.*;

/**
 * Created by jupiter on 4/28/17.
 */
public class MediaResourceTest {
    static final Book BOOK = new Book("title","author","978-3551551672");
    static final Book ANOTHER_BOOK = new Book("first","author2","978-3551551689");
    static final Disc DISC = new Disc("title","director","079879",0);
    static final String APP_URL = "/";
    static final int PORT = 8082;
    static final String WEBAPP_DIR = "./src/main/webapp/";
    static final Server jetty = new Server(PORT);
    static final JSONObject BOOK_JSON = new JSONObject();
    static final Client CLIENT = ClientBuilder.newClient();
    static final WebTarget BOOK_TARGET = CLIENT.target("http://localhost:8082").path("shareit/media/books");

    @Before
    public void setUp() throws Exception {
        BOOK_JSON.put("title", BOOK.getTitle());
        BOOK_JSON.put("author", BOOK.getAuthor());
        BOOK_JSON.put("isbn", BOOK.getIsbn());

        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        System.out.println("Jetty listening on port " + PORT);
        //jetty.join();
    }


    @After
    public void tearDown() throws Exception {
        jetty.stop();
    }

    private void reset() throws Exception {
        tearDown();
        setUp();
    }

    private String convertToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            System.out.println("Error");
            return "";
        }
    }


    @Test
    public void createBookStatus() throws Exception {
        reset();
        Response want = Response.ok().build();
        Response have = BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        assertEquals(want.getStatus(),have.getStatus());
    }

    @Test
    public void getBookCompareStatus() throws Exception {
        reset();
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        Response want = Response.ok().entity(BOOK).build();
        Response have = BOOK_TARGET.path("978-3551551672").request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(want.getStatus(),have.getStatus());
    }

    @Test
    public void getBookCompareJson() throws Exception {
        reset();
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        String want = convertToJson(BOOK);
        Response response = BOOK_TARGET.path(BOOK.getIsbn()).request(MediaType.APPLICATION_JSON_TYPE).get();
        String have = response.readEntity(String.class);
        assertEquals(want,have);
    }

    @Test
    public void getBooksCompareJson() throws Exception {
        reset();
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(ANOTHER_BOOK,MediaType.APPLICATION_JSON_TYPE));
        String want = convertToJson(new Book[]{ANOTHER_BOOK,BOOK});
        Response response = BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).get();
        String have = response.readEntity(String.class);
        assertEquals(want,have);
    }

    @Test
    public void getBooksCompareStatus() throws Exception {
        reset();
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(ANOTHER_BOOK,MediaType.APPLICATION_JSON_TYPE));
        Response want = Response.ok().entity(BOOK).build();
        Response have = BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(want.getStatus(),have.getStatus());
    }

    @Test
    public void updateBookCompareStatus() throws Exception {
        reset();
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(ANOTHER_BOOK,MediaType.APPLICATION_JSON_TYPE));
        Response want = Response.ok().entity(BOOK).build();
        Response have = BOOK_TARGET.path(BOOK.getIsbn()).request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(new Book("Rick","Roll",BOOK.getIsbn()),MediaType.APPLICATION_JSON_TYPE));
        assertEquals(want.getStatus(),have.getStatus());
    }

    @Test
    public void updateBookCompareJson() throws Exception {
        reset();
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(ANOTHER_BOOK,MediaType.APPLICATION_JSON_TYPE));
        Book toBeUpdated = new Book("Rick","Roll",BOOK.getIsbn());
        String want = convertToJson(new Book[]{toBeUpdated,ANOTHER_BOOK});
        BOOK_TARGET.path(BOOK.getIsbn()).request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(toBeUpdated,MediaType.APPLICATION_JSON_TYPE));
        Response response = BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).get();
        String have = response.readEntity(String.class);
        assertEquals(want,have);
    }

    /*
    //@Test
    public void createDisc() throws Exception {
        MediaResource mediaResource = new MediaResource();
        Response wanted = MediaServiceResult.OK.getResponse();
        Response have = mediaResource.createBook(BOOK);
        assertEquals(wanted,have);
    }

    @Test
    public void getDiscs() throws Exception {

    }

    @Test
    public void getDisc() throws Exception {

    }

    @Test
    public void updateBook() throws Exception {

    }

    @Test
    public void updateDisc() throws Exception {

    }
*/
}