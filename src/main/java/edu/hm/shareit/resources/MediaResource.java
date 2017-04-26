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

    /**
     *
     * @param book
     * @return
     */
    @POST
    @Path("books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book){
        System.out.println(book.getAuthor());
        MediaServiceResult msr = getMediaService().addBook(book);
        return msr.getResponse();
    }

    @POST
    @Path("discs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDisc(Disc disc){
        MediaServiceResult msr = getMediaService().addDisc(disc);
        return msr.getResponse();
    }

    private MediaService getMediaService(){
        return this.MEDIA_SERVICE;
    }

    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){
        Medium[] books = getMediaService().getBooks();

        return Response.ok().entity(convertToJson(books)).build();
    }

    @GET
    @Path("discs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscs(){
        Medium[] books = getMediaService().getDiscs();
        //TODO
        return Response.ok().build();
    }


    @PUT
    @Path("books/{mediaId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book){
        MediaServiceResult msr = getMediaService().updateBook(book);
        return Response.ok().build();
    }

    public Response updateDisc(Disc disc){
        MediaServiceResult msr = getMediaService().updateDisc(disc);
        return Response.ok().build();
    }

    public String convertToJson(Object media){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(media);
        } catch (Exception e) {
            System.out.println("Error");
            return "";
        }
    }

}
