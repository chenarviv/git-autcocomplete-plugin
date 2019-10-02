package co.chen.plugin.Terminal;

public class GoodWindowExec {

    public static String execute(String aCommand) {
        String output = "";
        try {
            String osName = System.getProperty("os.name");
            String[] cmd = new String[3];
            if (osName.startsWith("Windows")) {
                cmd[0] = "cmd.exe";
                cmd[1] = "/C";
                cmd[2] = aCommand;
            }

            Runtime rt = Runtime.getRuntime();
            System.out.println("Executing " + cmd[0] + " " + cmd[1]
                    + " " + cmd[2]);
            Process proc = rt.exec(cmd);
            // any error message?
            StreamGobbler errorGobbler = new
                    StreamGobbler(proc.getErrorStream(), "ERROR");

            // any output?
            StreamGobbler outputGobbler = new
                    StreamGobbler(proc.getInputStream(), "OUTPUT");

            // kick them off
            errorGobbler.start();
            outputGobbler.start();

            // any error???
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);

            output = outputGobbler.getOutput();
            System.out.println("Final output: " + output);
        return output;

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return  output;
    }
}