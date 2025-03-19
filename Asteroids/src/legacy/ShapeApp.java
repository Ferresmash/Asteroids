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

public class ShapeApp extends JFrame
  {
  private static final long serialVersionUID = 1L;
  private ShapeContainer shapeContainer = new ShapeContainer();
  public ShapeApp()
    {
    createMenue();
    this.add(shapeContainer);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400,400);
    this.setVisible(true);
    }
  
  public void createMenue()
    {
    JMenu menu = new JMenu("Modes");
    createMenuItem(menu, "Insert", e -> shapeContainer.setState(new InsertState()));
    createMenuItem(menu, "Move", e -> shapeContainer.setState(new MoveState()));
    createMenuItem(menu, "Delete", e -> shapeContainer.setState(new DeleteState()));
    createMenuItem(menu, "Mark", e -> shapeContainer.setState(new MarkState()));
    createMenuItem(menu, "Unmark", e -> shapeContainer.setState(new UnmarkState()));
    createMenuItem(menu, "Resize", e -> shapeContainer.setState(new ResizeState()));
    JMenu shapesMenu = new JMenu("Shapes");
    createMenuItem(shapesMenu, "Circle", e -> shapeContainer.setShapeState(new CircleState()));
    createMenuItem(shapesMenu, "Rectangle", e -> shapeContainer.setShapeState(new RectState()));
    JMenu decoratorMenu = new JMenu("Decorations");
    createMenuItem(decoratorMenu, "CrossHair", e -> shapeContainer.setDecoratorState(new CrosshairState()));
    createMenuItem(decoratorMenu, "Fill", e -> shapeContainer.setDecoratorState(new FillState()));
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
    new ShapeApp(); // obs egentligen SwingUtilities ...
    }
  }
