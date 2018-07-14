package com.github.gpor89.masters.rest;

import com.github.gpor89.masters.data.MemCache;
import com.github.gpor89.masters.rest.model.EpgItem;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Path("/rest")
public class EpgResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/epg")
    public Response getEpg(@QueryParam("size") Long size) {
        long s = new Date().getTime();

        if (size == null) {
            size = Long.MAX_VALUE;
        }

        List<EpgItem> result = MemCache.getEpgDataList().stream().limit(size).map(e -> {
            EpgItem item = new EpgItem();

            item.setIdEpg(e.getIdEpg());
            item.setAgeRestriction(e.getAgeRestriction());
            item.setDirector(e.getDirector());
            item.setEpgTitle(e.getEpgTitle());
            item.setGenre(e.getGenre().toString());
            item.setImdbId(e.getImdbId());
            item.setImdbRating(e.getImdbRating());
            item.setLongDescription(e.getLongDescription());
            item.setOriginalTitle(e.getOriginalTitle());
            item.setPlayingStart(e.getPlayingStart().toString());
            item.setPlayingEnd(e.getPlayingEnd().toString());
            item.setPoster(e.getPoster());
            item.setShortDescription(e.getShortDescription());
            item.setStars(e.getStars());
            item.setWriters(e.getWriters());
            item.setYear(e.getYear());

            return item;
        }).collect(Collectors.toList());
        System.out.print("prepared in " + (new Date().getTime() - s) + " ms");
        return Response.ok(result).build();
    }

}
