package tac.tic.com.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    GlobalVar globalVar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globalVar = (GlobalVar) getApplicationContext();
    }

    public void clk_dual(View view) {
        globalVar.setAuto_mode(false);
        startActivity(new Intent(getApplicationContext(), GameActivity.class));
    }

    public void clk_auto(View view) {
        globalVar.setAuto_mode(true);
        startActivity(new Intent(getApplicationContext(), GameActivity.class));
    }


}
