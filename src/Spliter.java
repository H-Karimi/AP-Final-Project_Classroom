public class Spliterer {
    static String[] page0(String ask) {
        String[] arrOfStr = new String[3];
        arrOfStr[0] = ask.substring(1, 2);
        arrOfStr[1] = ask.substring(2, ask.indexOf('.'));
        arrOfStr[2] = ask.substring(ask.indexOf('.'));
        return arrOfStr;
    }
    static char page1(String ask){
        char respond=ask.charAt(2);
        return respond;
    }
}
