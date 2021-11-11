package launcher;

/**
 * @Description
 * @Author weiyu
 * @Version V1.0.0
 * @Since 1.0
 * @Date 11/11/2021
 */
public class CommandBuilderUtils {

    /** Joins a list of strings using the given separator. */
    static String join(String sep, String... elements) {
        StringBuilder sb = new StringBuilder();
        for (String e : elements) {
            if (e != null) {
                if (sb.length() > 0) {
                    sb.append(sep);
                }
                sb.append(e);
            }
        }
        return sb.toString();
    }


    /** Joins a list of strings using the given separator. */
    static String join(String sep, Iterable<String> elements) {
        StringBuilder sb = new StringBuilder();
        for (String e : elements) {
            if (e != null) {
                if (sb.length() > 0) {
                    sb.append(sep);
                }
                sb.append(e);
            }
        }
        return sb.toString();
    }


}
