package xxl.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an interval of cells
 */
public class Range {
    
    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;

    public Range(Cell begin, Cell end){
        _beginColumn = begin.getColumn();
        _beginRow = begin.getLine();
        _endColumn = end.getColumn();
        _endRow = end.getLine();
    }

    public Range getRange(){
        return this;
    }

    /**
     * 
     * @return cells in that range
     */
    List<Cell> getCells(){
        List<Cell> cells = new ArrayList<>();
        int begining = _beginColumn;
        while (_beginRow <= _endRow){
            while (_beginColumn <= _endColumn)
                cells.add(new Cell(_beginRow, _beginColumn));
            _beginColumn = begining;
        }
        return cells;
    }

    public String toString(){
        return "(" + _beginRow + ";" + _beginColumn + ":" + _endRow + ";" + _endColumn + ")";
    }
}
