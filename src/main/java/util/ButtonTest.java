package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author /u/Philboyd_Studge on 2/24/2017.
 */
public class ButtonTest extends JFrame {
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Enter text here:");
    JTextField text = new JTextField();
    JButton button = new JButton("Reverse");
    JMenuBar menuBar = new JMenuBar();
    JMenu menuEdit = new JMenu("Edit");
    JMenuItem itemClear = new JMenuItem("Clear");

    public ButtonTest() {
        super("Press My Button");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.add(label);
        panel.add(text);
        panel.add(button);
        button.addActionListener((e) -> {
            if ((e.getModifiers() & ActionEvent.CTRL_MASK) > 0){
                text.setText(null);
            }
            String result = text.getText();
            result = new StringBuilder(result).reverse().toString();
            text.setText(result);
        });
        this.menuBar.add(menuEdit);
        this.menuEdit.add(itemClear);
        itemClear.addActionListener(e -> text.setText(null));
        this.setJMenuBar(menuBar);
        this.setSize(400,150);
        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // center window on screen
        this.setVisible(true);

    }

    public static void main(String[] args) {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(ButtonTest::new);
    }
}
