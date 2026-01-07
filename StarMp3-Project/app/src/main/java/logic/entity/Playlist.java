package logic.entity;

import logic.exception.SongNotExistsException;

import java.util.ArrayList;

/**
 * Playlist verwaltet eine ArrayList<Song> von Songs und den Namen der Playlist.
 */
public class Playlist {
    private String name;
    private ArrayList<Song> songs = new ArrayList<>();

    public Playlist(){
        this.name = "Playlist";
    }

    public Playlist(String name, ArrayList<Song> stringSongArrayList) {
        this.name = name;
        this.songs = stringSongArrayList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public Song searchFor(String songname){
        for(int i = 0; i < songs.size(); i++){
            Song song = songs.get(i);
            if(song.getTITLE().contains(songname)){
                return song;
            }
        }
        throw new SongNotExistsException("Song " +songname + " existiert nicht");
    }

    /**
     * @return String-Version der Playlist
     */
    @Override
    public String toString(){
        String output = "\"" + this.name + "\" [Playlist]\n";
        for(Song song : songs){
            output += "\t" +song.getTITLE() + "\n";
        }
        output += "\n";
        return output;
    }

}