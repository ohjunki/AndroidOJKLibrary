package ojk.android.utils;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;

public class AndroidUtilLibrary {
    public static boolean DEBUG = BuildConfig.DEBUG;

    public static void initLibraryBuildConfig( Context context ){
        try{
            DEBUG = (Boolean)initBuildConfigValue( context, "DEBUG");
        }catch (Exception ignore){}

        Log.e("TESTING", "debug="+DEBUG);
    }
    /**
     * Gets a field from the project's BuildConfig. This is useful when, for example, flavors
     * are used at the project level to set custom fields.
     * @param context       Used to find the correct file
     * @param fieldName     The name of the field-to-access
     * @return              The value of the field, or {@code null} if the field is not found.
     */
    private static Object initBuildConfigValue(Context context, String fieldName) {
        try {
            Class<?> clazz = Class.forName(context.getPackageName() + ".BuildConfig");
            Field field = clazz.getField(fieldName);
            return field.get(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
