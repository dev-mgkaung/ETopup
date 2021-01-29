package mm.etopup.com.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.adapters.AmountListAdapter;
import mm.etopup.com.adapters.OperatorListAdapter;
import mm.etopup.com.presenter.UserPresenter;

public class TopupFragment extends Fragment {

    @BindView(R.id.operatorlist)
    RecyclerView operator_recyclerview;

    @BindView(R.id.amount_list)
    RecyclerView amount_recyclerview;

    UserPresenter userPresenter;
    OperatorListAdapter adapter;
    AmountListAdapter amountListAdapter;
    ArrayList<String> operator_list = new ArrayList<String>();
    ArrayList<String> amount_list = new ArrayList<String>();
    public static TopupFragment newInstance() {
        TopupFragment fragment = new TopupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_topup, container, false);
        ButterKnife.bind(this, root);
        initPresenter();
        setUpRecyclerView();

        return root;
    }


    private void initPresenter()
    {
        userPresenter= ViewModelProviders.of(this).get(UserPresenter.class);
        userPresenter.initPresenter(getActivity());
    }

    public void setUpRecyclerView()
    {

        adapter = new OperatorListAdapter(getActivity());
        amountListAdapter = new AmountListAdapter(getActivity());
        operator_recyclerview.setAdapter(adapter);
        amount_recyclerview.setAdapter(amountListAdapter);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        amount_recyclerview.setLayoutManager(llm);
        RecyclerView.LayoutManager lln= new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        operator_recyclerview.setLayoutManager(lln);

        operator_list.clear();
        operator_list.add("ooredoo");
        operator_list.add("mpt");
        operator_list.add("telenor");
        operator_list.add("mytel");
        operator_list.add("mec");
        adapter.setNewData(operator_list);

        amount_list.clear();
        amount_list.add("500 Ks");
        amount_list.add("1000 Ks");
        amount_list.add("3000 Ks");
        amount_list.add("5000 Ks");
        amount_list.add("10000 Ks");
        amountListAdapter.setNewData(amount_list);

//        userPresenter.getAllUser().observe(getActivity(), new Observer<List<UserEntity>>() {
//            @Override
//            public void onChanged(@Nullable final List<UserEntity> userlist) {
//                if (userlist != null) {
//                    adapter.setNewData(userlist);
//                    userList.setScrollContainer(true);
//                    userList.computeVerticalScrollExtent();
//                }
//            }
//        });

    }
}