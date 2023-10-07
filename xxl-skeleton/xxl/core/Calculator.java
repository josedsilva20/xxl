package xxl.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

import java.io.*;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;


// FIXME import classes

/**
 * @author José da Silva, Tiago Apura
 * Class representing a spreadsheet application.
 */
public class Calculator {
  /** The current spreadsheet. */
  private Spreadsheet _spreadsheet = new Spreadsheet();
  private String _filename = "";
  private List<User> _users;

  
  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be null.
   */
  public final Spreadsheet getSpreadsheet() {
    return _spreadsheet;
  }

  /**
   * 
   * @return the Collection of users of the application
   */
  public List<User> getUsers(){
    return new ArrayList<User>(_users);
  }

  public boolean hasFile() {
    return !"".equals(_filename);
  }


  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException {

    try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filename))) {
      
      Object anObject = objIn.readObject();
      _spreadsheet = (Spreadsheet) anObject;
      _filename = filename;

    } catch (ClassNotFoundException cnfe) {
      throw cnfe;
    } catch (IOException e) {
      throw new UnavailableFileException(filename);
    }
  }


  /**
   * Saves the serialized application's state into the file associated to the current spreadsheet.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current spreadsheet does not have a file.
   * @throws IOException if there is some error while serializing the state of the spreadsheet to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {

    if (!(hasFile()))
      throw new MissingFileAssociationException();

    try (ObjectOutputStream obOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
      obOut.writeObject(_spreadsheet);
    } 
    
    catch (FileNotFoundException fnfe) {
      throw fnfe;
    } 
    
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
  
   /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current spreadsheet does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _filename = filename;
    save();
  }
  
  /**
   * Read text input file and create domain entities..
   * 
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException {
    try {
      _spreadsheet.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  }  
}
