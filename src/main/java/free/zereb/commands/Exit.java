package free.zereb.commands;

import free.zereb.utils.Command;

public class Exit implements Command {

    @Override
    public String getDescription() {
        return "Exit from programm";
    }

    @Override
    public void run() {
        System.exit(0);

    }
}
