package com.gabisan.midtermexam2pair.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gabisan.midtermexam2pair.APIs.BookApi;
import com.gabisan.midtermexam2pair.Adapters.BookAdapter;
import com.gabisan.midtermexam2pair.BookDetailsActivity;
import com.gabisan.midtermexam2pair.Entities.Book;
import com.gabisan.midtermexam2pair.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by junvir on 3/4/2016.
 */
public class ListViewFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private TextView mTvGettingBooks;
    private ProgressBar mProgBar;

    private List<Book> books = new ArrayList<>();
    private BookAdapter adapter;


    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FetchBooks fb= new FetchBooks();
        fb.execute();

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_listview, container, false);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState){
        // find all the views
        mListView = (ListView) view.findViewById(R.id.mainListView);
        mTvGettingBooks = (TextView) view.findViewById(R.id.tvProgressGettingBooks);
        mProgBar = (ProgressBar) view.findViewById(R.id.progressBarLoadingBooks);

        // create a new instance of adapter
        adapter = new BookAdapter(getActivity(), R.layout.list_item, books);

        // set the adapter
        mListView.setAdapter(adapter);

        // set item click listener
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra("Position", position);
        intent.putExtra("ID", (Serializable) books.get(position).getmId());
        startActivity(intent);
    }


    public class FetchBooks extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... params) {
            return BookApi.getBooks();
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);
            mTvGettingBooks.setVisibility(View.GONE);
            mProgBar.setVisibility(View.GONE);
            adapter.addAll(books);
        }
    }
}
