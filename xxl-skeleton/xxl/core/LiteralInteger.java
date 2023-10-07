package xxl.core;

/**
 * Abstraction for integers
 */
public class LiteralInteger extends Literal {

    private int _value;

    /**
     * 
     * @param value initializes the intiger
     */
    public LiteralInteger(int value){
        _value = value;
    }

    public LiteralInteger(){
        
    }
    /**
     * @return the IntegerLiteral
     */
    Literal value(){
        return this;
    }

    /**
     * @return the correspondent int value
     */
    public int asInt(){
        return _value;
    }

    /**
     * @return its string representation
     */
    public String toString(){
        return "" + this.asInt();
    }

}
