package free.zereb.commands;

import free.zereb.Main;
import free.zereb.utils.Command;

public class getRecords implements Command {

    @Override
    public String getDescription() {
        return "returns all records";
    }

    @Override
    public void run() {
        Main.records.forEach(System.out::println);
    }
}
