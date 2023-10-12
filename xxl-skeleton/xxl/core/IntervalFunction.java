package xxl.core;

public abstract class IntervalFunction extends Function {
    private Range _range;

    public IntervalFunction(String name, Range range){
        super(name);
        _range = range;
    }

    public String toString(){
        return "=" + getName() + _range.toString();
    }
}
