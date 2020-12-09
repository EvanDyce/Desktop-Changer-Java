package src.Java;

import com.sun.jna.Native;
import com.sun.jna.Library;

public class dllFuncs {

    public interface Idll extends Library {
        Idll INSTANCE = (Idll)Native.load("PATH TO .dll FILE", Idll.class);

        void Java_dllFuncs_change_wallpaper();
    }

    public dllFuncs() {
        Idll gang = Idll.INSTANCE;

        gang.Java_dllFuncs_change_wallpaper();
    }

    public static void main(String[] args) {
        Idll gang = Idll.INSTANCE;

        gang.Java_dllFuncs_change_wallpaper();
    
    }
}