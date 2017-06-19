package quest.com.quest.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kumbh on 28-05-2017.
 */

public  class Utilities {


    public static Calendar returnDatefromString(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date convertedDate = new Date();
        Calendar calendar = Calendar.getInstance();
        try {
            convertedDate = dateFormat.parse(date);
            calendar.setTime(convertedDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return calendar;
    }

    public static String returnDuration(int time){

        return time +"mins";
    }


    public static String returnTime(int millis){
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;
        String time ="";
        if(hour==0){
            time = String.format("%02d:%02d", minute, second);
        }else if(hour==0 && minute==0 && second ==0){
             return  "";
        }
        else {
            time = String.format("%02d:%02d:%02d", hour, minute, second);
        }

       return time+"";
    }



    public static String returnDatefromMillis(long millis){
            Date date = new Date();
            date.setTime(millis);
            String formattedDate=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(date);
            return formattedDate;

        }
}
