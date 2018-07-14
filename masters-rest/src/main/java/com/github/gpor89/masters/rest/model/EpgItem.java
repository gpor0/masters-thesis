package com.github.gpor89.masters.rest.model;

import java.util.List;

public class EpgItem {

    private String idEpg;
    private String epgTitle;
    private String originalTitle;
    private String shortDescription;
    private String longDescription;
    private String poster;
    private String imdbId;
    private Double imdbRating;
    private String director;
    private List<String> writers;
    private List<String> stars;
    private Integer year;
    private String genre;
    private String ageRestriction;
    private String playingStart;
    private String playingEnd;

    public String getIdEpg() {
        return idEpg;
    }

    public void setIdEpg(String idEpg) {
        this.idEpg = idEpg;
    }

    public String getEpgTitle() {
        return epgTitle;
    }

    public void setEpgTitle(String epgTitle) {
        this.epgTitle = epgTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getStars() {
        return stars;
    }

    public void setStars(List<String> stars) {
        this.stars = stars;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getPlayingStart() {
        return playingStart;
    }

    public void setPlayingStart(String playingStart) {
        this.playingStart = playingStart;
    }

    public String getPlayingEnd() {
        return playingEnd;
    }

    public void setPlayingEnd(String playingEnd) {
        this.playingEnd = playingEnd;
    }

}
