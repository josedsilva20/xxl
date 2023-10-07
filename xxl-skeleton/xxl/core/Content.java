package xxl.core;

import xxl.core.exception.InvalidAsIntegerException;
import xxl.core.exception.InvalidAsStringException;
/**
 * Class that represents the content inside of a cell
 */
public abstract class Content {

    /**
     * to be implemented by derivate classes
     */
    public abstract String toString();

    /**
     * 
     * @return the value of content
     */
    abstract Literal value();

    /**
     * 
     * @return string of type StringLiteral
     * @throws InvalidAsStringException
     */
    public String asString() throws InvalidAsStringException {
        throw new InvalidAsStringException();
    }

    /**
     * 
     * @return string of type IntegerLiteral
     * @throws InvalidAsIntegerException
     */
    public int asInt() throws InvalidAsIntegerException {
        throw new InvalidAsIntegerException();
    }
}
