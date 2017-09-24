package justor.gamenews2;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ProgressBar;
        import static justor.gamenews2.MainActivity.URL;

/**
 * Created by Lenovo on 23.09.2017.
 */

public class MainFragment extends Fragment {

    private GryTask _task = null;
    private GryAdapter _adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        _adapter = new GryAdapter((GryAdapter.URLLoader) getActivity());
        recyclerView.setAdapter(_adapter);
        _task = new GryTask(_adapter);
        _task.execute(URL);


        final ProgressBar progress = view.findViewById(R.id.progress);
        _adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                progress.setVisibility(View.GONE);
            }

        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(_task != null)
            _task.cancel(true);
    }
}

