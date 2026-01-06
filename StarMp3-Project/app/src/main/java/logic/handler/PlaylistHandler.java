package logic.handler;

import logic.entity.Playlist;
import logic.entity.Song;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class PlaylistHandler {
    protected static HashMap<String, Playlist> playlists =new HashMap<>();
    private static Playlist allSongs;

    public static void loadAllSongs() {
        allSongs = loadPlaylist("AllSongs");
        loadAllPlaylists();
    }

    public static void loadAllPlaylists(){
        File directory = new File(M3uHandler.PLAYLIST_PATH);

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {

                if (file.isFile() && file.getName().endsWith(".m3u")) {
                    //System.out.println("Gefundene Datei: " + file.getName());
                    loadPlaylist(removeM3uEnding(file.getName()));
                }
            }
        } else {
            //TODO: throw new DirectoryNotExistsException("Das Verzeichnis " + directory.getPath() + " existiert nicht.");
        }
    }

    public static Playlist loadPlaylist(String playlistName) {
        if(playlists.containsKey(playlistName)) {
            playlists.replace(playlistName, new Playlist(playlistName, M3uHandler.loadSongsFromPlaylist(playlistName)));
        } else {
            M3uHandler.addSongToPlaylist("", playlistName);
            playlists.put(playlistName, new Playlist(playlistName,M3uHandler.loadSongsFromPlaylist(playlistName)));
        }
        return playlists.get(playlistName);
    }

    public static Song getRandomSong(String playlistName) {
        Playlist playlist = playlists.get(playlistName);
        int playlistLength = playlist.getSongs().size();

        return playlist.getSongs().get(new Random().nextInt(playlistLength));
    }

    public static String getFullMusicPath(String songname){
        return "app/src/main/resources/music/" + songname + ".mp3";
    }

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

    public static Playlist getAllSongs() {
        return allSongs;
    }

    public static HashMap<String, Playlist> getPlaylists(){
        return playlists;
    }

    public static String removeM3uEnding(String filename){
        if(filename.contains(".m3u")){
            filename = filename.replace(".m3u", "");
        } else {
            System.err.println(filename +" ist keine Playlist. Die Endung .m3u kann hier nicht entfernt werden.");
        }
        return filename;
    }
}