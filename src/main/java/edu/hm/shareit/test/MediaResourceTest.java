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
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by jupiter on 4/28/17.
 */
public class MediaResourceTest {
    static final String APP_URL = "/";
    static final int PORT = 8082;
    static final String WEBAPP_DIR = "./src/main/webapp/";
    static final Server JETTY = new Server(PORT);
    static final JSONObject BOOK_JSON = new JSONObject();
    static final Client CLIENT = ClientBuilder.newClient();
    static final WebTarget BOOK_TARGET = CLIENT.target("http://localhost:8082").path("shareit/media/books");
    static final Stack<Book> BOOK_STACK = new Stack<>();
    static final Stack<Disc> DISC_STACK = new Stack<>();

    @Before
    public void setUp() throws Exception {

        BOOK_STACK.push(new Book("book1","author1","978-3551551672"));
        BOOK_STACK.push(new Book("book2","author2","978-3-89864-765-6"));
        BOOK_STACK.push(new Book("book3","author3","978-3-86894-031-2"));
        BOOK_STACK.push(new Book("book4","author4","978-3-8362-1802-3"));
        BOOK_STACK.push(new Book("book5","author5","978-3-89721-573-3"));
        BOOK_STACK.push(new Book("book6","author6","978-3-645-60151-1"));
        BOOK_STACK.push(new Book("book7","author7","978-3-8272-4714-8"));
        BOOK_STACK.push(new Book("book8","author8","978-3-86899-182-6"));
        BOOK_STACK.push(new Book("book9","author9","978-3-89721-862-8"));
        BOOK_STACK.push(new Book("book10","author10","978-3-8348-0365-8"));
        BOOK_STACK.push(new Book("book11","author11","978-3-86894-007-7"));
        BOOK_STACK.push(new Book("book12","author12","978-3-8274-1706-0"));
        BOOK_STACK.push(new Book("book13","author13","978-3-446-42254-4"));
        BOOK_STACK.push(new Book("book14","author14","978-3-8348-1856-0"));
        BOOK_STACK.push(new Book("book15","author15","978-3-8348-2002-0"));
        BOOK_STACK.push(new Book("book16","author16","978-3-642-29979-7"));
        BOOK_STACK.push(new Book("book17","author17","978-3-642-29980-3"));
        BOOK_STACK.push(new Book("book18","author18","978-3-8274-2504-1"));
        BOOK_STACK.push(new Book("book19","author19","978-3-642-37365-7"));
        BOOK_STACK.push(new Book("book20","author20","978-3-642-37366-4"));
        BOOK_STACK.push(new Book("book21","author21","978-3-86894-170-8"));

        DISC_STACK.push(new Disc("disc1","director1","9783551551672",0));
        DISC_STACK.push(new Disc("disc2","director2","9783898647650",0));
        DISC_STACK.push(new Disc("disc3","director3","9783898647651",0));
        DISC_STACK.push(new Disc("disc4","director4","9783898647652",0));
        DISC_STACK.push(new Disc("disc5","director5","9783898647653",0));
        DISC_STACK.push(new Disc("disc6","director6","9783898647654",0));
        DISC_STACK.push(new Disc("disc7","director7","9783898647655",0));
        DISC_STACK.push(new Disc("disc8","director8","9783898647656",0));
        DISC_STACK.push(new Disc("disc9","director9","9783898647657",0));
        DISC_STACK.push(new Disc("disc10","director10","9783898647658",0));
        DISC_STACK.push(new Disc("disc11","director11","9783898647659",0));
        DISC_STACK.push(new Disc("disc12","director12","9783898647660",0));
        DISC_STACK.push(new Disc("disc13","director13","9783898647661",0));
        DISC_STACK.push(new Disc("disc14","director14","9783898647662",0));
        DISC_STACK.push(new Disc("disc15","director15","9783898647663",0));
        DISC_STACK.push(new Disc("disc16","director16","9783898647664",0));
        DISC_STACK.push(new Disc("disc17","director17","9783898647665",0));
        DISC_STACK.push(new Disc("disc18","director18","9783898647666",0));
        DISC_STACK.push(new Disc("disc19","director19","9783898647667",0));
        DISC_STACK.push(new Disc("disc20","director20","9783898647668",0));
        DISC_STACK.push(new Disc("disc21","director21","9783898647669",0));

        JETTY.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        JETTY.start();
        System.out.println("Jetty listening on port " + PORT);
    }

    @After
    public void tearDown() throws Exception {
        JETTY.stop();
    }


    @Test
    public void createBookStatus() throws Exception {
        System.out.println("Before");
        System.out.println(BOOK_STACK);
        Book book = BOOK_STACK.pop();
        System.out.println("After");
        System.out.println(BOOK_STACK);
        System.out.println(book.getIsbn());
        Response want = Response.ok().build();
        Response have = BOOK_TARGET
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(want.getStatus(), have.getStatus());
    }

    @Test
    public void getBooks() throws Exception {
        System.out.println("Before");
        System.out.println(BOOK_STACK);
        Book book = BOOK_STACK.pop();
        System.out.println("After");
        System.out.println(BOOK_STACK);
        Response want = Response.ok().entity(book).build();
        Response have = BOOK_TARGET.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(book, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(want.getStatus(), have.getStatus());
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