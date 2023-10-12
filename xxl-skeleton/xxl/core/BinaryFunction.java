package xxl.core;

public abstract class BinaryFunction extends Function{
    public static final int NUMBER_OF_ARGUMENTS = 2;
    public static final int ARGUMENT_1 = 0;
    public static final int ARGUMENT_2 = 1;

    private Content[] _arguments = new Content[NUMBER_OF_ARGUMENTS];


    public BinaryFunction(String name, Content[] content){
        super(name);
        _arguments = content;
    }
    public String toString(){
        return "=" + getName() + "(" + _arguments[ARGUMENT_1].toString() + "," + _arguments[ARGUMENT_2].toString() + ")";
    }
}
