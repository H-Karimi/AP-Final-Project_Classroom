public class Spliter {
    static String[] page0(String ask) {
        String[] arrOfStr = ask.split("#");
        return arrOfStr;
    }
    static char page1(String ask){
        char respond=ask.charAt(2);
        return respond;
    }
}
