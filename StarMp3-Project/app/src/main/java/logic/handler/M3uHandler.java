package logic.handler;

import logic.entity.Song;
import logic.exception.FileReadException;
import logic.exception.FileWriteException;

import java.io.*;
import java.util.ArrayList;

/**
 * M3uHandler liest die Songs der einzelnen Playlists aus den .m3u-Dateien und bietet auch die Option, diese zu bearbeiten.
 */
public class M3uHandler {
    public static final String PLAYLIST_PATH = "app/src/main/resources/playlists/";

    /**
     * Liest Playlist aus .m3u-File ein und stellt die Songs als ArrayList<Song> zur Verfügung
     * @param name Name der Playlist, die eingelesen werden soll
     * @return ArrayList aus den Namen der eingelesenen Songs
     */
    public static ArrayList<Song> loadSongsFromPlaylist(String name){
        ArrayList<Song> songs = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(PLAYLIST_PATH +name + ".m3u"))){
            String line;
            String[] tokens;

            /**
             * Iteration über alle verfügbaren Zeilen der Datei
             */
            while((line = reader.readLine()) != null) {
                tokens = line.split(";");
                String title;
                title = tokens[0];
                /**
                 * Hinzufügen des Songtitels zur Liste
                 */
                songs.add(new Song(title));
            }

        } catch (IOException e) {
            throw new FileReadException("Beim Einlesen von "+PLAYLIST_PATH+name+".m3u ist ein Fehler aufgetreten",e.getMessage());
        }
        return songs;
    }

    /**
     * Bietet die Option, ein .m3u File um einen Song zu ergänzen
     * @param title Name des Songs, der ergänzt werden soll
     * @param playlistName Name der Playlist, in die der Song ergänzt werden soll
     */
    public static void addSongToPlaylist(String title, String playlistName){
        final String FILENAME = PLAYLIST_PATH+ playlistName + ".m3u";

        File file = new File(FILENAME);
        if(!file.exists()){
            FileWriter writer;
            try
            {
                /**
                 * Playlist wird als .m3u File erstellt, wenn noch nicht vorhanden
                 */
                writer = new FileWriter(FILENAME);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                /**
                 * Songtitel wird hinzugefügt
                 */
                if(!title.isEmpty()){
                    bufferedWriter.write(title + ";");
                }

                bufferedWriter.close();
                System.out.println(FILENAME + " erfolgreich erstellt.");
            }
            catch (IOException e)
            {
                throw new FileWriteException("Lesen der Datei "+FILENAME + " fehlgeschlagen", e.getMessage());
            }
        } else {
            FileReader reader;
            String fileContent = "";
            try{
                /**
                 * File wird einmal komplett eingelesen, wenn die Playlist bereits existiert
                 */
                reader = new FileReader(FILENAME);
                BufferedReader bufferedReader = new BufferedReader(reader);

                while(bufferedReader.ready()){
                    fileContent += bufferedReader.readLine() + "\n";
                }
                bufferedReader.close();

                fileContent = fileContent.trim();
            } catch (IOException e) {
                throw new FileReadException("Playlist " + playlistName + " konnte nicht erstellt werden", e.getMessage());
            }

            FileWriter writer;

            try
            {
                /**
                 * Alter Content wird mit neuem Content am Ende in das m3u File zurück geschrieben
                 */
                writer = new FileWriter(FILENAME);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                if(!title.isEmpty()){
                    bufferedWriter.write(fileContent + title + ";");
                } else {
                    bufferedWriter.write(fileContent);
                }

                bufferedWriter.close();

                if(!title.isEmpty()){
                    System.out.println(FILENAME + " erfolgreich editiert. [Song hinzugefügt: "+ title +"]");
                } else {
                    System.out.println(FILENAME + " erfolgreich geladen.");
                }
            }
            catch (IOException e)
            {
                throw new FileWriteException("Playlist " + playlistName + " konnte nicht bearbeitet werden.", e.getMessage());
            }
        }
    }
}