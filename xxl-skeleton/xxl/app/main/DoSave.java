package xxl.app.main;

import java.io.IOException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.*;
import xxl.core.Calculator;
// FIXME import classes
import xxl.core.exception.MissingFileAssociationException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command and create a local Form
    if (!(_receiver.hasFile())) {

      Form request = new Form();
      request.addStringField("answer", Message.newSaveAs());
      String filename = request.parse().stringField("answer");

      try {
        _receiver.saveAs(filename);
      } catch (MissingFileAssociationException mfae) {
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    else{
      try {
        _receiver.save();
      } catch (MissingFileAssociationException mfae) {

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

