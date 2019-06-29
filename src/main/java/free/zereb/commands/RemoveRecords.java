package free.zereb.commands;


import free.zereb.Main;
import free.zereb.data.Record;
import free.zereb.utils.Command;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RemoveRecords implements Command {


    @Override
    public String getDescription() {
        return "Removes records from db";
    }

    @Override
    public void run() {
        Scanner recSc = new Scanner(System.in);
        System.out.println("Username:");
        String user = recSc.nextLine();
        System.out.println("Url");
        String url = recSc.nextLine();

        System.out.println("Found these records, remove? [y/n]");
        List<Record> toRemove = Main.records.stream()
                .filter(record -> record.url.contains(url))
                .filter(record -> record.username.equals(user))
                .collect(Collectors.toList());
        toRemove.forEach(System.out::println);
        if (toRemove.size() < 1)
            return;
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equals("y")){
            Main.records.removeAll(toRemove);
            toRemove.forEach(Record::removeFromDB);
        }

    }
}
