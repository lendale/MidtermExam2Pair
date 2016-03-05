package com.gabisan.midtermexam2pair;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gabisan.midtermexam2pair.APIs.BookApi;
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

        //checks intent data passed
        Bundle intent = getIntent().getExtras();
        if (intent == null) {
            throw new RuntimeException("BookDetailsActivity is expecting an int extra passed by Intent");
        }

        //checks if a book is present
        position = intent.getInt("Position", -1);
        Log.d("POSITION", "" + position);
        if (position == -1) {
            throw new IllegalArgumentException("position passed is invalid.");
        }

//        mBook = mBookList.get(position);

//        if (mBook == null) {
//            throw new NullPointerException("No book found at the passed position.");
//        }

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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        new getBookDetail().execute();

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

    public class getBookDetail extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... params) {
            return BookApi.getBooks();
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);

            getSupportActionBar().setTitle(books.get(position).getmTitle());
            mEtBookTitle.setText(books.get(position).getmTitle());
            mEtBookGenre.setText(books.get(position).getmGenre());
            mEtBookAuthor.setText(books.get(position).getmAuthor());
            if (books.get(position).getmIsRead()) {
                mReadChkBox.setChecked(true);
            }
        }
    }
}
