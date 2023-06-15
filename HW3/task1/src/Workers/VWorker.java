package Workers;

public class VWorker extends Worker {
    public String name;
    public double averageMonthlyWage;

    public VWorker(String name, double monthlyFixedWage){
        this.name = name;
        averageMonthlyWageCount(monthlyFixedWage);
    }
    @Override
    public void averageMonthlyWageCount(double monthlyFixedWage) {
        this.averageMonthlyWage = monthlyFixedWage;
    }

    public double getAverageMonthlyWage() {
        return averageMonthlyWage;
    }

    @Override
    public String toString() {
        return "VWorker{" +
                "name='" + name + '\'' +
                ", averageMonthlyWage=" + averageMonthlyWage +
                '}';
    }
}
