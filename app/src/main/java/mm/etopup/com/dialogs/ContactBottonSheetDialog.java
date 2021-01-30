package mm.etopup.com.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import mm.etopup.com.R;
import mm.etopup.com.adapters.ContactAdapter;
import mm.etopup.com.utils.UtilContactNumber;
import mm.etopup.com.viewholder.ContactViewHolder;

public class ContactBottonSheetDialog extends BottomSheetDialogFragment implements ContactViewHolder.ContactSelectListener {
    ContactAdapter adapter;
    ContactBottomSheetSelectListener contactBottomSheetSelectListener;

   public ContactBottonSheetDialog(ContactBottomSheetSelectListener contactBottomSheetSelectListener)
    {
        this.contactBottomSheetSelectListener = contactBottomSheetSelectListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.contact_botton_sheet, container, false);

        RecyclerView phoneList = v.findViewById(R.id.phoneList);
        adapter = new ContactAdapter(getActivity(), this);
        phoneList.setAdapter(adapter);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        phoneList.setLayoutManager(llm);
        adapter.setNewData( UtilContactNumber.getInstance().getContactList(getActivity()));
        return v;
    }

    @Override
    public void selectedPhoneItem(String name, String phone) {
       String s= phone.replace(" ", "") ;
     contactBottomSheetSelectListener.selectedPhoneItem(name,s);
     dismiss();
    }

    public interface ContactBottomSheetSelectListener {
        void selectedPhoneItem(  String name, String phone);
    }
}