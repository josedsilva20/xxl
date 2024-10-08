package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;
import java.lang.String;

import xxl.core.exception.InvalidCellCoordinatesException;
import xxl.core.exception.InvalidFunctionException;
import xxl.core.exception.UnrecognizedEntryException;


class Parser {

  private Spreadsheet _spreadsheet;
  
  Parser() {
  }

  Parser(Spreadsheet spreadsheet) {
    _spreadsheet = spreadsheet;
  }

  Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException, InvalidCellCoordinatesException, InvalidFunctionException{
     try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      parseDimensions(reader);

      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }

    return _spreadsheet;
  }

  private void parseDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException{
    int rows = -1;
    int columns = -1;
    
    for (int i = 0; i < 2; i++) {
      String[] dimension = reader.readLine().split("=");
      if (dimension[0].equals("linhas"))
        rows = Integer.parseInt(dimension[1]);
      else if (dimension[0].equals("colunas"))
        columns = Integer.parseInt(dimension[1]);
      else
        throw new UnrecognizedEntryException("" + rows);
    }

    if (rows <= 0 || columns <= 0)
      throw new UnrecognizedEntryException("Dimensões inválidas para a folha");

    _spreadsheet = new Spreadsheet(rows, columns);
  }

  private void parseLine(String line) throws UnrecognizedEntryException, NumberFormatException, InvalidCellCoordinatesException, InvalidFunctionException{
    String[] components = line.split("\\|");

    if (components.length == 1) // do nothing
      return;
    
    if (components.length == 2) {
      String[] address = components[0].split(";");
      Content content = parseContent(components[1]);
      _spreadsheet.insertContent(Integer.parseInt(address[0]), Integer.parseInt(address[1]), content);
    } else
      throw new UnrecognizedEntryException("Wrong format in line: " + line);
  }

  // parse the begining of an expression
  Content parseContent(String contentSpecification) throws UnrecognizedEntryException, InvalidFunctionException, InvalidCellCoordinatesException{
    char c = contentSpecification.charAt(0);

    if (c == '=')
      return parseContentExpression(contentSpecification.substring(1));
    else
      return parseLiteral(contentSpecification);
  }

  private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
    if (literalExpression.charAt(0) == '\'')
      return new LiteralString(literalExpression);
    else {
      try {
        int val = Integer.parseInt(literalExpression);
        return new LiteralInteger(val);
      } catch (NumberFormatException nfe) {
        throw new UnrecognizedEntryException("Número inválido: " + literalExpression);
      }
    }
  }

  // contentSpecification is what comes after '='
  private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException, InvalidFunctionException, InvalidCellCoordinatesException {
    if (contentSpecification.contains("("))
      return parseFunction(contentSpecification);
    // It is a reference
    String[] address = contentSpecification.split(";");
    return new Reference (Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]));
  }

  private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException, InvalidFunctionException, InvalidCellCoordinatesException {
    String[] components = functionSpecification.split("[()]");
    if (components[1].contains(","))
      return parseBinaryFunction(components[0], components[1]);
        
    return parseIntervalFunction(components[0], components[1]);
  }

  private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException, InvalidFunctionException, InvalidCellCoordinatesException {
    String[] arguments = args.split(",");
    Content arg0 = parseArgumentExpression(arguments[0]);
    Content arg1 = parseArgumentExpression(arguments[1]);
    Content[] _args = {parseArgumentExpression(arguments[0]), parseArgumentExpression(arguments[1])};
    
    return switch (functionName) {
      case "ADD" -> new Add (_args);
      case "SUB" -> new Sub (_args);
      case "MUL" -> new Mul (_args);
      case "DIV" -> new Div (_args);
      default -> throw new InvalidFunctionException(functionName)  ;
    };
  }

  private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException{
    if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
      String[] address = argExpression.split(";");
      return new Reference(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]));
      // pode ser diferente do anterior em parseContentExpression
    } else
      return parseLiteral(argExpression);
  }

  private Content parseIntervalFunction(String functionName, String rangeDescription)
    throws UnrecognizedEntryException, NumberFormatException, InvalidFunctionException {
    Range range = _spreadsheet.buildRange(rangeDescription);
    return switch (functionName) {
      case "CONCAT" -> new Concat (range); 
      case "COASLECE" -> new Coalesce (range);
      case "PRODUCT" -> new Product (range);
      case "AVERAGE" -> new Average (range);
      default -> throw new InvalidFunctionException(functionName);
    };
  }
}


  /* Na classe Spreadsheet preciso de algo com a seguinte funcionalidade
  Range createRange(String range) throws ? {
    String[] rangeCoordinates;
    int firstRow, firstColumn, lastRow, lastColumn;
    
    if (range.indexOf(':') != -1) {
      rangeCoordinates = range.split("[:;]");
      firstRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = Integer.parseInt(rangeCoordinates[1]);
      lastRow = Integer.parseInt(rangeCoordinates[2]);
      lastColumn = Integer.parseInt(rangeCoordinates[3]);
    } else {
      rangeCoordinates = range.split(";");
      firstRow = lastRow = Integer.parseInt(rangeCoordinates[0]);
      firstColumn = lastColumn = Integer.parseInt(rangeCoordinates[1]);
    }

    // check if coordinates are valid
    // if yes
    return new Range with firstRow, firstColumn, lastRow, lastColumn and spreadsheet;
  }
  */
  

