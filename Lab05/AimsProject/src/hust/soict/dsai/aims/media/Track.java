package hust.soict.dsai.aims.media;

import java.util.Objects;

import hust.soict.dsai.aims.exception.PlayerException;

public class Track implements Playable {
    private String title;
    private int length;

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
        if (length <= 0) {
            String message = "Cannot play track \"" + title + "\" because its length is non-positive.";
            System.err.println(message);
            throw new PlayerException(message);
        }

        System.out.println("Playing track: " + title);
        System.out.println("Track length: " + length);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Track)) {
            return false;
        }

        Track other = (Track) obj;
        return Objects.equals(this.title, other.title) && this.length == other.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, length);
    }

    @Override
    public String toString() {
        return "Track - " + title + " - Length: " + length;
    }
}
