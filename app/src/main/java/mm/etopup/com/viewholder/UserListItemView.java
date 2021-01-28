package mm.etopup.com.viewholder;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.database.entity.UserEntity;

public class UserListItemView extends LinearLayout {

    @BindView(R.id.ul_name)
    TextView ul_name;
    @BindView(R.id.ul_phone)
    TextView ul_phone;
    @BindView(R.id.ul_password)
    TextView ul_password;
    @BindView(R.id.ul_amount)
    TextView ul_amount;

    Context context;
    UserEntity data;
    ArrayList<UserEntity> datalist;
    int position;

    public UserListItemView(Context context) {
        super(context);
        this.context = context;
    }

    public UserListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public UserListItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void bind( UserEntity data) {
        this.data = data;
        ul_name.setText    ("Name        =" +data.user_name);
        ul_amount.setText  ("Amount      =" +data.balance+"  MMK");
        ul_password.setText("Password    =" +data.password);
        ul_phone.setText   ("Phone       ="+data.phone_number);
    }

}
