package xxl.core;



/**
 * Class representing a Cell.
 */
public class Cell {
    private int _column;
    private int _line;
    private Content _content;

    
    /**
     * Constructor of cell used when spreadsheet is created
     * @param line
     * @param column
     */
    public Cell (int line, int column){
        _column = column;
        _line = line;
    }

    public int getLine(){
        return _line;
    }

    public int getColumn(){
        return _column;
    }
    
    /**
     * Add content to cell
     * @param content of type Content (can be literal, reference or function)
     */
    void setContent(Content content){
        _content = content;
    }

    public boolean equals(Cell cell){
        return (_column == cell._column && _line == cell._line);
    }

    public Content getContent(){
        return _content;
    }

    /**
     * Represents toString from the content
     */
    public String toString (){
        return _content.toString();
    }

    /**
     * 
     * @return content of cell
     */
    Literal value(){
        return _content.value();
    }

    
}
