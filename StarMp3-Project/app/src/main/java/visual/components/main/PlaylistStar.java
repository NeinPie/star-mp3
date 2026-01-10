package visual.components.main;

import com.brunomnsilva.smartgraph.graph.Vertex;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.entity.Playlist;

public class PlaylistStar extends ImageView{
    Image ima;
    Vertex<String> node;
    Playlist playlist;
    public PlaylistStar(Image ima, Vertex<String> node, Playlist playlist){
        super(ima);
        this.ima = ima;
        this.node = node;
        this.playlist = playlist;
    }

    public Image getIma(){
        return ima;
    }

    public Vertex<String> getNode(){
        return node;
    }

    public Playlist getPlaylist(){
        return playlist;
    }
}