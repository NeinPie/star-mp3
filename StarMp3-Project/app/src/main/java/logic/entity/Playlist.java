package logic.entity;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist() {
        this.name = "Playlist ";
    }

    public Playlist(String name, ArrayList<Song> stringSongArrayList) {
        this.name = name;
        this.songs = stringSongArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSong(String songname) {
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void addSongs(ArrayList<Song> songs) {
        this.songs.addAll(songs);
    }

    public Song searchFor(String songname) {
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getTITLE().contains(songname)) {
                return song;
            }
        }
        //TODO: throw new SongNotExistsException("Song " +songname + " existiert nicht");
        return null; //delete
    }

    public String toString() {
        String output = "\"" + this.name + "\" [Playlist]\n";
        for (Song song : songs) {
            output += "\t" + song.getTITLE() + "\n";
        }
        output += "\n";
        return output;
    }
}