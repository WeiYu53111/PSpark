package launcher;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author weiyu
 * @Version V1.0.0
 * @Since 1.0
 * @Date 11/11/2021
 */
abstract class AbstractCommandBuilder {

    abstract List<String> buildCommand(Map<String, String> env)
            throws IOException, IllegalArgumentException;

}
