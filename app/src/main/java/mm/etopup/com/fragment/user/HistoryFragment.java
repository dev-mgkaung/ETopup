package mm.etopup.com.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.adapters.HistoryAdapter;
import mm.etopup.com.database.entity.TransitionHistory;
import mm.etopup.com.presenter.UserPresenter;
import mm.etopup.com.session.SessionManager;

public class HistoryFragment extends Fragment {

    @BindView(R.id.historylist)
    RecyclerView historylist;

    UserPresenter userPresenter;
    HistoryAdapter adapter;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, root);
        initPresenter();
        setUpRecyclerView();
        listenOberser();
        return root;
    }

    private void initPresenter()
    {
        userPresenter= ViewModelProviders.of(this).get(UserPresenter.class);
        userPresenter.initPresenter(getActivity());
    }

    public void setUpRecyclerView()
    {

        adapter = new HistoryAdapter(getActivity());
        historylist.setAdapter(adapter);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        historylist.setLayoutManager(llm);
    }

    private void listenOberser()
    {

        userPresenter.getHistoryByEmail(SessionManager.getObjectInstance(getActivity()).getEmail().toString()).observe(getActivity(), new Observer<List<TransitionHistory>>() {
            @Override
            public void onChanged(@Nullable final List<TransitionHistory> transitionHistoryList) {
                if (transitionHistoryList != null) {
                    adapter.setNewData(transitionHistoryList);
                    historylist.setScrollContainer(true);
                    historylist.computeVerticalScrollExtent();
                }
            }
        });
    }
}