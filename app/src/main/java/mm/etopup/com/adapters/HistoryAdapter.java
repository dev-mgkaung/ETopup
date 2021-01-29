package mm.etopup.com.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import mm.etopup.com.R;
import mm.etopup.com.base.adapter.BaseRecyclerAdapter;
import mm.etopup.com.base.views.holders.BaseViewHolder;
import mm.etopup.com.viewholder.HistoryViewHolder;

public class HistoryAdapter<T extends BaseViewHolder, W> extends BaseRecyclerAdapter<T, W> {

    public HistoryAdapter(Context context ) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_listitem, parent, false);
        return (T) new HistoryViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        if (holder instanceof HistoryViewHolder) {
            HistoryViewHolder historyViewHolder = (HistoryViewHolder) holder;
            if(historyViewHolder!=null) {
                historyViewHolder.bindData(mData.get(position));
            }
        }
    }

}
