package justor.gamenews2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Article extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getIntent().getStringExtra("title"));

        ArticleFragment fragment = ArticleFragment.create(getIntent().getStringExtra("url"));

        getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .commit();

    }


}