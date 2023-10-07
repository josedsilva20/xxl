package xxl.core;

public class Reference extends Content {
    private Spreadsheet _spreadsheet;
    private int _row; 
    private int _column;

    public Reference (int row, int column, Spreadsheet spreadsheet){
        _spreadsheet = spreadsheet;
        _row = row;
        _column = column;
    }

    public Reference () {

    }

    public Spreadsheet getSpreadsheet(){
        return _spreadsheet;
    }

    Literal value(){
        return _spreadsheet.getCell(_row, _column).value();
    }

    public String toString() {
        return _spreadsheet.getCell(_row, _column).getContent().toString();
    }

}
