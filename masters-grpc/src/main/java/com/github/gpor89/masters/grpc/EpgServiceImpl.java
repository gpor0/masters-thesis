package com.github.gpor89.masters.grpc;

import grpc.EpgGrpc;
import grpc.EpgOuterClass;
import io.grpc.stub.StreamObserver;

import java.util.Date;
import java.util.logging.Logger;

import static com.github.gpor89.masters.data.MemCache.*;

public class EpgServiceImpl extends EpgGrpc.EpgImplBase {

    private Logger LOG = Logger.getLogger(EpgServiceImpl.class.getName());

    @Override
    public void getEpgList(EpgOuterClass.EpgRequest request, StreamObserver<EpgOuterClass.EpgResponse> responseObserver) {
        long s = new Date().getTime();

        int size = request.getSize();

        EpgOuterClass.EpgResponse.Builder builder = EpgOuterClass.EpgResponse.newBuilder();

        for (int i = 0; i < size; i++) {
            EpgOuterClass.EpgItem.Builder b = EpgOuterClass.EpgItem.newBuilder()
                    .setIdEpg(EPG_ID)
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
                    .addAllWriters(WRITERS).setDirector(DIRECTOR);

            builder.addItems(b.build());
        }

        EpgOuterClass.EpgResponse response = builder.build();

        responseObserver.onNext(response);
        LOG.info("prepared in " + (new Date().getTime() - s) + " ms");
        responseObserver.onCompleted();
    }

    @Override
    public void ping(EpgOuterClass.X request, StreamObserver<EpgOuterClass.X> responseObserver) {

        EpgOuterClass.X build = EpgOuterClass.X.newBuilder().setN(1).build();

        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    @Override
    public void getEpgItem(EpgOuterClass.EpgRequest request, StreamObserver<EpgOuterClass.EpgItem> responseObserver) {

        EpgOuterClass.EpgItem.Builder b = EpgOuterClass.EpgItem.newBuilder()
                .setIdEpg(EPG_ID)
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
                .addAllWriters(WRITERS).setDirector(DIRECTOR);

        responseObserver.onNext(b.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getEpgStream(EpgOuterClass.EpgRequest request, StreamObserver<EpgOuterClass.EpgItem> responseObserver) {
        long s = new Date().getTime();

        int size = request.getSize();

        for (int i = 0; i < size; i++) {
            EpgOuterClass.EpgItem.Builder b = EpgOuterClass.EpgItem.newBuilder()
                    .setIdEpg(EPG_ID)
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
                    .addAllWriters(WRITERS).setDirector(DIRECTOR);

            responseObserver.onNext(b.build());
        }

        LOG.info("completed in " + (new Date().getTime() - s) + " ms");
        responseObserver.onCompleted();
    }

    @Override
    public void sendEpgItem(EpgOuterClass.EpgItem request, StreamObserver<EpgOuterClass.X> responseObserver) {
        request.getIdEpg();

        responseObserver.onNext(EpgOuterClass.X.newBuilder().setN(1).build());
        responseObserver.onCompleted();
    }

    @Override
    public void sendEpgList(EpgOuterClass.EpgResponse request, StreamObserver<EpgOuterClass.X> responseObserver) {
        request.getItems(0).getIdEpg();

        responseObserver.onNext(EpgOuterClass.X.newBuilder().setN(request.getItemsCount()).build());
        responseObserver.onCompleted();
    }
}
