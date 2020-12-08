package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

import woo.app.exceptions.InvalidDateException;                                                                                                           import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.exceptions.NegativeDateException;

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<Storefront> {
  
  Input<Integer> numDays;

  public DoAdvanceDate(Storefront receiver) {
    super(Label.ADVANCE_DATE, receiver);
    
    numDays = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.advanceDate(numDays.value());
     
    } catch (NegativeDateException e) {
      throw new InvalidDateException(numDays.value());
    }
  }
}