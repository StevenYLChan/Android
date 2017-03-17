package theloaferlounge.hithym;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daasuu.ahp.AnimateHorizontalProgressBar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {

    boolean isPaused = false;
    long timeRemaining = 0;
    int progressRemaining=0;

    int counter = 0;
    ArrayList<Integer> sequence_list2 = new ArrayList<>();
    int[] tracker2;
    ArrayList<Integer> input_list = new ArrayList<>();
    String stars="";
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        SharedPreferences prefs = this.getSharedPreferences("STB_PREFS",Context.MODE_PRIVATE);
        int score = prefs.getInt("STB_HS", 0);
        TextView hsview = (TextView) findViewById(R.id.highscore);
        hsview.setText("" + score);


        game_initiate(counter);
        timer_initiate();

    }

    public void one_button(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        int value = 0;
        Log.d("BUTTON PRESS", "Value: " + value);
        input_list.add(value);
        TextView starview = (TextView) findViewById(R.id.inputstar);
        stars+="*";
        starview.setText(stars);
    }

    public void two_button(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        int value = 1;
        Log.d("BUTTON PRESS", "Value: " + value);
        input_list.add(value);
        TextView starview = (TextView) findViewById(R.id.inputstar);
        stars+="*";
        starview.setText(stars);
    }

    public void three_button(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        int value = 2;
        Log.d("BUTTON PRESS", "Value: " + value);
        input_list.add(value);
        TextView starview = (TextView) findViewById(R.id.inputstar);
        stars+="*";
        starview.setText(stars);
    }

    public void four_button(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        int value = 3;
        Log.d("BUTTON PRESS", "Value: " + value);
        input_list.add(value);
        TextView starview = (TextView) findViewById(R.id.inputstar);
        stars+="*";
        starview.setText(stars);
    }

    public void submit_button(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        int value = 1;
        Log.d("SUBMIT PRESS", "");
        Log.d("input_list",""+input_list);
        Log.d("sequence_list2", "" + sequence_list2);
        if (input_list.equals(sequence_list2)){
            TableRow tblayout = (TableRow) findViewById(R.id.tbRow);
            tblayout.removeAllViews();
            counter+=1;
            TextView starview = (TextView) findViewById(R.id.inputstar);
            starview.setText("CORRECT");
            //starview.setTextColor(0x1DE9B6);
            stars="";
            //starview.setTextColor(0x000000);;
            TextView counterview = (TextView) findViewById(R.id.counter);
            counterview.setText(""+counter);
            input_list.clear();
            game_initiate(counter);
            timer_initiate();
        }else{
            TextView starview = (TextView) findViewById(R.id.inputstar);
            stars="";
            starview.setText("INCORRECT");
            input_list.clear();
         }
    }

    public void game_initiate(int counter) {
        ImageButton button_1 = (ImageButton) findViewById(R.id.button1);
        ImageButton button_2 = (ImageButton) findViewById(R.id.button2);
        ImageButton button_3 = (ImageButton) findViewById(R.id.button3);
        ImageButton button_4 = (ImageButton) findViewById(R.id.button4);
        ImageButton button_submit = (ImageButton) findViewById(R.id.submit_button);
        ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
        pause_button.setEnabled(true);
        button_1.setEnabled(true);
        button_2.setEnabled(true);
        button_3.setEnabled(true);
        button_4.setEnabled(true);
        button_submit.setEnabled(true);
        int creation_number;
        if (counter < 5) {
            creation_number = 3;
            create_views(creation_number);
        } else if (counter >= 5 && counter <10) {
            creation_number = 5;
            create_views(creation_number);
        } else if (counter >= 10 && counter < 15) {
            creation_number = 7;
            create_views(creation_number);
        } else if (counter >= 15 && counter < 20) {
            creation_number = 9;
            create_views(creation_number);
        } else if (counter >=20 && counter<30){
            creation_number = 11;
            create_views(creation_number);
        } else if (counter >=30){
            creation_number = 13;
            create_views(creation_number);
        }

    }

    public void create_views(int creation_number) {

        int[] tracker = new int[creation_number];
        ArrayList<Integer> sequence_list = new ArrayList<>();
        Random r = new Random();
        for(int i =0;i<creation_number;i++){
            sequence_list.add(r.nextInt(4));
        }
        Log.d("sequence_list",""+sequence_list);
        tracker2=tracker;
        sequence_list2=sequence_list;
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        params.width = 100;
        params.height = 200;
        params.weight = 1;
        params.gravity= Gravity.CENTER_VERTICAL;
        TableRow tblayout = (TableRow) findViewById(R.id.tbRow);
        ImageView[] imgView = new ImageView[creation_number];
        int i = 0;
        for(int item:sequence_list){

            if(item==0){
                imgView[i] = new ImageView(this);
                imgView[i].setTag(i);
                imgView[i].setId(i);
                imgView[i].setImageResource(R.drawable.red_display_not_hit);
                imgView[i].setLayoutParams(params);
                tblayout.addView(imgView[i]);
                i+=1;
            }else if(item==1){
                imgView[i] = new ImageView(this);
                imgView[i].setTag(i);
                imgView[i].setId(i);
                imgView[i].setImageResource(R.drawable.green_display_not_hit);
                imgView[i].setLayoutParams(params);
                tblayout.addView(imgView[i]);
                i+=1;
            }else if (item==2){
                imgView[i] = new ImageView(this);
                imgView[i].setTag(i);
                imgView[i].setId(i);
                imgView[i].setImageResource(R.drawable.blue_display_not_hit);
                imgView[i].setLayoutParams(params);
                tblayout.addView(imgView[i]);
                i+=1;
            }else if(item==3){
                imgView[i] = new ImageView(this);
                imgView[i].setTag(i);
                imgView[i].setId(i);
                imgView[i].setImageResource(R.drawable.yellow_display_not_hit);
                imgView[i].setLayoutParams(params);
                tblayout.addView(imgView[i]);
                i+=1;
            }
        }

    }
    public static List<Integer> get_sequence_list(List<Integer> sequence_list){
        return sequence_list;
    }
    public void timer_initiate(){
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        AnimateHorizontalProgressBar progressBar = (AnimateHorizontalProgressBar) findViewById(R.id.animate_progress_bar);
        progressBar.setMax(500);
        progressBar.setProgress(500);

        countDownTimer = new CountDownTimer(6000,1000) {
            AnimateHorizontalProgressBar progressBar = (AnimateHorizontalProgressBar) findViewById(R.id.animate_progress_bar);
            int progress_decrement=500;
            @Override
            public void onTick(long millisUntilFinished) {
                //Log.i("millis", "" + millisUntilFinished);
                if(isPaused){
                    cancel();
                }else{
                    Log.i("Countdown Timer: ","seconds remaining: " + millisUntilFinished / 1000);
                    progress_decrement-=100;
                    progressBar.setProgressWithAnim(progress_decrement);
                    timeRemaining=millisUntilFinished;
                    progressRemaining=progress_decrement;
                }


            }
            @Override
            public void onFinish() {
                ImageButton button_1 = (ImageButton) findViewById(R.id.button1);
                ImageButton button_2 = (ImageButton) findViewById(R.id.button2);
                ImageButton button_3 = (ImageButton) findViewById(R.id.button3);
                ImageButton button_4 = (ImageButton) findViewById(R.id.button4);
                ImageButton button_submit = (ImageButton) findViewById(R.id.submit_button);
                ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
                pause_button.setEnabled(false);
                button_1.setEnabled(false);
                button_2.setEnabled(false);
                button_3.setEnabled(false);
                button_4.setEnabled(false);
                button_submit.setEnabled(false);
                SharedPreferences prefs = getSharedPreferences("STB_PREFS", Context.MODE_PRIVATE);
                int score = prefs.getInt("STB_HS", 0);
                if (counter>score) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("STB_HS", counter);
                    editor.commit();
                    SharedPreferences prefs2 = getSharedPreferences("STB_PREFS", Context.MODE_PRIVATE);
                    int score2 = prefs2.getInt("STB_HS", 0);
                    TextView hsview = (TextView) findViewById(R.id.highscore);
                    hsview.setText("" + score2);
                }
                popup();
            }
        };

        countDownTimer.start();
    }

    public void popup(){
        final LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup,null,false);
        final PopupWindow pw = new PopupWindow(popupView,800,1100,false);
        //PopupWindow pw = new PopupWindow(
          //      inflater.inflate(R.layout.popup, null, true),
            //    500,
              //  500,
                //true);
        // The code below assumes that the root container has an id called 'main'
        pw.setOutsideTouchable(false);
        pw.showAtLocation(this.findViewById(R.id.main_layout), Gravity.CENTER, 0, 0);
        ImageButton retry = (ImageButton) popupView.findViewById(R.id.retry_button);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                pw.dismiss();
                counter = 0;
                TableRow tblayout = (TableRow) findViewById(R.id.tbRow);
                tblayout.removeAllViews();
                TextView counterview = (TextView) findViewById(R.id.counter);
                counterview.setText("" + counter);
                input_list.clear();
                TextView starview = (TextView) findViewById(R.id.inputstar);
                starview.setText("");
                game_initiate(counter);
                timer_initiate();
            }
        });
    }
    public void home_pressed(View v){
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        finish();
        Intent intent = new Intent(this,MainMenu.class );
        startActivity(intent);
    }

    public void pause_pressed(View v){
        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Log.d("PAUSE BUTTON","pause pressed");
        isPaused=true;
        popup_pause();

    }
    public void popup_pause(){
        ImageButton button_1 = (ImageButton) findViewById(R.id.button1);
        ImageButton button_2 = (ImageButton) findViewById(R.id.button2);
        ImageButton button_3 = (ImageButton) findViewById(R.id.button3);
        ImageButton button_4 = (ImageButton) findViewById(R.id.button4);
        ImageButton button_submit = (ImageButton) findViewById(R.id.submit_button);
        ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
        pause_button.setEnabled(false);
        button_1.setEnabled(false);
        button_2.setEnabled(false);
        button_3.setEnabled(false);
        button_4.setEnabled(false);
        button_submit.setEnabled(false);
        final LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pause_popup,null,false);
        final PopupWindow pw2 = new PopupWindow(popupView,800,1100,false);
        //PopupWindow pw = new PopupWindow(
        //      inflater.inflate(R.layout.popup, null, true),
        //    500,
        //  500,
        //true);
        // The code below assumes that the root container has an id called 'main'
        pw2.setOutsideTouchable(false);
        pw2.showAtLocation(this.findViewById(R.id.main_layout), Gravity.CENTER, 0, 0);
        ImageButton pause_id = (ImageButton) popupView.findViewById(R.id.resume_popup_button);
        pause_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPaused=false;
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                pw2.dismiss();
                ImageButton button_1 = (ImageButton) findViewById(R.id.button1);
                ImageButton button_2 = (ImageButton) findViewById(R.id.button2);
                ImageButton button_3 = (ImageButton) findViewById(R.id.button3);
                ImageButton button_4 = (ImageButton) findViewById(R.id.button4);
                ImageButton button_submit = (ImageButton) findViewById(R.id.submit_button);
                ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
                pause_button.setEnabled(true);
                button_1.setEnabled(true);
                button_2.setEnabled(true);
                button_3.setEnabled(true);
                button_4.setEnabled(true);
                button_submit.setEnabled(true);
                long millisInFuture = timeRemaining;
                countDownTimer = new CountDownTimer(millisInFuture,1000) {
                    AnimateHorizontalProgressBar progressBar = (AnimateHorizontalProgressBar) findViewById(R.id.animate_progress_bar);
                    int progress_decrement=progressRemaining;
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //Log.i("millis", "" + millisUntilFinished);
                        if(isPaused){
                            cancel();
                        }else{
                            Log.i("Countdown Timer: ","seconds remaining: " + millisUntilFinished / 1000);
                            progress_decrement-=100;
                            progressBar.setProgressWithAnim(progress_decrement);
                            timeRemaining=millisUntilFinished;
                            progressRemaining=progress_decrement;
                        }
                    }
                    @Override
                    public void onFinish() {
                        ImageButton button_1 = (ImageButton) findViewById(R.id.button1);
                        ImageButton button_2 = (ImageButton) findViewById(R.id.button2);
                        ImageButton button_3 = (ImageButton) findViewById(R.id.button3);
                        ImageButton button_4 = (ImageButton) findViewById(R.id.button4);
                        ImageButton button_submit = (ImageButton) findViewById(R.id.submit_button);
                        ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
                        pause_button.setEnabled(false);
                        button_1.setEnabled(false);
                        button_2.setEnabled(false);
                        button_3.setEnabled(false);
                        button_4.setEnabled(false);
                        button_submit.setEnabled(false);
                        SharedPreferences prefs = getSharedPreferences("STB_PREFS", Context.MODE_PRIVATE);
                        int score = prefs.getInt("STB_HS", 0);
                        if (counter>score) {
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("STB_HS", counter);
                            editor.commit();
                            SharedPreferences prefs2 = getSharedPreferences("STB_PREFS", Context.MODE_PRIVATE);
                            int score2 = prefs2.getInt("STB_HS", 0);
                            TextView hsview = (TextView) findViewById(R.id.highscore);
                            hsview.setText("" + score2);
                        }
                        popup();
                    }
                };

                countDownTimer.start();
            }

        });
    }
}
