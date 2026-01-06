package logic.handler;

import logic.entity.Song;

import java.io.*;
import java.util.ArrayList;

public class M3uHandler {
    public static final String PLAYLIST_PATH = "app/src/main/resources/playlists/";

    public static ArrayList<Song> loadSongsFromPlaylist(String name){
        ArrayList<Song> songs = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(PLAYLIST_PATH +name + ".m3u"))){
            String line;
            String[] tokens;

            while((line = reader.readLine()) != null) {
                //System.out.println(line);
                tokens = line.split(";");
                String title;
                title = tokens[0];
                songs.add(new Song(title));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return songs;
    }

    public static void addSongToPlaylist(String title, String playlistName){
        final String FILENAME = PLAYLIST_PATH+ playlistName + ".m3u";

        File file = new File(FILENAME);
        if(!file.exists()){
            FileWriter writer;
            try
            {
                writer = new FileWriter(FILENAME);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                if(!title.isEmpty()){
                    bufferedWriter.write(title + ";");
                }

                bufferedWriter.close();
                System.out.println(FILENAME + " erfolgreich erstellt.");
            }
            catch (IOException except)
            {
                except.printStackTrace();
            }
        } else {
            FileReader reader;
            String fileContent = "";
            try{
                reader = new FileReader(FILENAME);
                BufferedReader bufferedReader = new BufferedReader(reader);

                while(bufferedReader.ready()){
                    fileContent += bufferedReader.readLine() + "\n";
                }
                bufferedReader.close();

                fileContent = fileContent.trim();
            } catch (IOException e) {
                //throw new PlaylistCreateException("playlists " + playlistName + " konnte nicht erstellt werden.");
            }

            FileWriter writer;

            try
            {
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
            catch (IOException except)
            {
                except.printStackTrace();
                System.out.println(except.getMessage());
                //throw new PlaylistCreateException("playlists " + playlistName + " konnte nicht bearbeitet werden.");
            }
        }
    }
}