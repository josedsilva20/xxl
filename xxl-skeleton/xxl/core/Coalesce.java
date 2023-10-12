package xxl.core;

public class Coalesce extends IntervalFunction  {
    
    public Coalesce (Range range){
        super("COALESCE", range);
    }

    // to implement
    protected Literal compute(){
        return null;
    }
}
