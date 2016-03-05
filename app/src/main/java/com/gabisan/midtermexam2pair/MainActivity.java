package com.gabisan.midtermexam2pair;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.gabisan.midtermexam2pair.Fragment.ListViewFragment;

public class MainActivity extends AppCompatActivity {


    private ListViewFragment mListViewFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListViewFragment = ListViewFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, mListViewFragment)
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
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
        if (id == R.id.action_refresh) {
            mListViewFragment = ListViewFragment.newInstance();


            getSupportFragmentManager()
                    .beginTransaction()
                    .detach(mListViewFragment)
                    .attach(mListViewFragment)
                    .commit();
        }

        if(id == R.id.action_search){


            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final Spinner spinner = (Spinner) findViewById(R.id.spnChoice);
            final View dialogView = inflater.inflate(R.layout.alert, null);
            dialogBuilder.setTitle(R.string.search)
                    .setItems(R.array.option, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String userChoice = spinner.getSelectedItem().toString();

                            if (userChoice == "Genre") {
                                //code
                            }
                            if (userChoice == "Author") {
                                //code
                            }
                        }
                    });

            dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //do something with edt.getText().toString();
                    final EditText edt = (EditText) findViewById(R.id.edtChoice);
                    edt.getText().toString();
                }
            });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //pass
                }
            });
            AlertDialog b = dialogBuilder.create();
            b.show();


        }



        return super.onOptionsItemSelected(item);
    }
}
