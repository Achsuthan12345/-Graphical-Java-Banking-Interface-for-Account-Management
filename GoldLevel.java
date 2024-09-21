package project;

public class GoldLevel extends Level {
    @Override
    public double calculateFee() {
        return 10;
    }
    @Override
    public String toString() {
        return "Gold";
    }
}

