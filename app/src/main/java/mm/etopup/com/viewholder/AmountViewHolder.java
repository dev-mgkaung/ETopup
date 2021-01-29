package mm.etopup.com.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import mm.etopup.com.R;
import mm.etopup.com.base.views.holders.BaseViewHolder;

public class AmountViewHolder <T> extends BaseViewHolder<T> {

    @BindView(R.id.amount_text)
    TextView amount_text;


    public AmountViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(T data) {
        if (data instanceof String){
            amount_text.setText(data.toString());
        }
    }


    @Override
    public void onClick(View v) {

    }
}