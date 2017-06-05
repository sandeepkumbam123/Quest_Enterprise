package quest.com.quest.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * Created by kumbh on 16-04-2017.
 */

public class LoginResponseModel {
    public LoginResponseModel(int user_id, int branch_id, String branch_name, String section, String classX, String subject,
                              boolean is_success, String session_id, String user_name, String role, int role_id, int errorCode,
                              String errorMessage) {
        this.user_id = user_id;
        this.branch_id = branch_id;
        this.branch_name = branch_name;
        this.section = section;
        this.classX = classX;
        this.subject = subject;
        this.is_success = is_success;
        this.session_id = session_id;
        this.user_name = user_name;
        this.role = role;
        this.role_id = role_id;
        ErrorCode = errorCode;
        ErrorMessage = errorMessage;
    }

    /**
     * user_id : 2
     * branch_id : 1
     * branch_name : Ameerpet Branch
     * section : null
     * class : null
     * subject : null
     * is_success : true
     * session_id : 3FABD9EC
     * user_name :
     * role : admin
     * role_id : 2
     * ErrorCode : null
     * ErrorMessage : null
     */


    private int user_id;
    private int branch_id;
    private String branch_name;
    private String section;
    @SerializedName("class")
    private String classX;
    private String subject;
    private boolean is_success;
    private String session_id;
    private String user_name;
    private String role;
    private int role_id;
    private int ErrorCode;
    private String ErrorMessage;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Object getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }


    /*@SerializedName("issuccess,")
    private boolean _$Issuccess127; // FIXME check this code
    private String status;

    public static LoginResponseModel objectFromData(String str) {

        return new Gson().fromJson(str, LoginResponseModel.class);
    }

    public boolean is_$Issuccess127() {
        return _$Issuccess127;
    }

    public void set_$Issuccess127(boolean _$Issuccess127) {
        this._$Issuccess127 = _$Issuccess127;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/

}
