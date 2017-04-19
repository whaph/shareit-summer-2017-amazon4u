package edu.hm.mediaService;

import edu.hm.media.Book;
import edu.hm.media.Disc;
import edu.hm.media.Medium;

import java.util.ArrayList;

/**
 * Created by jupiter on 4/19/17.
 */
public class MediaResource {
    private final MediaService mediaService = new MediaServiceImplementation();


    public Response createMedium(Medium medium){

        MediaServiceResult msr;
        if(medium instanceof Book){
            msr = mediaService.addBook((Book)medium);
        }else if(medium instanceof Disc){
            msr = mediaService.addDisc((Disc)medium);
        }else{
            throw new IllegalArgumentException("Not a medium");
        }
        return new Response(msr);
    }



    private class Response {
        private final MediaServiceResult msr;
        Response(MediaServiceResult msr){
            this.msr = msr;
        }

        public String getStatus() {
            //TODO
            return "To be implemented";
        }
    }

}
