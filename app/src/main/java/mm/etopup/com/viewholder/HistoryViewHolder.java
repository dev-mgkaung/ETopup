package mm.etopup.com.viewholder;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.base.views.holders.BaseViewHolder;
import mm.etopup.com.database.entity.TransitionHistory;

public class HistoryViewHolder <T> extends BaseViewHolder<T> {

    @BindView(R.id.history_date)
    TextView history_date;

    @BindView(R.id.rechargeNo)
    TextView rechargeNo;

    @BindView(R.id.operatorname)
    TextView operatorname;

    @BindView(R.id.amount)
    TextView amount;


    public HistoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(T data) {

    }


    public void bindData(T data ) {

        if (data instanceof TransitionHistory){
            history_date.setText(((TransitionHistory) data).date);
            rechargeNo.setText(((TransitionHistory) data).phonenumber);
            operatorname.setText(((TransitionHistory) data).operatorName);
            amount.setText(((TransitionHistory) data).amount +" Ks");
        }

    }

    @Override
    public void onClick(View v) { }

}