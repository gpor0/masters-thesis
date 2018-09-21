package com.github.gpor89.masters.rest;

import com.github.gpor89.masters.rest.model.EpgItem;
import com.github.gpor89.masters.rest.model.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static com.github.gpor89.masters.data.MemCache.*;

@Path("/rest")
public class EpgResource {

    Logger LOG = Logger.getLogger(EpgResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/epg")
    public Response getEpg(@QueryParam("size") Long size) {
        long s = new Date().getTime();

        if (size == null) {
            size = Long.MAX_VALUE;
        }

        List<EpgItem> result = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            EpgItem item = new EpgItem();

            item.setIdEpg(EPG_ID);
            item.setAgeRestriction(AGE);
            item.setDirector(DIRECTOR);
            item.setEpgTitle(TITLE);
            item.setGenre(GENRE);
            item.setImdbId(IMDB_ID);
            item.setImdbRating(IMDB_RATING);
            item.setLongDescription(LONG_DESCRIPTION);
            item.setOriginalTitle(ORIGINAL_TITLE);
            item.setPlayingStart(PLAYING_START);
            item.setPlayingEnd(PLAYING_END);
            item.setPoster(POSTER);
            item.setShortDescription(SHORT_DESCRIPTION);
            item.setStars(STARS);
            item.setWriters(WRITERS);
            item.setYear(YEAR);

            result.add(item);
        }
        LOG.info("prepared in " + (new Date().getTime() - s) + " ms");
        return Response.ok(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/epg/single")
    public Response getEpgItem() {
        long s = new Date().getTime();

        EpgItem item = new EpgItem();

        item.setIdEpg(EPG_ID);
        item.setAgeRestriction(AGE);
        item.setDirector(DIRECTOR);
        item.setEpgTitle(TITLE);
        item.setGenre(GENRE);
        item.setImdbId(IMDB_ID);
        item.setImdbRating(IMDB_RATING);
        item.setLongDescription(LONG_DESCRIPTION);
        item.setOriginalTitle(ORIGINAL_TITLE);
        item.setPlayingStart(PLAYING_START);
        item.setPlayingEnd(PLAYING_END);
        item.setPoster(POSTER);
        item.setShortDescription(SHORT_DESCRIPTION);
        item.setStars(STARS);
        item.setWriters(WRITERS);
        item.setYear(YEAR);


        LOG.info("prepared in " + (new Date().getTime() - s) + " ms");
        return Response.ok(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/epg")
    public Response sendEpgList(List<EpgItem> params) {
        params.size();
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/epg/single")
    public Response sendEpg(EpgItem i) {
        i.getIdEpg();
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ping")
    public Response ping() {

        Status s = new Status();
        s.setX(1);

        return Response.ok(s).build();
    }

}
