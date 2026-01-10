package visual.components.main;

import com.brunomnsilva.smartgraph.graphview.SmartGraphVertex;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.entity.Song;

public class Star2 extends ImageView{
    Image ima;
    Image ima_active;
    SmartGraphVertex<String> node;
    Song song;
    boolean act;
    public Star2(Image ima, Image ima_active, SmartGraphVertex<String> node, Song song){
        super(ima);
        this.ima = ima;
        this.node = node;
        this.song = song;
        this.ima_active = ima_active;
        this.act = false;
    }

    public Image getIma(){
        return ima;
    }

    public SmartGraphVertex<String> getNode(){
        return node;
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