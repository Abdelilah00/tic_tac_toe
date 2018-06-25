package tac.tic.com.tictactoe;


import android.app.Application;

public class GlobalVar extends Application{
    private boolean auto_mode;
    private int nv_auto_mode;

    public boolean getAuto_mode() {
        return auto_mode;
    }

    public void setAuto_mode(boolean auto_mode) {
        this.auto_mode = auto_mode;
    }

    public int getNv_auto_mode() {
        return nv_auto_mode;
    }

    public void setNv_auto_mode(int nv_auto_mode) {
        this.nv_auto_mode = nv_auto_mode;
    }
}
