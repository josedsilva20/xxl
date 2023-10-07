package xxl.core;

public class LiteralString extends Literal {
    private String _value;

    /**
     * 
     * @param value initializes the string _value
     */
    public LiteralString(String value){
        _value = value;
    }

    public LiteralString(){
        
    }

    /**
     * @return the StringLiteral
     */
    Literal value(){
        return this;
    }

    /**
     * @return the correspondent int value
     */
    public String asString(){
        return _value;
    }

    /**
     * @return its string representation
     */
    public String toString(){
        return "\'" + _value;
    }

}
