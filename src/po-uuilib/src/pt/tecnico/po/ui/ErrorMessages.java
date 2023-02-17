/** @version $Id: ErrorMessages.java,v 1.1 2016/09/17 17:43:35 david Exp $ */
package pt.tecnico.po.ui;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

import pt.tecnico.po.io.RuntimeEOFException;

/**
 * Messages for internal use. Thus none of the methods is public.
 */
@SuppressWarnings("nls")
final class ErrorMessages {

  /**
   * Message for presenting errors in commands.
   * 
   * @param error
   *            error message.
   * @return message text.
   */
  static final String invalidOperation(String error) {
    return "Operação inválida: " + error;
  }

  /**
   * Message for presenting EOF errors in commands.
   * 
   * @param eof
   *            an <code>EOFException</code>.
   * @return message text.
   */
  static final String errorEOF(EOFException eof) {
    return "Fim de entrada de dados (EOF): " + eof;
  }

  /**
   * Message for presenting IO errors in commands.
   * 
   * @param ioe
   *            an <code>IOException</code>.
   * @return message text.
   */
  static final String errorIO(IOException ioe) {
    return "Problema de I/O: " + ioe;
  }

  /**
   * Message for presenting number reading errors in commands.
   * 
   * @param nfe
   *            a <code>NumberFormatException</code>.
   * @return message text.
   */
  static final String errorInvalidNumber(NumberFormatException nfe) {
    return "Número inválido!";
  }

  /**
   * Message for presenting EOF errors in commands.
   * 
   * @param eof
   *            a <code>RuntimeEOFException</code>.
   * @return message text.
   */
  static final String errorREOF(RuntimeEOFException eof) {
    return "Fim de entrada de dados (R-EOF): " + eof;
  }
  /**
   * Error message for file-not-found errors (input).
   * 
   * @param fnfe
   *          an exception corresponding to a file-not-found problem.
   * @return error message.
   */
  static final String inputError(FileNotFoundException fnfe) {
    return "Erro a colocar a entrada de dados: " + fnfe;
  }

  /**
   * Error message for IO errors (closing input).
   * 
   * @param ioe
   *          an IO exception.
   * @return error message.
   */
  static final String errorClosingInput(IOException ioe) {
    return "Erro a fechar entrada de dados: " + ioe;
  }

  /**
   * Error message for number format errors.
   * 
   * @param nfe
   *          a <code>NumberFormatException</code>.
   * @return error message.
   */
  static final String invalidNumber(NumberFormatException nfe) {
    return "Número inválido!";
  }

  /**
   * Error message for EOF errors.
   * 
   * @return error message.
   */
  static final String endOfInput() {
    return "Fim do fluxo de dados de entrada";
  }

  /**
   * Error message for file-not-found errors (output).
   * 
   * @param fnfe
   *          an exception corresponding to a file-not-found problem.
   * @return error message.
   */
  static final String outputError(FileNotFoundException fnfe) {
    return "Erro a colocar a saída de dados: " + fnfe;
  }

  /**
   * Error message for file-not-found errors (log).
   * 
   * @param fnfe
   *          an exception corresponding to a file-not-found problem.
   * @return error message.
   */
  static final String logError(FileNotFoundException fnfe) {
    return "Erro a especificar o ficheiro de log: " + fnfe;
  }

  /**
   * Constructor.
   */
  private ErrorMessages() {
    // intentionally left empty
  }

}
