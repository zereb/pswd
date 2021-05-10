package free.zereb.commands;

import free.zereb.Main;

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
