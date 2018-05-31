package tac.tic.com.tictactoe;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    int nv_autoPlayer=2;
    boolean auto_player;
    boolean set_image = true;
    int playerX_win = 0;
    int playerY_win = 0;
    int round_run = 0;
    int click_number = 0;
    Handler handler = new Handler();
    Button A1,A2,A3,B1,B2,B3,C1,C2,C3;
    TextView count_playerX,count_playerO,count_round;
    GlobalVar globalVar = new GlobalVar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        globalVar = (GlobalVar)getApplicationContext();
        count_playerO = findViewById(R.id.tv_count_playerO);
        count_playerX = findViewById(R.id.tv_count_playerX);
        count_round = findViewById(R.id.tv_count_round);

        A1 = findViewById(R.id.A1);
        A2 = findViewById(R.id.A2);
        A3 = findViewById(R.id.A3);
        B1 = findViewById(R.id.B1);
        B2 = findViewById(R.id.B2);
        B3 = findViewById(R.id.B3);
        C1 = findViewById(R.id.C1);
        C2 = findViewById(R.id.C2);
        C3 = findViewById(R.id.C3);
        auto_player = globalVar.getAuto_mode();

        check_move_Auto_player();
        nv_autoPlayer = globalVar.getNv_auto_mode();
    }
    public void buttom_click(View view) {
        Button btn = (Button) view;

        if (set_image)  {
            btn.setBackgroundResource(R.drawable.ic_croix);
            btn.setText("X");
        } else {
            btn.setBackgroundResource(R.drawable.ic_circle);
            btn.setText("O");
        }

        set_image = !set_image;
        btn.setEnabled(false);
        click_number++;

        if (click_number == 9) {
            check_player_winner();
            Toast.makeText(getApplicationContext(), "game over try other parte", Toast.LENGTH_LONG).show();
            handler.postDelayed(new Runnable() {
                public void run() {
                    other_parte();
                }
            }, 1000);
        } else {
            check_move_Auto_player();
        }
        check_player_winner();
    }
    private void check_move_Auto_player(){
        if (auto_player && !set_image && nv_autoPlayer == 2) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Move_autoPlayer2();
                }
            }, 500);
        } else if (auto_player && !set_image && nv_autoPlayer == 1) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Move_autoPlayer1();
                }
            }, 300);
        }
    }
    private void DisableButton(){
        A1.setEnabled(false);
        A2.setEnabled(false);
        A3.setEnabled(false);
        B1.setEnabled(false);
        B2.setEnabled(false);
        B3.setEnabled(false);
        C1.setEnabled(false);
        C2.setEnabled(false);
        C3.setEnabled(false);
    }
    private void Player_winner_is(Button btn) {
        if (btn.getText() == "X") {
            playerX_win++;
        } else playerY_win++;
        DisableButton();
        Toast.makeText(getApplicationContext(), "the winner is :" + btn.getText(), Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                other_parte();
        }
        }, 2000);
    }
    private void other_parte() {
        check_move_Auto_player();
        round_run++;
        click_number = 0;
                    A1.setEnabled(true);
                    A1.setText("");
        A1.setBackgroundResource(R.drawable.shapr_button);
                    A2.setEnabled(true);
                    A2.setText("");
        A2.setBackgroundResource(R.drawable.shapr_button);
                    A3.setEnabled(true);
                    A3.setText("");
        A3.setBackgroundResource(R.drawable.shapr_button);
                    B1.setEnabled(true);
                    B1.setText("");
        B1.setBackgroundResource(R.drawable.shapr_button);
                    B2.setEnabled(true);
                    B2.setText("");
        B2.setBackgroundResource(R.drawable.shapr_button);
                    B3.setEnabled(true);
                    B3.setText("");
        B3.setBackgroundResource(R.drawable.shapr_button);
                    C1.setEnabled(true);
                    C1.setText("");
        C1.setBackgroundResource(R.drawable.shapr_button);
                    C2.setEnabled(true);
                    C2.setText("");
        C2.setBackgroundResource(R.drawable.shapr_button);
                    C3.setEnabled(true);
                    C3.setText("");
        C3.setBackgroundResource(R.drawable.shapr_button);

        count_playerX.setText(String.valueOf(playerX_win));
        count_playerO.setText(String.valueOf(playerY_win));
        count_round.setText(String.valueOf(round_run));
    }
    private void check_player_winner() {
        // ligne teste
        if ((A1.getText() == A2.getText()) && (A2.getText() == A3.getText()) && !A1.isEnabled())
        {
            Player_winner_is(A1);
        }
        else if ((B1.getText() == B2.getText()) && (B2.getText() == B3.getText()) && !B1.isEnabled())
        {
            Player_winner_is(B1);

        }
        else if ((C1.getText() == C2.getText()) && (C2.getText() == C3.getText()) && !C1.isEnabled())
        {
            Player_winner_is(C1);
        }

        // colonne teste
        else if ((A1.getText() == B1.getText()) && (B1.getText() == C1.getText()) && !A1.isEnabled())
        {
            Player_winner_is(A1);
        }
        else if ((A2.getText() == B2.getText()) && (B2.getText() == C2.getText()) && !A2.isEnabled())
        {
            Player_winner_is(A2);
        }
        else if ((A3.getText() == B3.getText()) && (B3.getText() == C3.getText()) && !A3.isEnabled())
        {
            Player_winner_is(A3);
        }

        // diagonale teste
        else if ((A1.getText() == B2.getText()) && (B2.getText() == C3.getText()) && !A1.isEnabled())
        {
            Player_winner_is(A1);
        }
        else if ((A3.getText() == B2.getText()) && (B2.getText() == C1.getText()) && !A3.isEnabled())
        {
            Player_winner_is(A3);
        }
    }

    private void Move_autoPlayer1() {
        //priority 1:  get tick tac toe
        //priority 2:  block x tic tac toe
        //priority 3:  go for corner space
        //priority 4:  pick open space

        Button move;

        //look for tic tac toe opportunities
        move = look_for_win_or_block("O"); //look for win
        if (move == null)
        {
            move = look_for_win_or_block("X"); //look for block
            if (move == null)
            {
                move = look_for_corner1();
                if (move == null)
                {
                    move = look_for_open_space();
                }//end if
            }//end if
        }//end if

        assert move != null;
        move.performClick();
    }
    private void Move_autoPlayer2() {
        //priority 1:  get tick tac toe
        //priority 2:  block x tic tac toe
        //priority 3:  go for corner space
        //priority 4:  pick open space

        Button move;

        //look for tic tac toe opportunities
        move = look_for_win_or_block("O"); //look for win
        if (move == null)
        {
            move = look_for_win_or_block("X"); //look for block
            if (move == null)
            {
                move = look_for_corner2();
                if (move == null)
                {
                    move = look_for_open_space();
                }//end if
            }//end if
        }//end if

        assert move != null;
        move.performClick();
    }

    private Button look_for_open_space() {

                if (A1.getText() == "")
                    return A1;
                if (A2.getText() == "")
                    return A2;
                if (A3.getText() == "")
                    return A3;
                if (B1.getText() == "")
                    return B1;
                if (B2.getText() == "")
                    return B2;
                if (B3.getText() == "")
                    return B3;
                if (C1.getText() == "")
                    return C1;
                if (C2.getText() == "")
                    return C2;
                if (C3.getText() == "")
                    return C3;
        return null;
    }
    private Button look_for_corner1() {
        if (A1.getText() == "O")
        {
            if (A3.getText() == "")
                return A3;
            if (C3.getText() == "")
                return C3;
            if (C1.getText() == "")
                return C1;
        }

        if (A3.getText() == "O")
        {
            if (A1.getText() == "")
                return A1;
            if (C3.getText() == "")
                return C3;
            if (C1.getText() == "")
                return C1;
        }

        if (C3.getText() == "O")
        {
            if (A1.getText() == "")
                return A3;
            if (A3.getText() == "")
                return A3;
            if (C1.getText() == "")
                return C1;
        }

        if (C1.getText() == "O")
        {
            if (A1.getText() == "")
                return A3;
            if (A3.getText() == "")
                return A3;
            if (C3.getText() == "")
                return C3;
        }
        if (A1.getText() == "")
            return A1;
        if (A3.getText() == "")
            return A3;
        if (C1.getText() == "")
            return C1;
        if (C3.getText() == "")
            return C3;

        return null;
    }
    private Button look_for_corner2() {
        if (A1.getText() == "O")
        {
            if (A3.getText() == "")
                return A3;
            if (C3.getText() == "")
                return C3;
            if (C1.getText() == "")
                return C1;
        }

        if (A3.getText() == "O")
        {
            if (A1.getText() == "")
                return A1;
            if (C3.getText() == "")
                return C3;
            if (C1.getText() == "")
                return C1;
        }

        if (C3.getText() == "O")
        {
            if (A1.getText() == "")
                return A3;
            if (A3.getText() == "")
                return A3;
            if (C1.getText() == "")
                return C1;
        }

        if (C1.getText() == "O")
        {
            if (A1.getText() == "")
                return A3;
            if (A3.getText() == "")
                return A3;
            if (C3.getText() == "")
                return C3;
        }

        if(B2.getText() == "X")
        {
            if (A1.getText() == "")
                return A3;
            if (A3.getText() == "")
                return A3;
            if (C3.getText() == "")
                return C3;
            if (C1.getText() == "")
                return C1;
        }

        if (B2.getText() == "O" && (A2.getText() == "X" || B1.getText() =="X" ||B3.getText() == "X" ||C2.getText() == "X"))
        {
            if(A1.getText() == "" && C3.getText() == "")
            {
                if (A3.getText() == "X")
                    return C3;
                if (A1.getText() == "X")
                    return C1;
            }

            if (A3.getText() == "")
                return A3;
            if (C3.getText() == "")
                return C3;
            if (C1.getText() == "")
                return C1;

        }

        if (B2.getText() == "")
            return B2;
        if (A2.getText() == "")
            return A2;
        if (B1.getText() == "")
            return B1;
        if (B3.getText() == "")
            return B3;
        if (C2.getText() == "")
            return C2;
        if (A1.getText() == "")
            return A1;
        if (A3.getText() == "")
            return A3;
        if (C1.getText() == "")
            return C1;
        if (C3.getText() == "")
            return C3;

        return null;
    }
    private Button look_for_win_or_block(String mark) {
        //HORIZONTAL TESTS
        if ((A1.getText() == mark) && (A2.getText() == mark) && (A3.getText() == ""))
            return A3;
        if ((A2.getText() == mark) && (A3.getText() == mark) && (A1.getText() == ""))
            return A1;
        if ((A1.getText() == mark) && (A3.getText() == mark) && (A2.getText() == ""))
            return A2;

        if ((B1.getText() == mark) && (B2.getText() == mark) && (B3.getText() == ""))
            return B3;
        if ((B2.getText() == mark) && (B3.getText() == mark) && (B1.getText() == ""))
            return B1;
        if ((B1.getText() == mark) && (B3.getText() == mark) && (B2.getText() == ""))
            return B2;

        if ((C1.getText() == mark) && (C2.getText() == mark) && (C3.getText() == ""))
            return C3;
        if ((C2.getText() == mark) && (C3.getText() == mark) && (C1.getText() == ""))
            return C1;
        if ((C1.getText() == mark) && (C3.getText() == mark) && (C2.getText() == ""))
            return C2;

        //VERTICAL TESTS
        if ((A1.getText() == mark) && (B1.getText() == mark) && (C1.getText() == ""))
            return C1;
        if ((B1.getText() == mark) && (C1.getText() == mark) && (A1.getText() == ""))
            return A1;
        if ((A1.getText() == mark) && (C1.getText() == mark) && (B1.getText() == ""))
            return B1;

        if ((A2.getText() == mark) && (B2.getText() == mark) && (C2.getText() == ""))
            return C2;
        if ((B2.getText() == mark) && (C2.getText() == mark) && (A2.getText() == ""))
            return A2;
        if ((A2.getText() == mark) && (C2.getText() == mark) && (B2.getText() == ""))
            return B2;

        if ((A3.getText() == mark) && (B3.getText() == mark) && (C3.getText() == ""))
            return C3;
        if ((B3.getText() == mark) && (C3.getText() == mark) && (A3.getText() == ""))
            return A3;
        if ((A3.getText() == mark) && (C3.getText() == mark) && (B3.getText() == ""))
            return B3;

        //DIAGONAL TESTS
        if ((A1.getText() == mark) && (B2.getText() == mark) && (C3.getText() == ""))
            return C3;
        if ((B2.getText() == mark) && (C3.getText() == mark) && (A1.getText() == ""))
            return A1;
        if ((A1.getText() == mark) && (C3.getText() == mark) && (B2.getText() == ""))
            return B2;

        if ((A3.getText() == mark) && (B2.getText() == mark) && (C1.getText() == ""))
            return C1;
        if ((B2.getText() == mark) && (C1.getText() == mark) && (A3.getText() == ""))
            return A3;
        if ((A3.getText() == mark) && (C1.getText() == mark) && (B2.getText() == ""))
            return B2;

        return null;
    }

    public void clk_rest(View view) {
        other_parte();
        round_run = playerX_win = playerY_win =0;
    }
}