package mm.etopup.com.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import mm.etopup.com.R;
import mm.etopup.com.base.adapter.BaseRecyclerAdapter;
import mm.etopup.com.base.views.holders.BaseViewHolder;
import mm.etopup.com.viewholder.ContactViewHolder;

public class ContactAdapter<T extends BaseViewHolder, W> extends BaseRecyclerAdapter<T, W> {

    ContactViewHolder.ContactSelectListener contactSelectListener;
    public ContactAdapter(Context context , ContactViewHolder.ContactSelectListener contactSelectListener) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
        this.contactSelectListener =contactSelectListener;
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);
        return (T) new ContactViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        if (holder instanceof ContactViewHolder) {
            ContactViewHolder contactViewHolder = (ContactViewHolder) holder;
            if(contactViewHolder!=null) {
                contactViewHolder.bindData(mData.get(position),contactSelectListener);
            }
        }
    }

}
