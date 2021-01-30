package mm.etopup.com.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import mm.etopup.com.R;
import mm.etopup.com.base.adapter.BaseRecyclerAdapter;
import mm.etopup.com.base.views.holders.BaseViewHolder;
import mm.etopup.com.viewholder.AmountViewHolder;

public class AmountListAdapter<T extends BaseViewHolder, W> extends BaseRecyclerAdapter<T, W> {

    int previous_postion = -1;
    AmountViewHolder.AmountSelectListener amountSelectListener ;
    public AmountListAdapter(Context context, AmountViewHolder.AmountSelectListener amountSelectListener) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
        this.amountSelectListener = amountSelectListener;
    }

    public  void setAmount(int previous_postion)
    {
        this.previous_postion =previous_postion;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.amount_list_item, parent, false);
        return (T) new AmountViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        if (holder instanceof AmountViewHolder) {
            AmountViewHolder amountViewHolder = (AmountViewHolder) holder;
            if(amountViewHolder!=null) {
                amountViewHolder.bindData(mData.get(position),previous_postion,amountSelectListener);
            }
        }
    }

}
