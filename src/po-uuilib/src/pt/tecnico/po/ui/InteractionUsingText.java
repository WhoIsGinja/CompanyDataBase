package pt.tecnico.po.ui;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import pt.tecnico.po.io.CompositePrintStream;
import pt.tecnico.po.io.RuntimeEOFException;

/**
 * Text interaction (either through the keyboard or files).
 */
public class InteractionUsingText implements Interaction {

  /** Input channel. */
  private BufferedReader _in = new BufferedReader(new InputStreamReader(System.in));;

  /** Output channel. */
  private PrintStream _out = System.out;

  /** Log channel. */
  private PrintStream _log = null;

  /** Copy input to output? */
  private boolean _writeInput;

  /**
   * Constructor (package).
   */
  public InteractionUsingText() {
    String fileName = System.getProperty(PropertyNames.INPUT_CHANNEL);
    if (fileName != null) {
      try {
        _in = new BufferedReader(new FileReader(fileName));
      } catch (FileNotFoundException fn) {
        println(ErrorMessages.inputError(fn));
        fileName = null; // uses the default value
      }
    }

    fileName = System.getProperty(PropertyNames.OUTPUT_CHANNEL);
    if (fileName != null) {
      try {
        PrintStream pr = new PrintStream(new FileOutputStream(fileName));
        if (Boolean.getBoolean(PropertyNames.BOTH_CHANNELS)) {
          CompositePrintStream out = new CompositePrintStream();
          out.add(pr);
          out.add(System.out);
          _out = out;
        } else
          _out = pr;
      } catch (FileNotFoundException fn) {
        println(ErrorMessages.outputError(fn));
        fileName = null; // uses the default value
      }
    }

    fileName = System.getProperty(PropertyNames.LOG_CHANNEL);
    if (fileName != null) {
      try {
        _log = new PrintStream(new FileOutputStream(fileName));
      } catch (FileNotFoundException fn) {
        println(ErrorMessages.logError(fn));
        fileName = null; // uses the default value
      }
    }

    _writeInput = Boolean.getBoolean(PropertyNames.WRITE_INPUT);
  }

  /**
   * Close all I/O channels.
   */
  public void closeDown() {
    if (System.out != _out)
      _out.close();
    try {
      String nome = System.getProperty(PropertyNames.INPUT_CHANNEL);
      if (nome != null)
        _in.close();
    } catch (IOException ioe) {
      println(ErrorMessages.errorClosingInput(ioe));
    }

    if (_log != null)
      _log.close();
  }

  /** @see pt.tecnico.po.ui.Interaction#menu(pt.tecnico.po.ui.Menu) */
  @Override
  public void menu(Menu m) {
    int option = 0, i;

    while (true) {
      println(m.title());
      for (i = 0; i < m.size(); i++)
        if (m.entry(i).isValid())
          println((i + 1) + " - " + m.entry(i).title()); //$NON-NLS-1$
      println(Messages.exit());

      try {
        option = readInteger(Messages.selectAnOption());
        if (option == 0)
          return;

        if (option < 0 || option > i || !m.entry(option - 1).isValid()) {
          println(Messages.invalidOption());
        } else {
          m.entry(option - 1).perform_command();
          if (m.entry(option - 1).isLast())
            return;
        }
      } catch (DialogException oi) {
        println(m.entry(option - 1).title() + ": " + oi); //$NON-NLS-1$
      } catch (EOFException eof) {
        println(ErrorMessages.errorEOF(eof));
        return;
      } catch (IOException ioe) {
        println(ErrorMessages.errorIO(ioe));
      } catch (NumberFormatException nbf) {
        println(ErrorMessages.errorInvalidNumber(nbf));
      } catch (RuntimeEOFException e) {
        // Não devia ser preciso mas há alunos que apanham
        // IOException e não fazem nada.
        println(ErrorMessages.errorREOF(e));
        return;
      }
    }
  }

  /** @see pt.tecnico.po.ui.Interaction#form(pt.tecnico.po.ui.Form) */
  @Override
  public void form(Form form) {
    // if (f.title() != null) println(f.title()); // No title in text mode
    try {
      for (Input<?> in : form.entries()) {
        if (in.regex() != null) {
          while (!in.parse(readString(in.prompt())))
            ;
        } else
          println(in.prompt());
      }
    } catch (EOFException eof) {
      println(ErrorMessages.errorEOF(eof));
      return;
    } catch (IOException ioe) {
      println(ErrorMessages.errorIO(ioe));
    } catch (NumberFormatException nbf) {
      println(ErrorMessages.errorInvalidNumber(nbf));
    } catch (RuntimeEOFException e) {
      // Não devia ser preciso mas há alunos que apanham
      // IOException e não fazem nada.
      println(ErrorMessages.errorREOF(e));
      return;
    }
  }

  /** @see pt.tecnico.po.ui.Interaction#message(pt.tecnico.po.ui.Display) */
  @Override
  public void message(Display display) {
    if (display.getText().length() == 0)
      return;
    if (display.getText().charAt(display.getText().length() - 1) == '\n')
      print(display.getText());
    else
      println(display.getText());
  }

  /** @see pt.tecnico.po.ui.Interaction#setTitle(java.lang.String) */
  @Override
  public void setTitle(String title) {
    // DO NOTHING
  }

  /** @see pt.tecnico.po.ui.Interaction#close() */
  @Override
  public void close() {
    closeDown();
  }

  /**
   * Read an integer number from the input.
   * 
   * @param prompt
   *          a prompt (may be null)
   * @return the number read from the input.
   * @throws IOException
   *           in case of read errors
   */
  private final int readInteger(String prompt) throws IOException {
    while (true) {
      try {
        return Integer.parseInt(readString(prompt));
      } catch (NumberFormatException e) {
        println(ErrorMessages.invalidNumber(e));
      }
    }
  }

  /**
   * Read a string.
   * 
   * @param prompt
   *          a prompt (may be null)
   * @return the string read from the input.
   * @throws IOException
   *           in case of read errors
   * @throws EOFException
   *           in case of end of file errors
   */
  private final String readString(String prompt) throws IOException {
    if (prompt != null)
      _out.print(prompt);
    String str = _in.readLine();
    if (str == null) {
      // infelizmente tem que ser esta excepcao pq ha
      // alunos que a apanham e nao fazem nada
      throw new RuntimeEOFException(ErrorMessages.endOfInput());
    }

    if (_log != null)
      _log.println(str);

    if (_writeInput)
      _out.println(str);

    return str;
  }

  /**
   * Write a string.
   * 
   * @param text
   *          string to write.
   */
  private final void println(String text) {
    _out.println(text);
  }

  /**
   * Write a string.
   *
   * @param text
   *          string to write.
   */
  private final void print(String text) {
    _out.print(text);
  }

}
