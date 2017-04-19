package edu.hm.mediaService;

import edu.hm.media.Book;
import edu.hm.media.Disc;
import edu.hm.media.Medium;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jupiter on 4/19/17.
 */
public class MediaResource {
    private final MediaService mediaService = new MediaServiceImplementation();

    @POST
    @Path("/books")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(Book book){
        System.out.println("hier");
        MediaServiceResult msr = getMediaService().addBook(book);
        return Response.ok().build();
    }

    public Response createDisc(Disc disc){
        MediaServiceResult msr = getMediaService().addDisc(disc);
        return Response.ok().build();
    }

    private MediaService getMediaService(){
        return this.mediaService;
    }


    @GET
    @Path("books")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(){
        Medium[] books = getMediaService().getBooks();
        System.out.println("in getBooks");
        return Response.ok().build();
    }


    public Response getDiscs(){
        Medium[] books = getMediaService().getDiscs();
        //TODO
        return Response.ok().build();
    }

    public Response updateBook(Book book){
        MediaServiceResult msr = getMediaService().updateBook(book);
        return Response.ok().build();

    }

    public Response updateDisc(Disc disc){
        MediaServiceResult msr = getMediaService().updateDisc(disc);
        return Response.ok().build();

    }
}
