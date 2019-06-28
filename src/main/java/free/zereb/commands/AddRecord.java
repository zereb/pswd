package free.zereb.commands;

import free.zereb.Main;
import free.zereb.data.Record;
import free.zereb.utils.Command;

import java.util.Scanner;

public class AddRecord implements Command {
    @Override
    public String getDescription() {
        return "Adds new record";
    }

    @Override
    public void run() {
        Scanner recSc = new Scanner(System.in);
        System.out.println("Username:");
        String user = recSc.nextLine();
        System.out.println("Password:");
        String passw = recSc.nextLine();
        System.out.println("Url");
        String url = recSc.nextLine();
        System.out.println("Tags:");
        String tags = recSc.nextLine();

        Record record = new Record(passw, user, url);
        Main.records.add(Record.insertInDB(record));


    }
}
