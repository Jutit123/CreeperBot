package util;

public class utils {

    public static int getInt(String string){
        try {
            return Integer.parseInt(string);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

}
