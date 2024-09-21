package project;

public abstract class Level {
    
    public abstract double calculateFee();
    
    public abstract String toString();
    
    public double getcalculteFee(){
       return calculateFee(); 
    }
    
    public String getLevel(){
       return toString(); 
    }
        
}
