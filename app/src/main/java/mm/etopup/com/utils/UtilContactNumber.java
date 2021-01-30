package mm.etopup.com.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

import mm.etopup.com.database.entity.Contact;

public class UtilContactNumber {

    public UtilContactNumber(){}

    private static UtilContactNumber objInstance;


    public static UtilContactNumber getInstance() {
        if (objInstance == null) {
            objInstance = new UtilContactNumber();
        }
        return objInstance;
    }


    public static  ArrayList<Contact> getContactList(Context context) {
        ArrayList<Contact> list = new ArrayList<Contact>();
        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i("Contact", "Name: " + name);
                        Log.i("Contact", "Phone Number: " + phoneNo);
                        list.add(new Contact(name , phoneNo));
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }

        return list;
    }
}
