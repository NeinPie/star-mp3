package logic.entity;
import javafx.beans.property.SimpleObjectProperty;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class PlayerQueue extends ArrayList<Song> {
    private ArrayList<Song> unshuffled;
    private final SimpleObjectProperty<Song> currentSong;
    private final Deque<Song> history;
    private boolean repeat;
    private int currentIndex;

    public PlayerQueue() {
        super();
        this.unshuffled = new ArrayList<>();
        this.currentSong = new SimpleObjectProperty<>(this, "currentSong");
        this.history = new ArrayDeque<>();
        this.repeat = false;
        this.currentIndex = -1;
    }

    public void addSongs(ArrayList < Song > songs) {
        addAll(songs);
        if (currentIndex == -1 && !isEmpty()) {
            resetCurrentToStart();
        }
    }

//    public void setSongs(List < Song > songs) {
//        clear();
//        addAll(songs);
//        resetCurrentToStart();
//    }
    private void resetCurrentToStart() {
        if (isEmpty()) {
            currentIndex = -1;
            currentSong.set(null);
            unshuffled.clear();
            history.clear();
            return;
        }
        currentIndex = 0;
        currentSong.set(get(currentIndex));
        unshuffled = new ArrayList<> (this);
        history.clear();
    }

    public Song getCurrentSong() {
        if (currentSong.get() == null && !isEmpty()) {
            resetCurrentToStart();
        }
        return currentSong.get();
    }

    public Song getNextSong() {
        if (repeat) {
            return getCurrentSong();
        }

        if (currentIndex + 1 >= size()) {
            return null;
        }
        if (currentIndex >= 0 && currentIndex < size()) {
            history.addLast(get(currentIndex));
        }
        currentIndex++;
        currentSong.set(get(currentIndex));
        return currentSong.get();
    }
    public Song getPreviousSong() {
        if (repeat) {
            return getCurrentSong();
        }
        if (currentIndex <= 0) {
            return getCurrentSong();
        }
        currentIndex--;
        currentSong.set(get(currentIndex));
        if (!history.isEmpty()) {
            history.removeLast();
        }
        return currentSong.get();
    }
    public void shuffleQueue() {
        if (isEmpty()) {
            return;
        }
        if (unshuffled.isEmpty()) {
            unshuffled = new ArrayList<>(this);
        }
        Song keepCurrent = getCurrentSong();
        ArrayList<Song> remaining = new ArrayList<>(this);
        remaining.remove(keepCurrent);
        Collections.shuffle(remaining);
        clear();
        add(keepCurrent);
        addAll(remaining);
        currentIndex = 0;
    }
    public void unshuffleQueue() {
        if (unshuffled == null || unshuffled.isEmpty()) {
            return;
        }
        Song keepCurrent = getCurrentSong();
        clear();
        addAll(unshuffled);
        int idx = indexOf(keepCurrent);
        if (idx < 0 && !isEmpty()) {
            idx = 0;
        }
        currentIndex = idx;
        currentSong.set(idx >= 0 && idx < size() ? get(currentIndex) : null);
    }
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
//    public boolean getRepeat() {
//        return repeat;
//    }
    @Override public String toString() {
        String queueString = "Queue:\n";
        for (Song s: this) {
            queueString += "\t" + s.getTITLE() + "\n";
        }
        return queueString;
    }
    public final SimpleObjectProperty < Song > currentSongProperty() {
        return this.currentSong;
    }
//    public void setCurrentSong(Song song) {
//        if (song == null) {
//            currentIndex = -1;
//            currentSong.set(null);
//            return;
//        }
//        int idx = indexOf(song);
//        if (idx < 0) {
//            clear();
//            add(song);
//            unshuffled = new ArrayList < > (this);
//            idx = 0;
//        }
//        currentIndex = idx;
//        currentSong.set(song);
//    }
//    public boolean hasNext() {
//        return repeat || currentIndex + 1 < size();
//    }
}