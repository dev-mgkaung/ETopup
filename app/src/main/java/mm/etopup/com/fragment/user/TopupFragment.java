package mm.etopup.com.fragment.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.activities.TopUpConfirmActivity;
import mm.etopup.com.adapters.AmountListAdapter;
import mm.etopup.com.adapters.OperatorListAdapter;
import mm.etopup.com.database.entity.TransitionHistory;
import mm.etopup.com.database.entity.UserEntity;
import mm.etopup.com.presenter.UserPresenter;
import mm.etopup.com.session.SessionManager;
import mm.etopup.com.viewholder.AmountViewHolder;
import mm.etopup.com.viewholder.OperatorViewHolder;

public class TopupFragment extends Fragment  implements AmountViewHolder.AmountSelectListener , OperatorViewHolder.OperatorSelectListener {

    @BindView(R.id.operatorlist)
    RecyclerView operator_recyclerview;

    @BindView(R.id.amount_list)
    RecyclerView amount_recyclerview;

    @BindView(R.id.mainbalance)
    TextView mainbalance;

    @BindView(R.id.ed_topup_phone)
    AppCompatEditText ed_topup_phone;

    @BindView(R.id.ed_topup_amount)
    AppCompatEditText ed_topup_amount;

    @BindView(R.id.rechargeBtn)
    AppCompatButton rechargeBtn;


    String currentOperator= "";

    UserPresenter userPresenter;
    int mainBalance = -1;
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
        listenObserver();
        setUpListener();
        return root;
    }


    private void initPresenter()
    {
        userPresenter= ViewModelProviders.of(this).get(UserPresenter.class);
        userPresenter.initPresenter(getActivity());
    }

    public void setUpRecyclerView()
    {

        adapter = new OperatorListAdapter(getActivity(),this);
        amountListAdapter = new AmountListAdapter(getActivity() , this);
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
        amount_list.add("500");
        amount_list.add("1000");
        amount_list.add("3000");
        amount_list.add("5000");
        amount_list.add("10000");
        amountListAdapter.setNewData(amount_list);

    }

    private void setUpListener()
    {
        rechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed_topup_amount.getText().toString().equalsIgnoreCase("") || ed_topup_amount.getText().toString().equalsIgnoreCase("0") ||
                 ed_topup_phone.getText().toString().equalsIgnoreCase("") || currentOperator.equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Something wrong incomplete form", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (mainBalance > 0) {
                        mainBalance = mainBalance - Integer.parseInt(ed_topup_amount.getText().toString());
                        TopUpConfirmActivity.open(getActivity(), mainBalance ,currentOperator , ed_topup_phone.getText().toString());
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), "Your balance is empty", Toast.LENGTH_SHORT).show();
                           }
                     }
            }
        });
    }

    private void listenObserver()
    {
        userPresenter.getUser(SessionManager.getObjectInstance(getActivity()).getEmail().toString()).observe(getActivity(), new Observer<UserEntity>() {
            @Override
            public void onChanged(@Nullable final UserEntity user) {
                if (user != null) {
                    mainbalance.setText(user.balance + "  Ks");
                    mainBalance = user.balance;
                }
            }
        });
    }

    @Override
    public void selectedAmountItem(int previousposition, String amount) {
        amountListAdapter.setAmount(previousposition);
        ed_topup_amount.setText(amount);
    }

    @Override
    public void selectedOperatorItem(int previousposition, String operator) {
        adapter.setSelectedOperator(previousposition);
        currentOperator = operator;
    }
}