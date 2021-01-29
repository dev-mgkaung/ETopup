package mm.etopup.com.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import mm.etopup.com.R;
import mm.etopup.com.base.views.holders.BaseViewHolder;

public class AmountViewHolder <T> extends BaseViewHolder<T> {

    @BindView(R.id.amount_text)
    TextView amount_text;

    @BindView(R.id.checkimage)
    ImageView checkimage;

    @BindView(R.id.amount_layout)
    LinearLayout amount_layout;

    AmountSelectListener amountSelectListener;
    int previous_position= -1;

    public AmountViewHolder(View itemView ) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void bind(T data) {
    }

    public void bindData(T data ,int previousposition ,AmountSelectListener amountSelectListener) {
        this.amountSelectListener =amountSelectListener;
        this.previous_position = previousposition;
        if (data instanceof String){

            if(previousposition != getAdapterPosition())
            {
                checkimage.setVisibility(View.GONE);
                amount_layout.setBackgroundResource(R.drawable.rounded_border_grey_amount);
            }else{
                checkimage.setVisibility(View.VISIBLE);
                amount_layout.setBackgroundResource(R.drawable.rounded_border_blue_amount);
            }

            amount_text.setText(data.toString() + " Ks ");

            amount_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    previous_position = getAdapterPosition();
                    amountSelectListener.selectedAmountItem(previous_position, data.toString());

                }
            });
        }
    }

    @Override
    public void onClick(View v) {

    }

    public interface AmountSelectListener {
        void selectedAmountItem(  int previousposition , String amount);
    }
}