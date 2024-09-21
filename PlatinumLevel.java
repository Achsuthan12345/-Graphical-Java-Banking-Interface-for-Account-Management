package project;

public class PlatinumLevel extends Level {
    @Override
    public double calculateFee() {
        return 0;
    }
    @Override
    public String toString() {
        return "Platinum";
    }
}