package cm.supptic.lidms;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by codename-tkc on 23/07/2018.
 */

public class IDStore {
    public static  final String SHARED_PREFERENCE_FOLDER="sakdhaljsdasd";
    public static  final String FOLDER_NAME="123412ASDASDCV";
    public static  final String DEFAULT_VALUE="123edsfSDCV";
    public  static void addId(String hashKey,Context context){
        SharedPreferences sharedPreferences  = context .getSharedPreferences(SHARED_PREFERENCE_FOLDER,Context.MODE_PRIVATE);
        String val = sharedPreferences.getString(FOLDER_NAME,DEFAULT_VALUE);
        if(val.equals(DEFAULT_VALUE)){
            sharedPreferences.edit().putString(FOLDER_NAME,hashKey).apply();
            Log.i("LiDMS","the new content is "+hashKey);
        }else{
            val+="_"+hashKey;
            sharedPreferences.edit().putString(FOLDER_NAME,val).apply();
            Log.i("LiDMS","the new content is "+val);
        }
    }
    public static  String getIds(Context context){
        SharedPreferences sharedPreferences  = context .getSharedPreferences(SHARED_PREFERENCE_FOLDER,Context.MODE_PRIVATE);
        return sharedPreferences.getString(FOLDER_NAME,DEFAULT_VALUE);
    }
}
