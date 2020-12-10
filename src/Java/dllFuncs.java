package src.Java;

import com.sun.jna.Native;
import com.sun.jna.Library;

public class dllFuncs {

    public interface Idll extends Library {
        // creates interface that loads the dll file
        Idll INSTANCE = (Idll)Native.load("PATH TO .dll FILE", Idll.class);

        void Java_dllFuncs_change_wallpaper();
    }

    // constructor makes instance and calls the dll function
    public dllFuncs() {
        Idll dllRef = Idll.INSTANCE;

        dllRef.Java_dllFuncs_change_wallpaper();
    }

}