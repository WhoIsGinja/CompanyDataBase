package pt.tecnico.po.ui;

import static pt.tecnico.po.ui.Dialog.IO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A Form is collection of Input requests. The values are requested to user
 * collectively. The requested can be sequential or random, depending on the
 * implementation of the UserInteraction used.
 */
public class Form {

  /** The graphical driver used to dialog with the user */
  private Dialog _ui;

  /** The Form's title (optional) */
  private String _title;

  /** A list of Input requests */
  private List<Input<?>> _entries = new ArrayList<Input<?>>();

  /** use default values: static IO and no title */
  public Form() {
    this(IO, null);
  }

  /**
   * provide a title and use the static IO
   * 
   * @param title
   *          the title
   */
  public Form(String title) {
    this(IO, title);
  }

  /**
   * Build a Form
   * 
   * @param ui
   *          the dialog driver
   * @param title
   *          the title
   */
  public Form(Dialog ui, String title) {
    _ui = ui;
    _title = title;
  }

  /** @return the title of the Form (can be null) */
  public String title() {
    return _title;
  }

  /** @return a copy of the Input requests of the Form */
  public Collection<Input<?>> entries() {
    return Collections.unmodifiableCollection(_entries);
  }

  /**
   * @param i
   * @return a copy of the Input requests of the Form
   */
  public Input<?> entry(int i) {
    return _entries.get(i);
  }

  /**
   * Append an Input to a Form
   * 
   * @param in
   *          the Input to add
   * @return the inserted input
   */
  public <T> Input<T> add(Input<T> in) {
    _entries.add(in);
    return in;
  }

  /**
   * Easy method for adding input field.
   * 
   * @param label
   * @return field
   */
  public Input<Boolean> addBooleanInput(String label) {
    return add(new InputBoolean(label));
  }

  /**
   * Easy method for adding input field.
   * 
   * @param label
   * @return field
   */
  public Input<String> addStringInput(String label) {
    return add(new InputString(label));
  }

  /**
   * Easy method for adding input field.
   * 
   * @param label
   * @return field
   */
  public Input<Float> addFloatInput(String label) {
    return add(new InputFloat(label));
  }

  /**
   * Easy method for adding input field.
   * 
   * @param label
   * @return field
   */
  public Input<Integer> addIntegerInput(String label) {
    return add(new InputInteger(label));
  }

  /** Request the parsing of the value, clearing previous Input values() */
  public void parse() {
    parse(true);
  }

  /**
   * Request the parsing of the value
   * 
   * @param clear
   *          if true Input values are first cleared and the request otherwise
   *          previous Input values are used as Input values by omission
   */
  public void parse(boolean clear) {
    if (clear)
      for (Input<?> in : _entries)
        in.clear();
    _ui.form(this);
  }

  /**
   * Clear the form.
   */
  public void clear() {
    _entries.clear();
  }

}
