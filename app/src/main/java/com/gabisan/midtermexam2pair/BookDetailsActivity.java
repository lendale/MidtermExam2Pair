package com.gabisan.midtermexam2pair;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gabisan.midtermexam2pair.Entities.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daleg on 05/03/2016.
 */
public class BookDetailsActivity extends AppCompatActivity {

    private EditText mEtBookTitle;
    private EditText mEtBookGenre;
    private EditText mEtBookAuthor;
    private CheckBox mReadChkBox;

    private List<Book> mBookList = new ArrayList<>();
    private Book mBook;
    private String mId;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        //find all views
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEtBookTitle = (EditText) findViewById(R.id.etBookTitle);
        mEtBookGenre = (EditText) findViewById(R.id.etBookGenre);
        mEtBookAuthor = (EditText) findViewById(R.id.etBookAuthor);
        mReadChkBox = (CheckBox) findViewById(R.id.ChkBoxRead);

        mEtBookTitle.setEnabled(false);
        mEtBookGenre.setEnabled(false);
        mEtBookAuthor.setEnabled(false);
        mReadChkBox.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
