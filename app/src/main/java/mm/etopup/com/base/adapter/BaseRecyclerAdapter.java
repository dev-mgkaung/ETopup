package mm.etopup.com.base.adapter;

import android.content.Context;
import android.os.Handler;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;
import mm.etopup.com.base.views.holders.BaseViewHolder;


public abstract class BaseRecyclerAdapter<T extends BaseViewHolder, W> extends RecyclerView.Adapter<T> {

    protected LayoutInflater mLayoutInflater;

    protected List<W> mData;

    public BaseRecyclerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mData = new ArrayList<W>();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setNewData(List<W> newData) {
        mData = newData;
        notifyDataSetChanged();
    }

    public void appendNewData(List<W> newData) {
        mData.addAll(newData);
        notifyDataSetChanged();
    }

    public W getItemAt(int position) {
        if (position < mData.size())
            return mData.get(position);

        return null;
    }

    public List<W> getItems() {
        if (mData == null)
            return new ArrayList<W>();

        return mData;
    }

    public void removeData(W data) {
        mData.remove(data);
        notifyItemRemoved(mData.size());
    }

    public void addNewData(W data) {
        mData.add(data);
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                notifyItemInserted(mData.size());
            }
        };
        handler.post(r);
    }

    public void clearData() {
        mData = new ArrayList<W>();
        notifyDataSetChanged();
    }
}
