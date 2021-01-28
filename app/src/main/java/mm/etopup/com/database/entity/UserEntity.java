package mm.etopup.com.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserEntity {

    @PrimaryKey
    public int user_id;
    public String user_name;
    public String password;
    public String phone_number;
    public int balance;
    public String user_type;


    public UserEntity(){}

     public UserEntity(int user_id, String user_name, String password, String phone_number, int balance,String user_type)
     {
         this.user_id =user_id;
         this.user_name = user_name;
         this.password= password;
         this.phone_number = phone_number;
         this.balance =balance;
         this.user_type= user_type;
     }

    public String getUserType() {
        return user_type;
    }

    public void setUserType(String user_type) {
        this.user_type = user_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}