package mm.etopup.com.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import mm.etopup.com.R;
import mm.etopup.com.base.adapter.BaseRecyclerAdapter;
import mm.etopup.com.base.views.holders.BaseViewHolder;
import mm.etopup.com.viewholder.OperatorViewHolder;

public class OperatorListAdapter<T extends BaseViewHolder, W> extends BaseRecyclerAdapter<T, W> {

    public OperatorListAdapter(Context context ) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.operator_list_item, parent, false);
        return (T) new OperatorViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        if (holder instanceof OperatorViewHolder) {
            OperatorViewHolder operatorViewHolder = (OperatorViewHolder) holder;
            if(operatorViewHolder!=null) {
                operatorViewHolder.bind(mData.get(position));
            }
        }
    }

}
