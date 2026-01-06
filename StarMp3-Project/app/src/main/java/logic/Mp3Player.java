package logic;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioMetaData;
import ddf.minim.AudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import logic.entity.Playlist;
import logic.entity.Song;
import logic.handler.PlaylistHandler;

import java.io.File;
import java.io.IOException;

import static logic.handler.PlaylistHandler.getFullMusicPath;

public class Mp3Player {
    private final SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private float currentVolume;
    private Song currentSong;

    public Mp3Player(){
        minim = new SimpleMinim();
        PlaylistHandler.loadAllSongs();
    }

    public void play() {
        audioPlayer.setGain(currentVolume);
        audioPlayer.play();
    }

    public void play(String name){/*name von Playlist oder File*/
        boolean nameIsMusic = new File(getFullMusicPath(name)).exists();

        if(nameIsMusic){
            name = getFullMusicPath(name);
            playSong(name);
            System.out.println("Playing "+name+" [Song]");
        } else {
            try {
                PlaylistHandler.loadPlaylist(name);
                if (PlaylistHandler.getPlaylists().containsKey(name)) {
                    Song song = PlaylistHandler.getRandomSong(name);
                    playSong(getFullMusicPath(song.getTITLE()));
                    System.out.println("Spielt " + song.getTITLE() + " von Playlist " + name);
                }
            } catch (Exception e) {
                //TODO: throw new PlaylistNotExistsException("Playlist "+name + " existiert nicht.");
            }
        }
    }

    private void playSong(String songname){
        try{
            audioPlayer = minim.loadMP3File(songname);
            loadSongMetaData(PlaylistHandler.removePathFromSongName(songname));
            audioPlayer.play();

            Playlist allSongs = PlaylistHandler.getAllSongs();
            Song song = allSongs.searchFor(PlaylistHandler.removePathFromSongName(songname));
            currentSong = song;
            System.out.println("artist : " + song.getArtist() + ".");
        } catch (Exception e){
            //TODO:throw new SongNotExistsException("Song " + songname + " existiert nicht.");
        }

    }

    public Song loadSongMetaData(String songname) throws InvalidDataException, UnsupportedTagException, IOException {
        Playlist allSongs = PlaylistHandler.getAllSongs();

        Song song = allSongs.searchFor(songname);

        if (song == null) {
            //TODO:throw new SongNotExistsException("Song " + songname + " existiert nicht in AllSongs.");
        }

        System.out.println("Metadaten werden geladen für: " + getFullMusicPath(song.getTITLE()));

        AudioPlayer player = minim.loadFile(getFullMusicPath(song.getTITLE()));
        AudioMetaData meta = player.getMetaData();

        song.setFilename(songname);
        song.setArtist(meta.author());
        song.setAlbum(meta.album());
        song.setGenre(meta.genre());
        song.setFileLengthMs(meta.length());
        song.setDate(meta.date());
        song.setLyrics(meta.lyrics());
        song.setComment(meta.comment());
        song.setCopyright(meta.copyright());
        song.setPublisher(meta.publisher());

        Mp3File mp3 = new Mp3File(getFullMusicPath(song.getTITLE()));
        if (mp3.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3.getId3v2Tag();
            byte[] imageData = id3v2Tag.getAlbumImage();

            if (imageData != null) {
                //System.out.println("Hier Cover gefunden. Größe: " + imageData.length + " Bytes");
                song.setCoverImage(imageData);
            } else {
                System.out.println("Kein Cover in " + song.getTITLE() + ".mp3 vorhanden");
            }
        } else {
            System.out.println("Keine ID2v3 Tags in " + song.getTITLE());
        }

        return song;
    }

    public void pause(){
        audioPlayer.setGain(currentVolume);
        audioPlayer.pause();
    }

    public SimpleAudioPlayer getAudioPlayer(){
        return audioPlayer;
    }

    public void volume(float value){
        currentVolume = value;
        audioPlayer.setGain(value);
    }

    public Song getCurrentSong() {
        return currentSong;
    }
}