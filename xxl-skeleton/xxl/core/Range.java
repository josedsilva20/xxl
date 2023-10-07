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
}
