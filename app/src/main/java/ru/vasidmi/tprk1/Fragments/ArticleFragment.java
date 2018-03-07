package ru.vasidmi.tprk1.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vasidmi.tprk1.R;

/**
 * Created by vasidmi on 07/03/2018.
 */

public class ArticleFragment extends Fragment {

    private AppCompatTextView title, content, date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.article_fragment, container, false);
        title = v.findViewById(R.id.title);
        content = v.findViewById(R.id.contentText);
        date = v.findViewById(R.id.date);
        setupUI(getArguments());
        return v;
    }

    public void setupUI(Bundle bundle) {
        title.setText(bundle.get("title").toString());
        content.setText(bundle.get("content").toString());
        date.setText(bundle.get("date").toString());
    }

    public static ArticleFragment newInstance(String title, String content, String date) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", content);
        args.putString("date", date);
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }
}