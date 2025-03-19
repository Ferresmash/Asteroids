package legacy;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import decoratorState.CrosshairState;
import decoratorState.FillState;
import shapeState.CircleState;
import shapeState.RectState;
import state.DeleteState;
import state.InsertState;
import state.MarkState;
import state.MoveState;
import state.ResizeState;
import state.UnmarkState;

public class AsteroidApp extends JFrame
  {
  private static final long serialVersionUID = 1L;
  private GameContainer gameContainer = new GameContainer();
  public AsteroidApp()
    {
    createMenue();
    this.add(gameContainer);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(700,700);
    this.setVisible(true);
    
    }
  
  public void createMenue()
    {
    JMenu menu = new JMenu("Modes");
    createMenuItem(menu, "Insert", e -> gameContainer.setState(new InsertState()));
    createMenuItem(menu, "Move", e -> gameContainer.setState(new MoveState()));
    createMenuItem(menu, "Delete", e -> gameContainer.setState(new DeleteState()));
    createMenuItem(menu, "Mark", e -> gameContainer.setState(new MarkState()));
    createMenuItem(menu, "Unmark", e -> gameContainer.setState(new UnmarkState()));
    createMenuItem(menu, "Resize", e -> gameContainer.setState(new ResizeState()));
    JMenu shapesMenu = new JMenu("Shapes");
    createMenuItem(shapesMenu, "Circle", e -> gameContainer.setShapeState(new CircleState()));
    createMenuItem(shapesMenu, "Rectangle", e -> gameContainer.setShapeState(new RectState()));
    JMenu decoratorMenu = new JMenu("Decorations");
    createMenuItem(decoratorMenu, "CrossHair", e -> gameContainer.setDecoratorState(new CrosshairState()));
    createMenuItem(decoratorMenu, "Fill", e -> gameContainer.setDecoratorState(new FillState()));
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menu);
    menuBar.add(shapesMenu);
    menuBar.add(decoratorMenu);
    this.setJMenuBar(menuBar);
    }
  
  private void createMenuItem(JMenu menu, String label, ActionListener listener)
    {
    JMenuItem menuItem = new JMenuItem(label);
    menuItem.addActionListener(listener);
    menu.add(menuItem);
    }
  
  public static void main(String args[])
    {
    new AsteroidApp(); // obs egentligen SwingUtilities ...
    }
  }
