package logic.entity;

import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Song {
    private final String TITLE;
    private String filename;
    private String artist;
    private String album;
    private String genre;
    private int fileLengthMs;
    private String date;
    private String lyrics;
    private String comment;
    private String copyright;
    private String publisher;
    private byte[] coverImage;


    public Song(String title){
        this.TITLE = title;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setFilename(String savingTitle) {
        this.filename = savingTitle;
    }
    public String getFilename() {
        return filename;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getArtist() {
        return artist;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getAlbum() {
        return album;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }
    public void setFileLengthMs(int fileLengthMs) {
        this.fileLengthMs = fileLengthMs;
    }
    public int getFileLengthMs() {
        return fileLengthMs;
    }
    public String getFileLength(){
        int sec = ((fileLengthMs/1000)%60);
        int min = (fileLengthMs- sec*1000)/1000/60;
        return min+ "min und "+ sec+ "s";
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
    public String getLyrics() {
        return lyrics;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getComment() {
        return comment;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    public String getCopyright() {
        return copyright;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getPublisher() {
        return publisher;
    }

    public void setCoverImage(byte[] img) {
        this.coverImage = img;
    }
    public byte[] getCoverImageByteArray() {
        return coverImage;
    }

    public String toString(){
        String allData = "";
        allData +=
                TITLE + " [Song]\n"+
                        "\tArtist: " + artist + "\n"+
                        "\tAlbum: " + album+ "\n"+
                        "\tLänge: " + getFileLength()+ "\n"+
                        "\tGenre: " + genre+ "\n"+
                        "\tVeröffentlichung: " + date+ "\n"+
                        "\tText: " + lyrics+ "\n"+
                        "\tKommentar: " + comment+ "\n"+
                        "\tPublisher: " + publisher+ "\n"+
                        "\tCopyright: " + copyright+ "\n\n"
        ;

        return allData;
    }

    public static Image byteArrayToImage(byte[] array) throws IOException {
        ByteArrayInputStream inStreambj = new ByteArrayInputStream(array);

        Image sceneImage = new Image(inStreambj);

        return sceneImage;
    }

    public int getFileLengthS() {
        return getFileLengthMs()/1000;
    }
}