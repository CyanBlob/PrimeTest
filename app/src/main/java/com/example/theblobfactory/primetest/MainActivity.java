package com.example.theblobfactory.primetest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    boolean paused = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();

       //Create a random number
       final int randomNum = rand.nextInt((25)) + 4;


        //Display the number on screen (upper right-hand corner)
        TextView numberText = (TextView)(findViewById(R.id.textView2));
        numberText.setText(Integer.toString(randomNum));

        //Create an array to hold our checkboxes, then store the boxes in the array
        final CheckBox[][] boxArray = new CheckBox[13][10];
        storeBoxes(boxArray);

        final TextView timer = (TextView) findViewById(R.id.timer);

        //Starts the countdown
        final CountDownTimer count = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (paused) //Stop the timer if the app is taken out of focus
                    cancel();
                timer.setText("    " + millisUntilFinished / 1000);
            }

            public void onFinish() {  //If the timer reaches 0, the player loses
                timer.setText("    0");
                Toast toast = Toast.makeText(getApplicationContext(), "You ran out of time!", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                startActivity(getIntent());
            }
        }.start();

        //Check the shape of the box the user made when the user presses a button, and check if the
        //shape is allowed for the button pressed.
        final Button compositeButton = (Button) findViewById(R.id.compositeButton);
        compositeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int x = countBoxes(boxArray);
                if (x == randomNum)
                {
                    Toast toast;
                    if (isRect(boxArray) == 1)
                    {
                        toast = Toast.makeText(getApplicationContext(), "This is a composite number! Congratulations!", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                        count.cancel();
                        startActivity(getIntent());
                    }
                    else if (isRect(boxArray) == 2)
                    {
                        toast = Toast.makeText(getApplicationContext(), "This is not a composite number! You lose!", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                        count.cancel();
                        startActivity(getIntent());
                    }
                    else if (isRect(boxArray) == 0)
                    {
                        toast = Toast.makeText(getApplicationContext(), "This is not a valid shape! Try again!", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "You did not check the correct number of boxes. Try again", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        final Button primeButton = (Button) findViewById(R.id.primeButton);
        primeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int x = countBoxes(boxArray);
                if (x == randomNum)
                {
                    Toast toast;

                    if (isRect(boxArray) == 2)
                    {
                        toast = Toast.makeText(getApplicationContext(), "This is a prime number! Good job!", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                        count.cancel();
                        startActivity(getIntent());

                    }
                    else if (isRect(boxArray) == 1)
                    {
                        toast = Toast.makeText(getApplicationContext(), "This is not a prime number! You lose!", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                        count.cancel();
                        startActivity(getIntent());
                    }
                    else if (isRect(boxArray) == 0)
                    {
                        toast = Toast.makeText(getApplicationContext(), "This is not a valid shape! Try again!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "You did not check the correct number of boxes. Try again", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        final Button retryButton = (Button) findViewById(R.id.retryButton);
        retryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                count.cancel();
                startActivity(getIntent());

            }
        });

        final Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearBoxes(boxArray);

            }
        });
    }

    //Returns the "shape" of the object
    //0 = not a valid shape
    //1 = rectangle
    //2 = "prime" rectangle
    public int isRect(CheckBox[][] boxArray) {
        int x;
        int y;
        int leaf = 0; //A leaf is a checked checkbox adacent to 1 checked checkbox
        int bite = 0; //A bite is an empty checkbox adjacent to 2 checked checkboxes
        boolean foundOne = false;
        boolean blankAfter = false;

        for (x = 0; x < 13; x++) {
            for (y = 0; y < 10; y++) {
                if ((!boxArray[x][y].isChecked() && findNumAdjacent(x, y, boxArray) == 2))
                {
                    bite++;
                }
                if ((boxArray[x][y].isChecked() && findNumAdjacent(x, y, boxArray) == 1)) {
                    leaf++;
                    if((x != 0 && findNumAdjacent(x-1,y,boxArray) == 1 &&  boxArray[x-1][y].isChecked()) || (x != 12 && findNumAdjacent(x+1,y,boxArray) == 1  && boxArray[x+1][y].isChecked()) || (y != 0 && findNumAdjacent(x,y-1,boxArray) == 1  && boxArray[x][y-1].isChecked()) || (y != 9 &&findNumAdjacent(x,y+1,boxArray) == 1 && boxArray[x][y+1].isChecked()))
                        leaf--;
                }

                    if (boxArray[x][y].isChecked()) {
                        foundOne = true;
                    }
                    if (!boxArray[x][y].isChecked() && foundOne) {
                        blankAfter = true;
                    }
                    if (boxArray[x][y].isChecked() && foundOne && blankAfter) {

                        return 0;
                    }
                }
                foundOne = false;
                blankAfter = false;
            }


        for (y = 0; y < 10; y++) {
            for (x = 0; x < 13; x++) {

                if (boxArray[x][y].isChecked()) {
                    foundOne = true;
                }
                if (!boxArray[x][y].isChecked() && foundOne) {
                    blankAfter = true;
                }
                if (boxArray[x][y].isChecked() && foundOne && blankAfter) {

                    return 0;
                }
            }
        foundOne = false;
        blankAfter = false;
        }

            if (leaf == 0 && bite == 0)
                return 1;
            if (leaf == 1 && bite <= 1)
                return 2;
            else
                return 0;
    }


    //Returns the number of checked boxes that a box is adjacent to
    public int findNumAdjacent(int x, int y, CheckBox[][] boxArray)
    {
        int adjacent = 0;

        if (y < 9 && boxArray[x][y + 1].isChecked())
            adjacent++;
        if (y > 0 && boxArray[x][y - 1].isChecked())
            adjacent++;
        if (x < 12 &&boxArray[x + 1][y].isChecked())
            adjacent++;
        if (x > 0 && boxArray[x - 1][y].isChecked())
            adjacent++;

        return  adjacent;
    }


    //Stores the actual checkboxes in the 2d array of checkboxes for easy access
    public void storeBoxes(CheckBox[][] boxArray)
    {
        boxArray[0][0] = (CheckBox)(findViewById(R.id.checkBox0));
        boxArray[0][1] = (CheckBox)(findViewById(R.id.checkBox1));
        boxArray[0][2] = (CheckBox)(findViewById(R.id.checkBox2));
        boxArray[0][3] = (CheckBox)(findViewById(R.id.checkBox3));
        boxArray[0][4] = (CheckBox)(findViewById(R.id.checkBox4));
        boxArray[0][5] = (CheckBox)(findViewById(R.id.checkBox5));
        boxArray[0][6] = (CheckBox)(findViewById(R.id.checkBox6));
        boxArray[0][7] = (CheckBox)(findViewById(R.id.checkBox7));
        boxArray[0][8] = (CheckBox)(findViewById(R.id.checkBox8));
        boxArray[0][9] = (CheckBox)(findViewById(R.id.checkBox9));

        boxArray[1][0] = (CheckBox)(findViewById(R.id.checkBox10));
        boxArray[1][1] = (CheckBox)(findViewById(R.id.checkBox11));
        boxArray[1][2] = (CheckBox)(findViewById(R.id.checkBox12));
        boxArray[1][3] = (CheckBox)(findViewById(R.id.checkBox13));
        boxArray[1][4] = (CheckBox)(findViewById(R.id.checkBox14));
        boxArray[1][5] = (CheckBox)(findViewById(R.id.checkBox15));
        boxArray[1][6] = (CheckBox)(findViewById(R.id.checkBox16));
        boxArray[1][7] = (CheckBox)(findViewById(R.id.checkBox17));
        boxArray[1][8] = (CheckBox)(findViewById(R.id.checkBox18));
        boxArray[1][9] = (CheckBox)(findViewById(R.id.checkBox19));

        boxArray[2][0] = (CheckBox)(findViewById(R.id.checkBox20));
        boxArray[2][1] = (CheckBox)(findViewById(R.id.checkBox21));
        boxArray[2][2] = (CheckBox)(findViewById(R.id.checkBox22));
        boxArray[2][3] = (CheckBox)(findViewById(R.id.checkBox23));
        boxArray[2][4] = (CheckBox)(findViewById(R.id.checkBox24));
        boxArray[2][5] = (CheckBox)(findViewById(R.id.checkBox25));
        boxArray[2][6] = (CheckBox)(findViewById(R.id.checkBox26));
        boxArray[2][7] = (CheckBox)(findViewById(R.id.checkBox27));
        boxArray[2][8] = (CheckBox)(findViewById(R.id.checkBox28));
        boxArray[2][9] = (CheckBox)(findViewById(R.id.checkBox29));

        boxArray[3][0] = (CheckBox)(findViewById(R.id.checkBox30));
        boxArray[3][1] = (CheckBox)(findViewById(R.id.checkBox31));
        boxArray[3][2] = (CheckBox)(findViewById(R.id.checkBox32));
        boxArray[3][3] = (CheckBox)(findViewById(R.id.checkBox33));
        boxArray[3][4] = (CheckBox)(findViewById(R.id.checkBox34));
        boxArray[3][5] = (CheckBox)(findViewById(R.id.checkBox35));
        boxArray[3][6] = (CheckBox)(findViewById(R.id.checkBox36));
        boxArray[3][7] = (CheckBox)(findViewById(R.id.checkBox37));
        boxArray[3][8] = (CheckBox)(findViewById(R.id.checkBox38));
        boxArray[3][9] = (CheckBox)(findViewById(R.id.checkBox39));

        boxArray[4][0] = (CheckBox)(findViewById(R.id.checkBox40));
        boxArray[4][1] = (CheckBox)(findViewById(R.id.checkBox41));
        boxArray[4][2] = (CheckBox)(findViewById(R.id.checkBox42));
        boxArray[4][3] = (CheckBox)(findViewById(R.id.checkBox43));
        boxArray[4][4] = (CheckBox)(findViewById(R.id.checkBox44));
        boxArray[4][5] = (CheckBox)(findViewById(R.id.checkBox45));
        boxArray[4][6] = (CheckBox)(findViewById(R.id.checkBox46));
        boxArray[4][7] = (CheckBox)(findViewById(R.id.checkBox47));
        boxArray[4][8] = (CheckBox)(findViewById(R.id.checkBox48));
        boxArray[4][9] = (CheckBox)(findViewById(R.id.checkBox49));

        boxArray[5][0] = (CheckBox)(findViewById(R.id.checkBox50));
        boxArray[5][1] = (CheckBox)(findViewById(R.id.checkBox51));
        boxArray[5][2] = (CheckBox)(findViewById(R.id.checkBox52));
        boxArray[5][3] = (CheckBox)(findViewById(R.id.checkBox53));
        boxArray[5][4] = (CheckBox)(findViewById(R.id.checkBox54));
        boxArray[5][5] = (CheckBox)(findViewById(R.id.checkBox55));
        boxArray[5][6] = (CheckBox)(findViewById(R.id.checkBox56));
        boxArray[5][7] = (CheckBox)(findViewById(R.id.checkBox57));
        boxArray[5][8] = (CheckBox)(findViewById(R.id.checkBox58));
        boxArray[5][9] = (CheckBox)(findViewById(R.id.checkBox59));

        boxArray[6][0] = (CheckBox)(findViewById(R.id.checkBox60));
        boxArray[6][1] = (CheckBox)(findViewById(R.id.checkBox61));
        boxArray[6][2] = (CheckBox)(findViewById(R.id.checkBox62));
        boxArray[6][3] = (CheckBox)(findViewById(R.id.checkBox63));
        boxArray[6][4] = (CheckBox)(findViewById(R.id.checkBox64));
        boxArray[6][5] = (CheckBox)(findViewById(R.id.checkBox65));
        boxArray[6][6] = (CheckBox)(findViewById(R.id.checkBox66));
        boxArray[6][7] = (CheckBox)(findViewById(R.id.checkBox67));
        boxArray[6][8] = (CheckBox)(findViewById(R.id.checkBox68));
        boxArray[6][9] = (CheckBox)(findViewById(R.id.checkBox69));

        boxArray[7][0] = (CheckBox)(findViewById(R.id.checkBox70));
        boxArray[7][1] = (CheckBox)(findViewById(R.id.checkBox71));
        boxArray[7][2] = (CheckBox)(findViewById(R.id.checkBox72));
        boxArray[7][3] = (CheckBox)(findViewById(R.id.checkBox73));
        boxArray[7][4] = (CheckBox)(findViewById(R.id.checkBox74));
        boxArray[7][5] = (CheckBox)(findViewById(R.id.checkBox75));
        boxArray[7][6] = (CheckBox)(findViewById(R.id.checkBox76));
        boxArray[7][7] = (CheckBox)(findViewById(R.id.checkBox77));
        boxArray[7][8] = (CheckBox)(findViewById(R.id.checkBox78));
        boxArray[7][9] = (CheckBox)(findViewById(R.id.checkBox79));

        boxArray[8][0] = (CheckBox)(findViewById(R.id.checkBox80));
        boxArray[8][1] = (CheckBox)(findViewById(R.id.checkBox81));
        boxArray[8][2] = (CheckBox)(findViewById(R.id.checkBox82));
        boxArray[8][3] = (CheckBox)(findViewById(R.id.checkBox83));
        boxArray[8][4] = (CheckBox)(findViewById(R.id.checkBox84));
        boxArray[8][5] = (CheckBox)(findViewById(R.id.checkBox85));
        boxArray[8][6] = (CheckBox)(findViewById(R.id.checkBox86));
        boxArray[8][7] = (CheckBox)(findViewById(R.id.checkBox87));
        boxArray[8][8] = (CheckBox)(findViewById(R.id.checkBox88));
        boxArray[8][9] = (CheckBox)(findViewById(R.id.checkBox89));

        boxArray[9][0] = (CheckBox)(findViewById(R.id.checkBox90));
        boxArray[9][1] = (CheckBox)(findViewById(R.id.checkBox91));
        boxArray[9][2] = (CheckBox)(findViewById(R.id.checkBox92));
        boxArray[9][3] = (CheckBox)(findViewById(R.id.checkBox93));
        boxArray[9][4] = (CheckBox)(findViewById(R.id.checkBox94));
        boxArray[9][5] = (CheckBox)(findViewById(R.id.checkBox95));
        boxArray[9][6] = (CheckBox)(findViewById(R.id.checkBox96));
        boxArray[9][7] = (CheckBox)(findViewById(R.id.checkBox97));
        boxArray[9][8] = (CheckBox)(findViewById(R.id.checkBox98));
        boxArray[9][9] = (CheckBox)(findViewById(R.id.checkBox99));

        boxArray[10][0] = (CheckBox)(findViewById(R.id.checkBox100));
        boxArray[10][1] = (CheckBox)(findViewById(R.id.checkBox101));
        boxArray[10][2] = (CheckBox)(findViewById(R.id.checkBox102));
        boxArray[10][3] = (CheckBox)(findViewById(R.id.checkBox103));
        boxArray[10][4] = (CheckBox)(findViewById(R.id.checkBox104));
        boxArray[10][5] = (CheckBox)(findViewById(R.id.checkBox105));
        boxArray[10][6] = (CheckBox)(findViewById(R.id.checkBox106));
        boxArray[10][7] = (CheckBox)(findViewById(R.id.checkBox107));
        boxArray[10][8] = (CheckBox)(findViewById(R.id.checkBox108));
        boxArray[10][9] = (CheckBox)(findViewById(R.id.checkBox109));

        boxArray[11][0] = (CheckBox)(findViewById(R.id.checkBox110));
        boxArray[11][1] = (CheckBox)(findViewById(R.id.checkBox111));
        boxArray[11][2] = (CheckBox)(findViewById(R.id.checkBox112));
        boxArray[11][3] = (CheckBox)(findViewById(R.id.checkBox113));
        boxArray[11][4] = (CheckBox)(findViewById(R.id.checkBox114));
        boxArray[11][5] = (CheckBox)(findViewById(R.id.checkBox115));
        boxArray[11][6] = (CheckBox)(findViewById(R.id.checkBox116));
        boxArray[11][7] = (CheckBox)(findViewById(R.id.checkBox117));
        boxArray[11][8] = (CheckBox)(findViewById(R.id.checkBox118));
        boxArray[11][9] = (CheckBox)(findViewById(R.id.checkBox119));

        boxArray[12][0] = (CheckBox)(findViewById(R.id.checkBox120));
        boxArray[12][1] = (CheckBox)(findViewById(R.id.checkBox121));
        boxArray[12][2] = (CheckBox)(findViewById(R.id.checkBox122));
        boxArray[12][3] = (CheckBox)(findViewById(R.id.checkBox123));
        boxArray[12][4] = (CheckBox)(findViewById(R.id.checkBox124));
        boxArray[12][5] = (CheckBox)(findViewById(R.id.checkBox125));
        boxArray[12][6] = (CheckBox)(findViewById(R.id.checkBox126));
        boxArray[12][7] = (CheckBox)(findViewById(R.id.checkBox127));
        boxArray[12][8] = (CheckBox)(findViewById(R.id.checkBox128));
        boxArray[12][9] = (CheckBox)(findViewById(R.id.checkBox129));


    }

    //Unchecks all the checkboxes
    public void clearBoxes(CheckBox[][] boxArray)
    {
        int x;
        int y;

        for (x = 0; x < 10; x++)
        {
            for (y = 0; y < 10; y++)
                boxArray[x][y].setChecked(false);
        }
    }

    //Counts how many checkboxes have been checked
    public int countBoxes(CheckBox[][] boxArray)
    {
        int x;
        int y;
        int checkedBoxes = 0;

        for (x = 0; x < 13; x++)
        {
            for (y = 0; y < 10; y++)
            {
                if (boxArray[x][y].isChecked())
                    checkedBoxes++;
            }
        }

        return checkedBoxes;
    }

    protected void onPause()
    {
        super.onPause();
        finish();
        paused = true;
    }

    protected void onStop()
    {
        super.onStop();
        finish();
        paused = true;
    }
}

