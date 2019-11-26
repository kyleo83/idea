package com.example.idea;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Utils {
    private static final String TAG = "Utils";
//
//    public static List<Design> loadDesigns(Context context){
//        try{
//            GsonBuilder builder = new GsonBuilder();
//            Gson gson = builder.create();
//            JSONArray array = new JSONArray(loadJSONFromAsset(context, "designs.json"));
//            List<Design> profileList = new ArrayList<>();
//            for(int i=0;i<array.length();i++){
//                Design design = gson.fromJson(array.getString(i), Design.class);
//                profileList.add(design);
//            }
//            return profileList;
//        }catch (Exception e){
//            e.printStackTrace();
//            return null;
//        }
//    }

//    private static String loadJSONFromAsset(Context context, String jsonFileName) {
//        String json = null;
//        InputStream is=null;
//        try {
//            AssetManager manager = context.getAssets();
//            Log.d(TAG,"path "+jsonFileName);
//            is = manager.open(jsonFileName);
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }

    public static Point getDisplaySize(WindowManager windowManager){
        try {
            if(Build.VERSION.SDK_INT > 16) {
                Display display = windowManager.getDefaultDisplay();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                display.getMetrics(displayMetrics);
                return new Point(displayMetrics.widthPixels, displayMetrics.heightPixels);
            }else{
                return new Point(0, 0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Point(0, 0);
        }
    }
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }


//    /**
//     * Gets the uid from current user through FirebaseAuth.
//     * Catches NullPointerException.
//     * @return String uid
//     */
//    public static String getCurrentUserUid() {
//        return FirebaseAuth.getInstance().getCurrentUser().getUid();
//    }

}
