package Workers;

public class FWorker extends Worker{
    public String name;
    public double averageMonthlyWage;

    public FWorker(String name, double hourlyRate){
        this.name = name;
        averageMonthlyWageCount(hourlyRate);
    }

    @Override
    public void averageMonthlyWageCount(double hourlyRate) {
        this.averageMonthlyWage = 20.8*8*hourlyRate;
    }

    public double getAverageMonthlyWage() {
        return averageMonthlyWage;
    }

    @Override
    public String toString() {
        return "FWorker{" +
                "name='" + name + '\'' +
                ", averageMonthlyWage=" + averageMonthlyWage +
                '}';
    }
}
