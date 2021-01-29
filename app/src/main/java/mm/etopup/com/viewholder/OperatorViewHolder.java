package mm.etopup.com.viewholder;

import android.graphics.Color;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import mm.etopup.com.R;
import mm.etopup.com.base.views.holders.BaseViewHolder;

public class OperatorViewHolder <T> extends BaseViewHolder<T> {

    @BindView(R.id.operatorid)
    CircleImageView operator;

    OperatorSelectListener operatorSelectListener;
    int previous_position= -1;

    public OperatorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(T data) {

    }


    public void bindData(T data , int previousposition , OperatorSelectListener operatorSelectListener) {
        this.operatorSelectListener = operatorSelectListener;
        this.previous_position = previousposition;

        if(previousposition != getAdapterPosition())
        {
            operator.setBorderColor(Color.LTGRAY);
        }else{
            operator.setBorderColor(0xFF673AB7);
        }

        if (data instanceof String){
            if(((String) data).equalsIgnoreCase("ooredoo")) {
                operator.setImageResource(R.drawable.ooredoo);
            } else
            if(((String) data).equalsIgnoreCase("mpt")) {
                operator.setImageResource(R.drawable.mpt);
            } else
            if(((String) data).equalsIgnoreCase("mytel")) {
                operator.setImageResource(R.drawable.mytel);
            } else
            if(((String) data).equalsIgnoreCase("telenor")) {
                operator.setImageResource(R.drawable.telenor);
            } else
            if(((String) data).equalsIgnoreCase("mec")) {
                operator.setImageResource(R.drawable.mec);
            }
        }

        operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous_position = getAdapterPosition();
                operatorSelectListener.selectedOperatorItem(previous_position , data.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    public interface OperatorSelectListener {
        void selectedOperatorItem(  int previousposition , String operator);
    }
}