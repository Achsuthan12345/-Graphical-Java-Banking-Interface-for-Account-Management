package project;

public class SilverLevel extends Level {
    @Override
    public double calculateFee() {
        return 20;
    }
    @Override
    public String toString() {
        return "Silver";
    }
}

