package free.zereb.commands;

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
