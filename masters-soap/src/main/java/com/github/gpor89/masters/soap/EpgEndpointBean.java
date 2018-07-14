package com.github.gpor89.masters.soap;

import com.github.gpor89.masters.data.MemCache;
import com.github.gpor89.masters.data.model.EpgData;
import com.kumuluz.ee.jaxws.cxf.annotations.WsContext;
import https.github_com.gpor89.jax_ws.cxf.epg._1.Epg;
import https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint;
import https.github_com.gpor89.jax_ws.cxf.epg._1.GetEpg;
import https.github_com.gpor89.jax_ws.cxf.epg._1.GetEpgResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@WsContext(contextRoot = "/soap", urlPattern = "/epg/1.0")
@ApplicationScoped
@WebService(serviceName = "EpgEndpoint", portName = "EpgEndpointSOAP", targetNamespace = "https://github.com/gpor89/jax-ws/cxf/epg/1.0",
        endpointInterface = "https.github_com.gpor89.jax_ws.cxf.epg._1.EpgEndpoint", wsdlLocation = "/wsdls/epg.wsdl")
public class EpgEndpointBean implements EpgEndpoint {

    @Override
    public GetEpgResponse getEpg(GetEpg parameters) {

        GetEpgResponse response = new GetEpgResponse();

        List<EpgData> epgDataList = MemCache.getEpgDataList();

        response.getEpg().addAll(
                epgDataList.stream().limit(parameters.getSize().longValue()).map(e -> {
                    Epg epg = new Epg();

                    epg.setIdEpg(e.getIdEpg());
                    epg.setEpgTitle(e.getEpgTitle());
                    epg.setDirector(e.getDirector());
                    epg.setGenre(e.getGenre().toString());
                    epg.setImdbId(e.getImdbId());
                    epg.setImdbRating(BigDecimal.valueOf(e.getImdbRating()));
                    epg.setLongDescription(e.getLongDescription());
                    epg.setOriginalTitle(e.getOriginalTitle());
                    epg.setPlayingStart(e.getPlayingStart().toString());
                    epg.setPlayingEnd(e.getPlayingEnd().toString());
                    epg.setPoster(e.getPoster());
                    epg.setShortDescription(e.getShortDescription());
                    epg.setYear(BigInteger.valueOf(e.getYear()));
                    epg.setAgeRestriction(e.getAgeRestriction());
                    epg.getWriters().addAll(e.getWriters());
                    epg.getStars().addAll(e.getStars());

                    return epg;
                }).collect(Collectors.toList()));

        return response;
    }
}
