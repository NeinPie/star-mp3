package logic;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import ddf.minim.AudioMetaData;
import ddf.minim.AudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleObjectProperty;
import logic.entity.Playlist;
import logic.entity.Song;
import logic.exception.SongNotExistsException;
import logic.handler.M3uHandler;
import logic.handler.PlaylistHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static logic.handler.PlaylistHandler.getFullMusicPath;

/**
 * Verwaltet das Spielen der Songs
 */
public class Mp3Player {
    private final SimpleMinim minim;
    private SimpleAudioPlayer audioPlayer;
    private float currentVolume;
    private SimpleObjectProperty<Song> currentSong;
    private volatile boolean paused;
    private Runnable songCompletelyPlayed;
    private volatile ArrayList<Song> queue;
    private ArrayList<Song> unshuffled;
    private volatile List<Song> playedQueue;

    /**
     * TODO: lautstärke
     */
    public Mp3Player(){
        minim = new SimpleMinim(true);
        PlaylistHandler.loadAllSongs();
        playedQueue = new ArrayList<>();
        unshuffled = new ArrayList<>();
        currentVolume = 3;

        currentSong = new SimpleObjectProperty<>(this, "currentSong");
    }

    /**
     * Setzt einen pausierten Song fort
     */
    public void play() {
        audioPlayer.play();
    }

    /**
     * Pausiert das Abspielen des aktuellen Songs
     */
    public void pause() {
        if(audioPlayer!=null){
            audioPlayer.pause();
        }
    }

    /**
     * Spielt die Lieder einer Playlist in zufälliger Reihenfolge ab
     * @param name Name der Playlist
     */
    public void playShuffledPlaylist(String name){
        if(!PlaylistHandler.getPlaylists().containsKey(name)){
            PlaylistHandler.loadPlaylist(name);
        } else {
            /**
             * Fügt die Songs der Playlist zur Queue hinzu, mischt diese und Startet das Abspielen
             */
            Playlist playlist = PlaylistHandler.getPlaylists().get(name);
            queue = playlist.getSongs();
            Collections.shuffle(queue);

            try {
                playQueue(name);
            } catch (Exception e) {
                e.printStackTrace(); //TODO: Handle Exceptions
            }
        }
    }

    /**
     * Spielt die Lieder einer Playlist in Reihenfolge ab
     * @param name Name der Playlist
     */
    public void playPlaylist(String name){
        if(!PlaylistHandler.getPlaylists().containsKey(name)){
            PlaylistHandler.loadPlaylist(name);
        } else {
            /**
             * Fügt die Songs der Playlist zur Queue hinzu, mischt diese und Startet das Abspielen
             */
            Playlist playlist = PlaylistHandler.getPlaylists().get(name);
            queue = playlist.getSongs();

            try {
                playQueue(name);
            } catch (Exception e) {
                e.printStackTrace(); //TODO: Handle Exceptions
            }
        }
    }

    /**
     * Spielt die Queue in ihrer Reihenfolge ab
     * @param playlistName Name der Playlist, die gequeued wurde
     */
    private void playQueue(String playlistName) throws InvalidDataException, UnsupportedTagException, IOException {
        /**
         * Abspielen und Löschen des ersten Songs der Queue (und hinzufügen zur playedQueue)
         */
        if(!queue.isEmpty()){
            playSong(queue.get(0).getTITLE(), 0);
            Song song = queue.get(0);
            queue.remove(0);

            playedQueue.addLast(song);
        }

        while(!queue.isEmpty() && !paused){
            /**
             * Spielt den nächsten Song der Queue, wenn da noch einer ist und nicht pausiert wurde
             */
            Song nextSong = queue.get(0);
            if(nextSong == null){
                System.out.println("Playlist " + playlistName + " beendet.");
                //new Mp3Alert(Alert.AlertType.INFORMATION,"Information","Playlist beendet","Starte eine andere Playlist, um weiterhin Musik zu hören.",5000);
            } else {
                currentSong = new SimpleObjectProperty<>(nextSong);
                playSong(nextSong.getTITLE(),0);
                queue.remove(0);
                playedQueue.addLast(nextSong);
            }
        }

        //new Mp3Alert(Alert.AlertType.INFORMATION,"Information","Playlist beendet","Starte eine andere Playlist, um weiterhin Musik zu hören.",5000);
    }

    /**
     * Mischt die Queue durch
     */
    public void mixQueue(){
        unshuffled = (ArrayList<Song>) queue.clone();
        Collections.shuffle(queue);
        printQueue();
    }

    /**
     * Macht das Mischen der Queue rückgängig und lässt sie ab dem aktuellen Song wieder in der vorherigen Reihenfolge spielen
     */
    public void unmixQueue() {
        if (queue.isEmpty() || currentSong == null) {
            //new Mp3Alert(Alert.AlertType.INFORMATION, "Information", "Playlist beendet", "Queue kann nicht geändert werden.", 3000);
            return;
        }

        int index = -1;
        for (int i = 0; i < unshuffled.size(); i++) {
            /**
             * Setzt index auf den Index des aktuellen Songs
             */
//            if (unshuffled.get(i).getTITLE().equals(currentSong.getTITLE())) {
//                index = i;
//                break;
//            }
        }
        /**
         * Löscht alle Songs vor bis inklusive dem aktuellem Song aus unshuffled
         */
        if (index != -1) {
            unshuffled.subList(0, index).clear();
        }


        queue = new ArrayList<>(unshuffled);
        printQueue();
    }

    /**
     * Funktion zum Ansehen der Queue in der Konsole
     */
    private void printQueue(){
        String queueString = "Queue:\n";
        for(Song s : queue){
            queueString+="\t"+s.getTITLE()+"\n";
        }
        System.out.println(queueString);
    }

    /**
     * Spielt einen Song ab
     * @param songname Name des Songs
     * @param milliseconds Zeitpunkt, ab dem der Song abgespielt werden soll in Millisekunden
     */
    public void playSong(String songname, int milliseconds) throws InvalidDataException, UnsupportedTagException, IOException {
        if(audioPlayer != null &&audioPlayer.isPlaying()){
            audioPlayer.pause();
        }

        final String SONG_PATH = getFullMusicPath(songname);
        /**
         * Laden + Abspielen des Songs
         */
        audioPlayer = minim.loadMP3File(SONG_PATH);
        //currentSong = loadSongMetaData(PlaylistHandler.removePathFromSongName(SONG_PATH));
        audioPlayer.play(milliseconds);

        /**
         * Eventuelles Abspielen der Queue, wenn vorhanden
         */
        if (songCompletelyPlayed != null) {
            songCompletelyPlayed.run();
        }
    }

    /**
     * Laden der Metadaten des Songs
     * @param songname Name des Songs, dessen Metadaten geladen werden sollen
     * @return Song-Entity mit Metadaten
     * @throws InvalidDataException Für MP3File Entity notwendig
     * @throws UnsupportedTagException Für MP3File Entity notwendig
     * @throws IOException Für MP3File Entity notwendig
     */
    public Song loadSongMetaData(String songname) throws InvalidDataException, UnsupportedTagException, IOException {
        Playlist allSongs = PlaylistHandler.getAllSongs();

        Song song = allSongs.searchFor(songname);

        if (song == null) {
            throw new SongNotExistsException("Dein Lied ist nicht vorhanden","Song " + songname + " existiert nicht in AllSongs.");
        }

        System.out.println("Metadaten werden geladen für: " + getFullMusicPath(song.getTITLE()));

        AudioPlayer player = minim.loadFile(getFullMusicPath(song.getTITLE()));
        AudioMetaData meta = player.getMetaData();

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

    public void addSongToFavorites(){
        M3uHandler.addSongToPlaylist(getCurrentSong().getTITLE(),"favorites");
        PlaylistHandler.getPlaylists().get("favorites").addSong(getCurrentSong());
        System.out.println("added " + getCurrentSong().getTITLE() + " to fav");
    }
    public void removeSongFromFavorites(){
        M3uHandler.removeFromPlaylist(getCurrentSong().getTITLE(),"favorites");
        PlaylistHandler.getPlaylists().get("favorites").removeSong(getCurrentSong());
        System.out.println("removed " + getCurrentSong().getTITLE() + " from fav");
    }

    /**
     * Methode zum Verändern der Position im Song
     * <br>
     * -! aktuell nicht Lauffähig!
     * @param ms neue Position im Song
     */
    public void replaySong(int ms){
        if(audioPlayer != null && currentSong != null){
                audioPlayer.pause();
                audioPlayer.rewind();
                audioPlayer.play(ms);
        }
    }

    /**
     * Bricht das Spielen des aktuellen Songs ab
     */
    public void stop(){
        if (audioPlayer != null) {
            audioPlayer.pause();
            audioPlayer.rewind();
        }
    }

    /**
     * Springt einen Song zurück
     */
    public void skipSongBack(){
        if(audioPlayer != null){
            stop();
            queue.addFirst(playedQueue.getFirst());
            playedQueue.remove(0);
        }
    }

    /**
     * Springt einen Song vor
     */
    public void skipSong(){
        pause();
    }

    public void setSongCompletelyPlayed(Runnable played) {
        this.songCompletelyPlayed = played;
    }

    /**
     * Setzt Lautstärke des Spielers
     * @param value Wert für das neue Gain des AudioPlayers
     */
    public void volume(float value){
        currentVolume = value;
        audioPlayer.setGain(value);
    }

    public final SimpleObjectProperty<Song> currentSongProperty() {
        return this.currentSong;
    }

    public final Song getCurrentSong() {
        return this.currentSong.get();
    }

    public float getCurrentVolume(){
        return currentVolume;
    }
    public List<Song> getQueue() {
        return queue;
    }
}