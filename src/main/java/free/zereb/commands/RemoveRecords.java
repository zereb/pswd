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
        return "Interactive removing records from db";
    }

    @Override
    public void run() {
        Scanner recSc = new Scanner(System.in);
        System.out.println("inpud id of record to delete");
        int id = Integer.parseInt(recSc.nextLine());
        System.out.println("Found these record, remove? [y/n]");
        List<Record> toRemove = Main.records.stream()
                .filter(record -> record.id == id)
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
