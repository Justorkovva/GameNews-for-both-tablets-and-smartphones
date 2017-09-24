package justor.gamenews2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Article extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.article);
        setTitle(getIntent().getStringExtra("title"));

        ArticleFragment fragment = ArticleFragment.create(getIntent().getStringExtra("url"));

        getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit();

        TextView textView=(TextView) findViewById(R.id.textView);

    }


}