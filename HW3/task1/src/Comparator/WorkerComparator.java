package Comparator;

import Workers.*;
import java.util.Comparator;

public class WorkerComparator implements Comparator<Worker> {

    @Override
    public int compare(Worker o1, Worker o2) {
        if (o1.getAverageMonthlyWage() == o2.getAverageMonthlyWage()){
            return 0;
        }
        return o1.getAverageMonthlyWage() > o2.getAverageMonthlyWage() ? -1:1;
    }
}
