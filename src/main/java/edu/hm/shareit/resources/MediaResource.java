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

/**
 * Created by jupiter on 4/19/17.
 */
@Path("media")
public class MediaResource {
    private static final MediaService MEDIA_SERVICE = new MediaServiceImplementation();

    /**
     * Creates a book (not an exemplar).
     *
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
     *
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
     * Getter for all books.
     *
     * @return The books
     */
    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        final Medium[] books = getMediaService().getBooks();
        return Response.ok().entity(books).build();
    }

    /**
     * Getter for one book.
     *
     * @param isbn Used to identify the book
     * @return The book
     */
    @GET
    @Path("books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
        final Medium book = getMediaService().getBook(isbn);
        return book != null
                ? Response.ok().entity(book).build()
                : MediaServiceResult.NOT_FOUND.getResponse();
    }

    /**
     * Getter for all discs.
     *
     * @return The discs
     */
    @GET
    @Path("discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs() {
        final Medium[] discs = getMediaService().getDiscs();
        return Response.ok().entity(discs).build();
    }

    /**
     * Getter for one disc.
     *
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
     * Update a book.
     *
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
     * Update a disc.
     *
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
