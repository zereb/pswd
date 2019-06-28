package free.zereb.commands;

import free.zereb.Main;
import free.zereb.utils.Command;

public class Help implements Command {

    @Override
    public String getDescription() {
        return "Help";
    }

    @Override
    public void run() {
        Main.commands.forEach((cmd, run) -> {
            String spaces = " ".repeat(Math.max(0, 15 - cmd.length()));
            System.out.println(cmd + spaces + run.getDescription());
        });

    }
}
