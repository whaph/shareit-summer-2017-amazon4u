package edu.hm.shareit.test;


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
    static final Book ANOTHER_BOOK = new Book("title","author","978-3551551689");
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


    @Test
    public void createBookStatus() throws Exception {
        Response want = Response.ok().build();
        Response have = BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        assertEquals(want.getStatus(),have.getStatus());
    }

    @Test
    public void getBooks() throws Exception {
        Response want = Response.ok().entity(BOOK).build();
        Response have = BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(BOOK,MediaType.APPLICATION_JSON_TYPE));
        assertEquals(want.getStatus(),have.getStatus());
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
    public void getBooks() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.createBook(BOOK);

        //mediaResource.createBook(ANOTHER_BOOK);
        Object obj = mediaResource.getBooks().getEntity();
        ObjectMapper om = new ObjectMapper();



        //assertEquals(wanted,have);
    }

    @Test
    public void getBook() throws Exception {
        MediaResource mediaResource = new MediaResource();
        mediaResource.createBook(BOOK);
        Response wanted = Response.ok().entity(BOOK).build();
        Response have = mediaResource.getBook(BOOK.getIsbn());
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