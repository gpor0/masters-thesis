package com.github.gpor89.masters.data.model;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class EpgData {

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
    private GenreData genre;
    private String ageRestriction;
    private LocalDateTime playingStart;
    private LocalDateTime playingEnd;

    public EpgData() {

        this.idEpg = genString(10);
        this.epgTitle = genString(25);
        this.originalTitle = genString(25);
        this.shortDescription = genString(200);
        this.longDescription = genString(1200);
        this.poster = genString(32);
        this.imdbId = genString(8);
        this.imdbRating = new SecureRandom().nextDouble();
        this.director = null;
        this.writers = new LinkedList<>();
        this.stars = new LinkedList<>();

        for (int i = new SecureRandom().nextInt(10); i > 0; i--) {
            this.writers.add(genString(16));
        }

        this.year = 1970 + new SecureRandom().nextInt(58);
        this.genre = GenreData.values()[new SecureRandom().nextInt(GenreData.values().length)];
        this.ageRestriction = genString(5);
        this.playingStart = LocalDateTime.now().plusSeconds(new SecureRandom().nextInt(120));
        this.playingEnd = LocalDateTime.now().plusMinutes(30).minusSeconds(new SecureRandom().nextInt(120));

    }

    private String genString(int size) {
        char[] chars = " abcdeeefghijklmnopqrstuvwxyz ".toCharArray();
        StringBuilder sb = new StringBuilder(size);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < size; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
    }

    public String getIdEpg() {
        return idEpg;
    }

    public String getEpgTitle() {
        return epgTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getPoster() {
        return poster;
    }

    public String getImdbId() {
        return imdbId;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getStars() {
        return stars;
    }

    public Integer getYear() {
        return year;
    }

    public GenreData getGenre() {
        return genre;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public LocalDateTime getPlayingStart() {
        return playingStart;
    }

    public LocalDateTime getPlayingEnd() {
        return playingEnd;
    }

}
