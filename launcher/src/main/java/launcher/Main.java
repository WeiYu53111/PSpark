package launcher;

import java.io.IOException;
import java.util.*;


import static launcher.CommandBuilderUtils.*;
/**
 * @Description
 * @Author weiyu
 * @Version V1.0.0
 * @Since 1.0
 * @Date 11/10/2021
 */
public class Main {

    private static List<String> buildCommand(
            AbstractCommandBuilder builder,
            Map<String, String> env,
            boolean printLaunchCommand) throws IOException, IllegalArgumentException {
        List<String> cmd = builder.buildCommand(env);
        if (printLaunchCommand) {
            System.err.println("Spark Command: " + join(" ", cmd));
            System.err.println("========================================");
        }
        return cmd;
    }


    /**
     * Prepare the command for execution from a bash script. The final command will have commands to
     * set up any needed environment variables needed by the child process.
     */
    private static List<String> prepareBashCommand(List<String> cmd, Map<String, String> childEnv) {
        if (childEnv.isEmpty()) {
            return cmd;
        }

        List<String> newCmd = new ArrayList<>();
        newCmd.add("env");

        for (Map.Entry<String, String> e : childEnv.entrySet()) {
            newCmd.add(String.format("%s=%s", e.getKey(), e.getValue()));
        }
        newCmd.addAll(cmd);
        return newCmd;
    }

    public static void main(String[] argsArray) throws Exception {
        List<String> args = new ArrayList<>(Arrays.asList(argsArray));
        String className = args.remove(0);

        boolean printLaunchCommand = true;
        Map<String, String> env = new HashMap<>();
        List<String> cmd;


        if (className.equals("org.apache.spark.deploy.SparkSubmit")) {
            try {
                /**
                 * 主要的校验以及转换参数就在SparkSubmitCommandBuilder中
                 */
                AbstractCommandBuilder builder = new SparkSubmitCommandBuilder(args);
                cmd = buildCommand(builder, env, printLaunchCommand);
            } catch (IllegalArgumentException e) {
                printLaunchCommand = false;
                System.err.println("Error: " + e.getMessage());
                System.err.println();
                //随便给个空的,否则后面会提示cmd未初始化
                cmd = new ArrayList<>();
            }
        }else{

            /***
             * 第一版不考虑其他命令,这里是SparkSubmit以外的解析代码，例如 启动master等
             */
            //随便给个空的,否则后面会提示cmd未初始化
            cmd = new ArrayList<>();
        }

        List<String> bashCmd = prepareBashCommand(cmd, env);
        for (String c : bashCmd) {
            System.out.print(c);
            System.out.print('\0');
        }


    }
}
