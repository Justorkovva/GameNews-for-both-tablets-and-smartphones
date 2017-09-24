package justor.gamenews2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static justor.gamenews2.MainActivity.URL;
import static justor.gamenews2.MainActivity.stat_title;

public class Recycler extends AppCompatActivity implements GryAdapter.URLLoader {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

        setTitle(stat_title);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.listFragment, new MainFragment())
                .commit();
    }

    public void load(String title, String url) {
        if (findViewById(R.id.articleFragment) != null) {
            ArticleFragment fragment = ArticleFragment.create(url);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.articleFragment, fragment)
                    .addToBackStack(null)
                    .commit();

        }
        else {
            Intent myIntent = new Intent(this, Article.class);
            myIntent.putExtra("url", url);
            myIntent.putExtra("title", title);
            startActivity(myIntent);
        }
    }

    public void refresh(View v) {
        this.finish();
        this.startActivity(getIntent());
    }
}
