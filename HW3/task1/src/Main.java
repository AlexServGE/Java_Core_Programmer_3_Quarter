import Workers.*;
import Comparator.WorkerComparator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Worker> workers = new ArrayList<Worker>();
        workers.add(new VWorker("Саша", 5));
        workers.add(new FWorker("Вася",1));
        workers.add(new VWorker("Катя",2));
        workers.add(new VWorker("Аня",2));
        workers.add(new VWorker("Даша",0));

        for (Worker worker: workers){
            System.out.println(worker);
        }
        System.out.println();

        workers.sort(new WorkerComparator());

        for (Worker worker: workers){
            System.out.println(worker);
        }

    }
}