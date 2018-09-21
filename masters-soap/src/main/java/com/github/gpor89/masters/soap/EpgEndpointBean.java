package com.github.gpor89.masters.soap;

import com.kumuluz.ee.jaxws.cxf.annotations.WsContext;
import https.github_com.gpor89.jax_ws.cxf.epg._1.*;

import javax.enterprise.context.ApplicationScoped;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import static com.github.gpor89.masters.data.MemCache.*;

@WsContext(contextRoot = "/soap", urlPattern = "/epg/1.0")
@ApplicationScoped
@WebService(serviceName = "EpgEndpoint", portName = "EpgEndpointSOAP", targetNamespace = "https://github.com/gpor89/jax-ws/cxf/epg/1.0",
        endpointInterface = "https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint", wsdlLocation = "/wsdls/epg.wsdl")
public class EpgEndpointBean implements EpgEndpoint {

    @Override
    public GetEpgListResponse getEpgList(GetEpgList parameters) {
        List<Epg> list = new LinkedList<>();
        for (int i = 0; i < parameters.getSize().longValue(); i++) {
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

            list.add(epg);
        }

        GetEpgListResponse response = new GetEpgListResponse();

        response.getEpg().addAll(list);

        return response;
    }

    @Override
    public Pong ping(Empty parameters) {
        Pong pong = new Pong();
        pong.setX(BigInteger.ONE);
        return pong;
    }

    @Override
    public Epg getEpg(Empty parameters) {

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

    @Override
    public Empty sendEpg(Epg parameters) {
        parameters.getStars();
        return new Empty();
    }

    @Override
    public Empty sendEpgList(GetEpgListResponse parameters) {
        parameters.getEpg();
        return new Empty();
    }

}
