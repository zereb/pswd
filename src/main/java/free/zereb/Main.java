package free.zereb;

import free.zereb.commands.AddRecord;
import free.zereb.commands.Exit;
import free.zereb.commands.Help;
import free.zereb.commands.getRecords;
import free.zereb.data.Record;
import free.zereb.utils.ArgumentHandler;
import free.zereb.utils.Command;
import free.zereb.utils.H2Connect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

public static ArrayList<Record> records;
public static HashMap<String, Command> commands = new HashMap<>();
public static H2Connect h2;

    private Main(String[] args){
        h2 = new H2Connect();
        records = Record.getRecordsFromDB();
        new ArgumentHandler().runArgs(args);



        commands.put("exit", new Exit());
        commands.put("help", new Help());
        commands.put("add", new AddRecord());
        commands.put("records", new getRecords());

        while (true) {
            Scanner in = new Scanner(System.in);
            String command = in.next();
            commands.forEach((cmd, run) -> {
                if (command.equals(cmd))
                    run.run();
            });
        }
    }

    public static void main(String[] args) {
        new Main(args);
    }

}
