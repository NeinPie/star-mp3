package visual.components.main;

import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.entity.Song;

public class Star2 extends ImageView{
    Image ima;
    Image ima_active;
    Vertex<String> node;
    SmartGraphVertex<String> smartnode;
    Song song;
    boolean act;
    public Star2(Image ima, Image ima_active){
        super(ima);
        this.ima = ima;
        this.node = null;
        this.smartnode = null;
        this.song = null;
        this.ima_active = ima_active;
        this.act = false;
    }

    public void setNode(Vertex<String> node){
        this.node = node;
    }

    public void setSmartnode(SmartGraphVertex<String> smartnode){
        this.smartnode = smartnode;
    }

    public void setSong(Song song){
        this.song = song;
    }

    public Image getIma(){
        return ima;
    }

    public Vertex<String> getNode(){
        return node;
    }

    public SmartGraphVertex<String> getSmartNode(){
        return smartnode;
    }

    public Song getSong(){
        return song;
    }

    public boolean getAct(){
        return act;
    }

    public void switchImage(){
        if(this.act == true){
            this.act = false;
            this.setImage(this.ima);
        }
        else{
            this.act = true;
            this.setImage(this.ima_active);
        }
    }
}