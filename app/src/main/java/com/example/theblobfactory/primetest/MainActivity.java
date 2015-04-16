package com.example.theblobfactory.primetest;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        final int randomNum = rand.nextInt((25));

        //Display the number on screen (upper right-hand corner)
        TextView numberText = (TextView)(findViewById(R.id.textView2));
        numberText.setText(Integer.toString(randomNum));

        //Create an array to hold our checkboxes, then store the boxes in the array
        final CheckBox[][] boxArray = new CheckBox[10][10];
        storeBoxes(boxArray);





        final Button compositeButton = (Button) findViewById(R.id.compositeButton);
        compositeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Perform action on click
                int x = countBoxes(boxArray);
                if (x == randomNum)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "You checked the correct number of boxes", Toast.LENGTH_SHORT);
                    toast.show();
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
                // Perform action on click
                int x = countBoxes(boxArray);
                if (x == randomNum)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "You checked the correct number of boxes", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "You did not check the correct number of boxes. Try again", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        final Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                clearBoxes(boxArray);
                //Toast toast = Toast.makeText(getApplicationContext(), "Clear Button Pressed", Toast.LENGTH_SHORT);
                //toast.show();
            }
        });
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

        for (x = 0; x < 10; x++)
        {
            for (y = 0; y < 10; y++)
            {
                if (boxArray[x][y].isChecked())
                    checkedBoxes++;
            }
        }

        return checkedBoxes;
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}

