package com.github.gpor89.masters.grpc;

import com.github.gpor89.masters.data.MemCache;
import com.github.gpor89.masters.data.model.EpgData;
import grpc.EpgGrpc;
import grpc.EpgOuterClass;
import io.grpc.stub.StreamObserver;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EpgServiceImpl extends EpgGrpc.EpgImplBase {

    @Override
    public void getEpg(EpgOuterClass.EpgRequest request, StreamObserver<EpgOuterClass.EpgResponse> responseObserver) {
        long s = new Date().getTime();
        List<EpgData> epgDataList = MemCache.getEpgDataList();

        int size = request.getSize();

        EpgOuterClass.EpgResponse.Builder builder = EpgOuterClass.EpgResponse.newBuilder().addAllItems(epgDataList.stream().limit(size).map(e -> {

            EpgOuterClass.EpgItem.Builder b = EpgOuterClass.EpgItem.newBuilder()
                    .setIdEpg(e.getIdEpg())
                    .setAgeRestriction(e.getAgeRestriction())
                    .setEpgTitle(e.getEpgTitle())
                    .setGenre(e.getGenre().toString())
                    .setImdbId(e.getImdbId())
                    .setImdbRating(e.getImdbRating())
                    .setLongDescription(e.getLongDescription())
                    .setShortDescription(e.getShortDescription())
                    .setOriginalTitle(e.getOriginalTitle())
                    .setPlayingStart(e.getPlayingStart().toString())
                    .setPlayingEnd(e.getPlayingEnd().toString())
                    .setPoster(e.getPoster())
                    .setYear(e.getYear())
                    .addAllStars(e.getStars())
                    .addAllWriters(e.getWriters());

            if(e.getDirector() != null) {
                b.setDirector(e.getDirector());
            }

            return b.build();}).collect(Collectors.toList()));

        System.out.print("prepared in " + (new Date().getTime() - s) + " ms");

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
