package justor.gamenews2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
            setTitle(title);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //switch (item.getItemId()) and cases  (R.id.name) normally, but here I have only one item
        this.finish();
        this.startActivity(getIntent());
        return true;
    }
}
