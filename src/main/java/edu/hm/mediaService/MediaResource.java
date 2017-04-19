package edu.hm.mediaService;

import edu.hm.media.Book;
import edu.hm.media.Disc;
import edu.hm.media.Medium;
import edu.hm.REST.response.Response;

/**
 * Created by jupiter on 4/19/17.
 */
public class MediaResource {
    private final MediaService mediaService = new MediaServiceImplementation();

    public Response createBook(Book book){
        MediaServiceResult msr = getMediaService().addBook(book);
        return new Response(msr);
    }

    public Response createDisc(Disc disc){
        MediaServiceResult msr = getMediaService().addDisc(disc);
        return new Response(msr);
    }

    private MediaService getMediaService(){
        return this.mediaService;
    }

    public Response getBooks(){
        Medium[] books = getMediaService().getBooks();
        //TODO
        return new Response(MediaServiceResult.valueOf(""));
    }


    public Response getDiscs(){
        Medium[] books = getMediaService().getDiscs();
        //TODO
        return new Response(MediaServiceResult.valueOf(""));
    }

    public Response updateBook(Book book){
        MediaServiceResult msr = getMediaService().updateBook(book);
        return new Response(msr);

    }

    public Response updateDisc(Disc disc){
        MediaServiceResult msr = getMediaService().updateDisc(disc);
        return new Response(msr);

    }
}
