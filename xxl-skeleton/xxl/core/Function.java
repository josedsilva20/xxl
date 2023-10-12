package xxl.core;

import java.util.ArrayList;

import xxl.core.exception.InvalidAsIntegerException;
import xxl.core.exception.InvalidAsStringException;

public abstract class Function extends Content{
    private String _name;

    public Function(String name){
        _name = name;
    }

    protected abstract Literal compute();

    public String asString() throws InvalidAsStringException{
        return compute().asString();
    }

    public int asInt() throws InvalidAsIntegerException{
        return compute().asInt();
    }

    public Literal value(){
        return compute().value();
    }

    public void setName(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }
}