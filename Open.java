
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Open {
    private JButton Sign;
    private JPasswordField password;
    private JTextField username;

    private String mdp = "admin";
    private String user = "admin";

    JFrame firstFrame = new JFrame("Gestion Bibliotheque");
    JPanel MainPanel = new JPanel();

    Open() {
        firstFrame.setSize(750, 750);
        firstFrame.setLocationRelativeTo(null);

        MainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        MainPanel.add(new JLabel("Bonjour, Veuillez Connecter !"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 100;
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        MainPanel.add(username = new JTextField(), gbc);

        username.setUI(new hint("Entrer votre nom d'utilisateur", true));
        username.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 0;
        gbc.gridy = 200;
        gbc.insets = new Insets(0, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        MainPanel.add(password = new JPasswordField(""), gbc);

        password.setUI(new hint("Entrer votre mot de passe", true));
        password.setPreferredSize(new Dimension(200, 30));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 300;
        gbc.insets = new Insets(0, 10, 10, 10);

        MainPanel.add(Sign = new JButton("Connecter"), gbc);
        Sign.addActionListener(new ListenSign());

        firstFrame.add(MainPanel);
        firstFrame.setVisible(true);
    }

    class ListenSign implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String t1 = username.getText();
            String t2 = String.valueOf(password.getPassword());

            if (!t1.equals(user) || !t2.equals(mdp)) {
                toast t = new toast("Username ou mot de passe incorrect, Verifiez ! ", 700, 700);
                t.showtoast();
            } else {
                toast t = new toast("Bienvenue ! ", 700, 700);
                t.showtoast();

                firstFrame.dispose();
                ;
                new Acceuil().setVisible(true);

            }
        }

    }
}
