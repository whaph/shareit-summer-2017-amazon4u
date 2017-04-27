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
    private static final MediaService MEDIA_SERVICE = new MediaServiceImplementation();

    @POST
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book){
        final MediaServiceResult msr = getMediaService().addBook(book);
        return msr.getResponse();
    }

    @POST
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc){
        final MediaServiceResult msr = getMediaService().addDisc(disc);
        return msr.getResponse();
    }

    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){
        final Medium[] books = getMediaService().getBooks();
        return Response.ok().entity(convertToJson(books)).build();
    }

    @GET
    @Path("books/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn")String isbn){
        final Medium book = getMediaService().getBook(isbn);
        return book != null
                ? Response.ok().entity(convertToJson(book)).build()
                : MediaServiceResult.NOT_FOUND.getResponse();
    }

    @GET
    @Path("discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(){
        final Medium[] discs = getMediaService().getDiscs();
        return Response.ok().entity(convertToJson(discs)).build();
    }


    @GET
    @Path("discs/{barcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisc(@PathParam("barcode")String barcode){
        final Medium disc = getMediaService().getBook(barcode);
        return disc != null
                ? Response.ok().entity(convertToJson(disc)).build()
                : MediaServiceResult.NOT_FOUND.getResponse();
    }


    @PUT
    @Path("books/{isbn}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book, @PathParam("isbn")String isbn){
        // TODO - update the book correctly please
        MediaServiceResult msr = getMediaService().updateBook(book);
        return Response.ok().build();
    }

    @PUT
    @Path("discs/{barcode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDisc(Disc disc, @PathParam("barcode")String barcode){
        // TODO - update the disc correctly please
        MediaServiceResult msr = getMediaService().updateDisc(disc);
        return Response.ok().build();
    }

    /** Converts an object into JSON-format
     *
     * @param media object that shall be converted
     * @return JSON representation of the object
     */
    public String convertToJson(Object media){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(media);
        } catch (Exception e) {
            System.out.println("Error");
            return "";
        }
    }

    private MediaService getMediaService(){
        return MEDIA_SERVICE;
    }
}
