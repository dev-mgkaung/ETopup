package mm.etopup.com.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transition")
public class TransitionHistory {

    public long getTransition_id() {
        return transition_id;
    }

    public void setTransition_id(long transition_id) {
        this.transition_id = transition_id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @PrimaryKey(autoGenerate =  true)
    public long transition_id;
    public String operatorName;
    public String phonenumber;
    public String user_email;
    public String date;
    public String amount;

    public TransitionHistory() { }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public TransitionHistory(String operatorName, String phonenumber, String user_email, String date, String amount) {
        this.operatorName = operatorName;
        this.phonenumber = phonenumber ;
        this.user_email = user_email;
        this.amount =amount;
        this.date = date;
    }
}
