package com.github.gpor89.masters.client.util;

import com.github.gpor89.masters.rest.model.EpgItem;
import grpc.EpgOuterClass;
import https.github_com.gpor89.jax_ws.cxf.epg._1.Epg;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import static com.github.gpor89.masters.data.MemCache.*;

public class ObjectUtils {

    public static EpgOuterClass.EpgResponse getGrpcEpgResponse(int size) {
        EpgOuterClass.EpgResponse.Builder request = EpgOuterClass.EpgResponse.newBuilder();
        for (int j = 0; j < size; j++) {
            request.addItems(getGrpcEpgItem());
        }

        return request.build();
    }

    public static EpgOuterClass.EpgItem getGrpcEpgItem() {
        EpgOuterClass.EpgItem item = EpgOuterClass.EpgItem.newBuilder().setIdEpg(EPG_ID)
                .setAgeRestriction(AGE)
                .setEpgTitle(TITLE)
                .setGenre(GENRE)
                .setImdbId(IMDB_ID)
                .setImdbRating(IMDB_RATING)
                .setLongDescription(LONG_DESCRIPTION)
                .setShortDescription(SHORT_DESCRIPTION)
                .setOriginalTitle(ORIGINAL_TITLE)
                .setPlayingStart(PLAYING_START)
                .setPlayingEnd(PLAYING_END)
                .setPoster(POSTER)
                .setYear(YEAR)
                .addAllStars(STARS)
                .addAllWriters(WRITERS).setDirector(DIRECTOR).build();

        return item;
    }

    public static List<EpgItem> getRestEpgResponse(int size) {
        List<EpgItem> result = new LinkedList<>();
        for (int j = 0; j < size; j++) {
            result.add(getRestEpgItem());
        }

        return result;
    }

    public static EpgItem getRestEpgItem() {
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

        return item;
    }

    public static List<Epg> getSoapEpgResponse(int size) {
        List<Epg> result = new LinkedList<>();
        for (int j = 0; j < size; j++) {
            result.add(getSoapEpgItem());
        }

        return result;
    }

    public static Epg getSoapEpgItem() {
        Epg epg = new Epg();

        epg.setIdEpg(EPG_ID);
        epg.setEpgTitle(TITLE);
        epg.setDirector(DIRECTOR);
        epg.setGenre(GENRE);
        epg.setImdbId(IMDB_ID);
        epg.setImdbRating(BigDecimal.valueOf(IMDB_RATING));
        epg.setLongDescription(LONG_DESCRIPTION);
        epg.setOriginalTitle(ORIGINAL_TITLE);
        epg.setPlayingStart(PLAYING_START);
        epg.setPlayingEnd(PLAYING_END);
        epg.setPoster(POSTER);
        epg.setShortDescription(SHORT_DESCRIPTION);
        epg.setYear(BigInteger.valueOf(YEAR));
        epg.setAgeRestriction(AGE);
        epg.getWriters().addAll(WRITERS);
        epg.getStars().addAll(STARS);

        return epg;
    }

}
