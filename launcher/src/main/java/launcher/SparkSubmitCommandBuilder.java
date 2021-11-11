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
public class SparkSubmitCommandBuilder extends AbstractCommandBuilder  {


    SparkSubmitCommandBuilder(List<String> args) {
        List<String> submitArgs = args;
        if (args.size() > 0) {
            OptionParser parser = new OptionParser(true);
            parser.parse(submitArgs);
            this.isSpecialCommand = parser.isSpecialCommand;
        } else {
            //
        }

    }

    @Override
    public List<String> buildCommand(Map<String, String> env)
            throws IOException, IllegalArgumentException {

            return buildSparkSubmitCommand(env);

    }



}
