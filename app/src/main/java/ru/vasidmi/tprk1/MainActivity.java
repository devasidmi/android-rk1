package ru.vasidmi.tprk1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import ru.mail.park.articlelistlib.Article;
import ru.mail.park.articlelistlib.ArticleListFragment;
import ru.mail.park.articlelistlib.OnArticleClickListener;
import ru.vasidmi.tprk1.Fragments.ArticleFragment;

public class MainActivity extends AppCompatActivity implements OnArticleClickListener {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    ArticleListFragment mArticleListFragment;
    private LinearLayout secondFrag;
    private String orient = "p";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secondFrag = findViewById(R.id.secondFrag);
        secondFrag.setVisibility(View.GONE);
        mArticleListFragment = new ArticleListFragment();
        mArticleListFragment.setOnArticleClickListener(this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.mainFragmentContainer, mArticleListFragment);
        mFragmentTransaction.commit();
    }

    @Override
    public void onArticleClick(Article article) {
        ArticleFragment mArticleFragment = ArticleFragment.newInstance(article.getTitle(), article.getContent(), article.getDate().toString());
        mFragmentTransaction = mFragmentManager.beginTransaction();
        switch (orient){
            case "l":
                mFragmentTransaction.replace(R.id.secondFrag, mArticleFragment).addToBackStack("ok");
                break;
            case "p":
                mFragmentTransaction.replace(R.id.mainFragmentContainer, mArticleFragment).addToBackStack("ok");
                break;
        }

        mFragmentTransaction.commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            orient = "l";
            secondFrag.setVisibility(View.VISIBLE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            orient = "p";
            secondFrag.setVisibility(View.GONE);
        }
    }
}
