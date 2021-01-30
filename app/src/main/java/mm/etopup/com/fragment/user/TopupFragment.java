package mm.etopup.com.fragment.user;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.activities.TopUpConfirmActivity;
import mm.etopup.com.activities.UserActivity;
import mm.etopup.com.adapters.AmountListAdapter;
import mm.etopup.com.adapters.OperatorListAdapter;
import mm.etopup.com.database.entity.UserEntity;
import mm.etopup.com.dialogs.ContactBottonSheetDialog;
import mm.etopup.com.presenter.UserPresenter;
import mm.etopup.com.session.SessionManager;
import mm.etopup.com.utils.UtilPnoValidation;
import mm.etopup.com.viewholder.AmountViewHolder;
import mm.etopup.com.viewholder.OperatorViewHolder;

public class TopupFragment extends Fragment implements AmountViewHolder.AmountSelectListener, OperatorViewHolder.OperatorSelectListener, ContactBottonSheetDialog.ContactBottomSheetSelectListener {

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


    String currentOperator = "";

    UserPresenter userPresenter;
    int mainBalance = -1;
    OperatorListAdapter adapter;
    AmountListAdapter amountListAdapter;
    ArrayList<String> operator_list = new ArrayList<String>();
    ArrayList<String> amount_list = new ArrayList<String>();
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;

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


    private void initPresenter() {
        userPresenter = ViewModelProviders.of(this).get(UserPresenter.class);
        userPresenter.initPresenter(getActivity());
    }

    public void setUpRecyclerView() {

        adapter = new OperatorListAdapter(getActivity(), this);
        amountListAdapter = new AmountListAdapter(getActivity(), this);
        operator_recyclerview.setAdapter(adapter);
        amount_recyclerview.setAdapter(amountListAdapter);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        amount_recyclerview.setLayoutManager(llm);
        RecyclerView.LayoutManager lln = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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

    private void setUpListener() {
        rechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_topup_amount.getText().toString().equalsIgnoreCase("") || ed_topup_amount.getText().toString().equalsIgnoreCase("0") ||
                        ed_topup_phone.getText().toString().equalsIgnoreCase("") || currentOperator.equalsIgnoreCase("") || UtilPnoValidation.getInstance().isValidMyanmarPhoneNumber(ed_topup_phone.getText().toString()) == false) {
                    if (UtilPnoValidation.getInstance().isValidMyanmarPhoneNumber(ed_topup_phone.getText().toString()) == false) {
                        Toast.makeText(getActivity(), "Incorrect Phone Number", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Something wrong incomplete form", Toast.LENGTH_LONG).show();
                    }

                } else {

                    if (Integer.parseInt(ed_topup_amount.getText().toString()) < mainBalance) {
                        if (mainBalance >= 0) {
                            boolean status  = false;
                            mainBalance = mainBalance - Integer.parseInt(ed_topup_amount.getText().toString());

                            if(currentOperator.equalsIgnoreCase("ooredoo"))
                            {
                                status = UtilPnoValidation.getInstance().isOoredoo(ed_topup_phone.getText().toString());
                            }else if(currentOperator.equalsIgnoreCase("mytel"))
                            {
                                status = UtilPnoValidation.getInstance().isMyTel(ed_topup_phone.getText().toString());
                            }else if( currentOperator.equalsIgnoreCase("mpt"))
                            {
                                status = UtilPnoValidation.getInstance().isMPT(ed_topup_phone.getText().toString());
                            }else if( currentOperator.equalsIgnoreCase("telenor"))
                            {
                                status = UtilPnoValidation.getInstance().isTelenor(ed_topup_phone.getText().toString());
                            }else if( currentOperator.equalsIgnoreCase("mec"))
                            {
                                status = UtilPnoValidation.getInstance().isMEC(ed_topup_phone.getText().toString());
                            }
                            if(status) {
                                TopUpConfirmActivity.open(getActivity(), mainBalance , Integer.parseInt(ed_topup_amount.getText().toString()), currentOperator, ed_topup_phone.getText().toString());
                                getActivity().finish();
                            }else{
                                Toast.makeText(getActivity(),"Invalid Mobile No ( operator and phone number is not same type)",Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Toast.makeText(getActivity(), "Your balance is empty", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Not Available topup amount is exceed than main balance", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        ed_topup_phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (ed_topup_phone.getRight() - ed_topup_phone.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        requestContactPermission();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void listenObserver() {
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

    private  void showContactBottomSheet()
    {
        ContactBottonSheetDialog bottomSheet = new ContactBottonSheetDialog(this);
        bottomSheet.show(getActivity().getSupportFragmentManager(),
                "ModalBottomSheet");
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

    public void requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        android.Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Read Contacts permission");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("Please enable access to contacts.");
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {android.Manifest.permission.READ_CONTACTS}
                                    , PERMISSIONS_REQUEST_READ_CONTACTS);
                        }
                    });
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{android.Manifest.permission.READ_CONTACTS},
                            PERMISSIONS_REQUEST_READ_CONTACTS);
                }
            } else {
                showContactBottomSheet();
            }
        } else {
            showContactBottomSheet();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showContactBottomSheet();
                } else {
                    Toast.makeText(getActivity(), "You have disabled a contacts permission", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void selectedPhoneItem(String name, String phone) {
        ed_topup_phone.setText(phone);
    }
}