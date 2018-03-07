package ru.vasidmi.tprk1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import ru.mail.park.articlelistlib.Article;
import ru.mail.park.articlelistlib.ArticleListFragment;
import ru.mail.park.articlelistlib.OnArticleClickListener;
import ru.vasidmi.tprk1.Fragments.ArticleFragment;

public class MainActivity extends AppCompatActivity implements OnArticleClickListener {

    private FragmentManager mFragmentManager;
    ArticleListFragment mArticleListFragment;
    LinearLayout secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArticleListFragment = new ArticleListFragment();
        secondFragment = findViewById(R.id.secondFragmentContainer);
        mArticleListFragment.setOnArticleClickListener(this);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentContainer, mArticleListFragment)
                .commit();
    }

    @Override
    public void onArticleClick(Article article) {
        ArticleFragment mArticleFragment = ArticleFragment.newInstance(article.getTitle(), article.getContent(), article.getDate().toString());
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (secondFragment != null) {
            fragmentTransaction.replace(R.id.secondFragmentContainer, mArticleFragment);
            fragmentTransaction.addToBackStack("second");
        } else {
            fragmentTransaction.replace(R.id.mainFragmentContainer, mArticleFragment);
            fragmentTransaction.addToBackStack("main");
        }
        fragmentTransaction.commit();
    }
}
