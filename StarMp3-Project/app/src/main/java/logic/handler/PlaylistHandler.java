package logic.handler;

import logic.entity.Playlist;
import logic.exception.DirectoryNotExistsException;
import java.io.File;
import java.util.HashMap;

/**
 * Verwaltet das Laden der Playlists
 */
public class PlaylistHandler {
    protected static HashMap<String, Playlist> playlists =new HashMap<>();
    private static Playlist allSongs;

    /**
     * Lädt alle im Player verfügbaren Songs
     */
    public static void loadAllSongs() {
        allSongs = loadPlaylist("AllSongs");
        loadAllPlaylists();
    }

    /**
     * Lädt den Inhalt aller Playlists
     */
    public static void loadAllPlaylists(){
        File directory = new File(M3uHandler.PLAYLIST_PATH);

        File[] files = directory.listFiles();

        if (files != null) {
            /**
             * Iteriert durch alle Files im Playlist-directory, die mit .m3u enden und lädt deren Inhalt
             */
            for (File file : files) {

                if (file.isFile() && file.getName().endsWith(".m3u")) {
                    //System.out.println("Gefundene Datei: " + file.getName());
                    loadPlaylist(removeM3uEnding(file.getName()));
                }
            }
        } else {
            throw new DirectoryNotExistsException("Das Verzeichnis " + directory.getPath() + " existiert nicht.");
        }
    }

    /**
     * Lädt die Playlist aus ihrem Namen heraus
     * @param playlistName Name der Playlist
     * @return Playlist als Playlist Entity mit den entsprechenden Songs gefüllt
     */
    public static Playlist loadPlaylist(String playlistName) {
        if(playlists.containsKey(playlistName)) {
            /**
             * Laden der Songs mithilfe des M3uHandlers
             */
            playlists.replace(playlistName, new Playlist(playlistName, M3uHandler.loadSongsFromPlaylist(playlistName)));
        } else {
            /**
             * Erstellt die Playlist, wenn noch nicht vorhanden
             */
            M3uHandler.addSongToPlaylist("", playlistName);
            playlists.put(playlistName, new Playlist(playlistName,M3uHandler.loadSongsFromPlaylist(playlistName)));
        }
        return playlists.get(playlistName);
    }

    /**
     * @param songname Name eines songs
     * @return Den Pfad, unter dem die mp3 Datei zu finden ist
     */
    public static String getFullMusicPath(String songname){
        return "src/main/resources/music/" + songname + ".mp3";
    }

    /**
     * Entfernt mithilfe der getFullMusicPath-Methode den Pfad von einem Songnamen
     * @param songNameWithPath
     * @return Songname ohne Pfad
     */
    public static String removePathFromSongName(String songNameWithPath) {
        final String TESTPATH = "TESTPATH";

        String testpathPath = getFullMusicPath(TESTPATH);
        String[] pathValues = testpathPath.split(TESTPATH);

        String prefix = pathValues[0];
        String suffix = pathValues[1];

        String songNameWithoutPath = songNameWithPath.replace(prefix, "");
        songNameWithoutPath = songNameWithoutPath.replace(suffix, "");

        return songNameWithoutPath;
    }

    /**
     * @return Gibt eine Playlist mit allen Songs, die im Mp3Player verfügbar sind, zurück
     */
    public static Playlist getAllSongs() {
        return allSongs;
    }

    /**
     * @return Gibt alle verfügbaren Playlists zurück
     */
    public static HashMap<String, Playlist> getPlaylists(){
        return playlists;
    }

    /**
     * Entfernt die .m3u Dateiendung eines Filenamens
     * @param filename Name des Files.m3u
     * @return Name des Files ohne Endung
     */
    public static String removeM3uEnding(String filename){
        if(filename.contains(".m3u")){
            filename = filename.replace(".m3u", "");
        } else {
            System.err.println(filename +" ist keine Playlist. Die Endung .m3u kann hier nicht entfernt werden.");
        }
        return filename;
    }
}