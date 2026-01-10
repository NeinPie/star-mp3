package visual.components.main;

import com.brunomnsilva.smartgraph.graph.Vertex;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Moon extends ImageView{
    Image moon_play;
    Image moon_playHOVER;
    Image moon_stop;
    Image moon_stopHOVER;
    Vertex<String> node;
    boolean play_active;
    public Moon(Image moon_play, Image moon_playHOVER, Image moon_stop, Image moon_stopHOVER, Vertex<String> node){
        super(moon_play);
        this.moon_play = moon_play;
        this.moon_playHOVER = moon_playHOVER;
        this.moon_stop = moon_stop;
        this.moon_stopHOVER = moon_stopHOVER;
        this.node = node;
        this.play_active = false;
    }


    public Vertex<String> getNode(){
        return node;
    }

    public void hover(){
        if(play_active == true){
            this.setImage(moon_stopHOVER);
        }
        else if(play_active == false){
            this.setImage(moon_playHOVER);
        }
    }

    public void unhover(){
        if(play_active == true){
            this.setImage(moon_stop);
        }
        else if(play_active == false){
            this.setImage(moon_play);
        }
    }
}