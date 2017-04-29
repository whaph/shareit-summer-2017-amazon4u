package edu.hm.shareit.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.shareit.media.Book;
import edu.hm.shareit.media.Disc;
import edu.hm.shareit.media.Medium;
import edu.hm.shareit.mediaService.MediaService;
import edu.hm.shareit.mediaService.MediaServiceImplementation;
import edu.hm.shareit.mediaService.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

/**
 * Created by jupiter on 4/19/17.
 */
@Path("media")
public class MediaResource {
    private static MediaService MEDIA_SERVICE = new MediaServiceImplementation();

    /**
     * Creates a book (not an exemplar).
     * REST interface: POST /media/books
     * @param book The book
     * @return Response success, failure, ...
     */
    @POST
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {
        final MediaServiceResult msr = getMediaService().addBook(book);
        return msr.getResponse();
    }

    /**
     * Creates a disc (not an exemplar).
     * REST interface: POST /media/discs
     * @param disc The disc
     * @return Response success, failure, ...
     */
    @POST
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc) {
        final MediaServiceResult msr = getMediaService().addDisc(disc);
        return msr.getResponse();
    }

    /**
     * Show all books.
     * REST interface: GET /media/books
     * @return The books
     */
    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        final Medium[] books = getMediaService().getBooks();
        final Object[] sortedBooks = Arrays.stream(books)
                .sorted((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()))
                .toArray();
        return Response.ok().entity(convertToJson(sortedBooks)).build();
    }

    /**
     * Show the book having the isbn.
     * REST interface: GET /media/books/{isbn}
     * @param isbn Used to identify the book
     * @return The book
     */
    @GET
    @Path("books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
        final Medium book = getMediaService().getBook(isbn);
        System.out.println(book);
        return book != null
                ? Response.ok().entity(book).build()
                : MediaServiceResult.NOT_FOUND.getResponse();
    }

    /**
     * Show all discs.
     * REST interface: GET /media/discs/
     * @return The discs
     */
    @GET
    @Path("discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        final Medium[] discs = getMediaService().getDiscs();
        final Object[] sortedDiscs = Arrays.stream(discs)
                .sorted((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()))
                .toArray();
        return Response.ok().entity(sortedDiscs).build();
    }

    /**
     * Show the disc having the barcode.
     * REST interface: GET /media/discs/{barcode}
     * @param barcode Used to identify the disc
     * @return The disc
     */
    @GET
    @Path("discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode") String barcode) {
        final Medium disc = getMediaService().getBook(barcode);
        return disc != null
                ? Response.ok().entity(disc).build()
                : MediaServiceResult.NOT_FOUND.getResponse();
    }

    /**
     * Update a the book having the isbn.
     * REST interface: PUT /media/books/{isbn}
     * @param book The book-update
     * @param isbn Used to identify the book
     * @return Response success, failure, ...
     */
    @PUT
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn") String isbn) {
        System.out.println("MediaResource >>> updateBook >> ISBN: " + isbn);
        System.out.println("MediaResource >>> new Author and title: " + book.getAuthor() + " " + book.getTitle());
        MediaServiceResult msr = getMediaService()
                .updateBook(new Book(book.getTitle(), book.getAuthor(), isbn));
        return msr.getResponse();
    }

    /**
     * Update the disc having the barcode.
     * REST interface: PUT /media/discs/{barcode}
     * @param disc    The disc-update
     * @param barcode Used to identify the disc
     * @return Response success, failure, ...
     */
    @PUT
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode") String barcode) {
        System.out.println("MediaResource >>> updateDisc >> Barcode: " + barcode);
        System.out.println("MediaResource >>> new Director, Fsk and title: " + disc.getDirector() + disc.getFsk() + disc.getTitle());
        MediaServiceResult msr = getMediaService()
                .updateDisc(new Disc(disc.getTitle(), barcode, disc.getDirector(), disc.getFsk()));
        return msr.getResponse();
    }

    @DELETE
    public void purge() {
       this.MEDIA_SERVICE = new MediaServiceImplementation();
        System.out.println("PURGE ALL");
    }

    /**
     * Converts an object into JSON-format.
     *
     * @param object object that shall be converted
     * @return JSON representation of the object
     */
    private String convertToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            System.out.println("Error");
            return "";
        }
    }

    /**
     * Getter for the mediaService.
     *
     * @return mediaSerive
     */
    private MediaService getMediaService() {
        return MEDIA_SERVICE;
    }
}
