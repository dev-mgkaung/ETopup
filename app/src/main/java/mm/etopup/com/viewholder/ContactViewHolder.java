package mm.etopup.com.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.base.views.holders.BaseViewHolder;
import mm.etopup.com.database.entity.Contact;

public class ContactViewHolder <T> extends BaseViewHolder<T> {

    @BindView(R.id.phone_linear)
    LinearLayout phone_linear;

    @BindView(R.id.contactname)
    TextView contactname;

    @BindView(R.id.contactphonenumber)
    TextView contactphonenumber;

    ContactSelectListener contactSelectListener;
    public ContactViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(T data) {

    }


    public void bindData(T data , ContactSelectListener contactSelectListener ) {
        this.contactSelectListener = contactSelectListener;
        if (data instanceof Contact){
            contactname.setText(((Contact) data).getName());
            contactphonenumber.setText(((Contact) data).getPhone());

            phone_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contactSelectListener.selectedPhoneItem(((Contact) data).getName(),((Contact) data).getPhone().toString().trim());
                }
            });
        }

    }

    @Override
    public void onClick(View v) { }

    public interface ContactSelectListener {
        void selectedPhoneItem(  String name, String phone);
    }
}