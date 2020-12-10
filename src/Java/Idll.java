package src.Java;
import com.sun.jna.Library;

// interface for dll reference
public interface Idll extends Library {
    void change_wallpaper();
}