package quest.com.quest.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kumbh on 16-04-2017.
 */

public class LoginResponseModel {


    @SerializedName("issuccess,")
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
    }
}
