package pt.tecnico.po.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Swing interaction.
 */
public class InteractionUsingSwing extends JFrame implements Interaction {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201608231515L;

  /**
   * Constructor (package).
   */
  public InteractionUsingSwing() {
    super(Constants.MAIN_TITLE);
    setDefaultLookAndFeelDecorated(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);
    setVisible(true);
  }

  /** @see pt.tecnico.po.ui.Interaction#close() */
  @Override
  public void close() {
    dispose();
  }

  /** @see pt.tecnico.po.ui.Interaction#menu(pt.tecnico.po.ui.Menu) */
  @Override
  public void menu(Menu m) {
    int option;
    do {
      SwingPanel menu = new SwingPanel(m);
      add(menu);
      pack();
      menu.await();
      option = menu.option();
      remove(menu);
      if (option == 0)
        break;
      try {
        m.entry(option - 1).perform_command();
      } catch (DialogException oi) {
        message(m.entry(option - 1).title() + ": " + oi, m.title()); //$NON-NLS-1$
      }
    } while (!m.entry(option - 1).isLast());
  }

  /** @see pt.tecnico.po.ui.Interaction#form(pt.tecnico.po.ui.Form) */
  @Override
  public void form(Form f) {
    SwingPanel form = new SwingPanel(f);
    add(form);
    pack();
    do {
      form.await();
    } while (!form.parse());
    remove(form);
  }

  /** @see pt.tecnico.po.ui.Interaction#message(pt.tecnico.po.ui.Display) */
  @Override
  public void message(Display d) {
    message(d.getText(), d.getTitle());
  }

  /**
   * Display a Message.
   * 
   * @param s
   * @param title
   */
  private void message(String s, String title) {
    SwingPanel mesg = new SwingPanel(s, title);
    add(mesg);
    pack();
    mesg.await();
    remove(mesg);
  }

  /**
   * The class manages the body of the window, that can be used to select an
   * option (Command) from a Menu, fill the Input values of a Form, or output a
   * text message
   */
  @SuppressWarnings("nls")
  public class SwingPanel extends JPanel implements ActionListener {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 201608231505L;

    /** The menu option selected */
    private int _opt;

    /** The OK button was pressed or a menu option selected */
    private boolean _end;

    /**
     * Association between user Input instances and Applet's JTextField values
     */
    private Map<Input<?>, JTextField> _ins;

    /** Object lock used to synchronize interface and application's threads */
    private final Object _lock = new Object();

    /**
     * Implements ActionListener is called when a menu option is selected or
     * when th OK button is pressed is Forms or messages.
     * 
     * @param evt
     *          the ActionEvent to be processed
     *
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
      synchronized (_lock) {
        _opt = Integer.parseInt(evt.getActionCommand());
        _end = true;
        _lock.notifyAll();
      }
    }

    /**
     * Sleep the current thread so that other actions can be performed
     * 
     * @param n
     *          time to wait
     */
    private synchronized void sleep(int n) {
      try {
        Thread.sleep(n);
      } catch (InterruptedException ie) {
        System.out.println(ie);
      }
    }

    /**
     * Wait until the OK button is pressed and the <b>_end</b> becomes
     * <b>true</b>
     */
    private synchronized void await() {
      _end = false;
      // while(!_end) { sleep(1); }
      synchronized (_lock) {
        while (!_end)
          try {
            _lock.wait();
          } catch (InterruptedException ie) {
            System.out.println(ie);
          }
      }
    }

    /** @return the select option from a menu */
    private int option() {
      return _opt;
    }

    /**
     * Copy user input values to the respective Input class values
     * 
     * @return parsing success condition
     */
    private boolean parse() {
      boolean ret = true;
      for (Input<?> in : _ins.keySet()) {
        if (in.regex() != null) {
          if (!in.parse(_ins.get(in).getText())) {
            _ins.get(in).setText("");
            ret = false;
          }
        }
      }
      return ret;
    }

    /**
     * Build an SwingPanel to display a Menu
     * 
     * @param m
     *          the Menu to display
     */
    SwingPanel(Menu m) {
      super(new GridLayout(m.size() + 3, 1));
      // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      int i;
      JButton jb;
      int[] key = { KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4, KeyEvent.VK_5,
          KeyEvent.VK_6, KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9 };

      add(new JLabel(m.title(), SwingConstants.CENTER));
      add(new JLabel("")); // não gosto do JSeparator

      for (i = 0; i < m.size(); i++)
        if (m.entry(i).isValid()) {
          add(jb = new JButton((i + 1) + " - " + m.entry(i).title()));
          jb.addActionListener(this);
          jb.setActionCommand("" + (i + 1));
          if (i < 9)
            jb.setMnemonic(key[i]);
        } else
          add(new JLabel((i + 1) + " - " + m.entry(i).title(), SwingConstants.CENTER));
      add(jb = new JButton(Messages.exit()));
      jb.addActionListener(this);
      jb.setActionCommand("0");
      jb.setMnemonic(KeyEvent.VK_0);
    }

    /**
     * Build an SwingPanel to display a Form
     * 
     * @param f
     *          the Form to display
     */
    SwingPanel(Form f) {
      super(new GridLayout(f.entries().size() + 2, 2));
      _ins = new HashMap<Input<?>, JTextField>();
      boolean first = true;

      if (f.title() != null) {
        add(new JLabel(""));
        add(new JLabel(f.title()));
      }
      for (Input<?> in : f.entries()) {
        JLabel l;
        add(l = new JLabel(in.prompt(), SwingConstants.TRAILING));
        if (in.regex() != null) {
          JTextField textField = in.cleared() ? new JTextField(10)
              : new JTextField(in.toString(), 10);
          add(textField);
          l.setLabelFor(textField);
          _ins.put(in, textField);
          if (first) {
            first = false;
            textField.setFocusAccelerator('1');
          }
        } else {
          add(new JLabel(""));
        }
      }
      add(new JLabel(""));
      
      JButton jb = new JButton("OK");
      add(jb);
      jb.addActionListener(this);
      jb.setActionCommand("0");
      jb.setMnemonic(KeyEvent.VK_ENTER);
    }

    /**
     * Build an SwingPanel to display a Message
     * 
     * @param s
     *          the Message to display
     * @param title
     */
    SwingPanel(String s, String title) {
      // setLayout(new GridLayout(2,1));
      setLayout(new BorderLayout());

      if (title != null)
        add(new JLabel(title, SwingConstants.CENTER));

      JTextArea textArea = new JTextArea(5, 20);
      textArea.setEditable(false);
      textArea.append(s);
      // textArea.setFocusAccelerator('1'); // nao editavel => no focus

      JScrollPane scrollPane = new JScrollPane(textArea);
      add(scrollPane, BorderLayout.CENTER);

      JButton jb = new JButton("OK");
      add(jb, BorderLayout.SOUTH);
      jb.addActionListener(this);
      jb.setActionCommand("0");
      jb.setMnemonic(KeyEvent.VK_ENTER);
    }
  }

}
