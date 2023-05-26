
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Open extends JFrame {

        public Open() {
                initComponents();
        }

        private void initComponents() {

                jPanel2 = new JPanel();
                jPanel1 = new JPanel();
                Password = new JPasswordField();
                jLabel2 = new JLabel();
                Sign = new JButton();
                username = new JTextField();
                jLabel3 = new JLabel();
                jLabel4 = new JLabel();
                jLabel5 = new JLabel();
                jLabel1 = new JLabel();

                setTitle("Sign in");
                setBackground(new Color(255, 255, 255));
                setBounds(new Rectangle(0, 0, 0, 0));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setMaximumSize(new Dimension(635, 719));
                setPreferredSize(new Dimension(635, 719));
                setSize(new Dimension(635, 719));
                getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

                jPanel2.setBackground(new Color(255, 255, 255));
                jPanel2.setMaximumSize(null);
                jPanel2.setMinimumSize(null);
                jPanel2.setPreferredSize(new Dimension(693, 719));
                jPanel2.setLayout(null);

                jPanel1.setBackground(new Color(18, 48, 170));
                jPanel1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                jPanel1.setLayout(null);
                jPanel1.add(Password);
                Password.setBounds(160, 190, 170, 30);

                jLabel2.setFont(new Font("Segoe UI", 1, 14));
                jLabel2.setForeground(new Color(255, 162, 18));
                jLabel2.setText("Mot de passe");
                jPanel1.add(jLabel2);
                jLabel2.setBounds(30, 190, 110, 30);

                Sign.setFont(new Font("Segoe UI", 1, 12));
                Sign.setText("Connecter");
                Sign.setBorderPainted(false);
                Sign.addActionListener(new ListenSign());
                jPanel1.add(Sign);
                Sign.setBounds(130, 270, 100, 30);

                jPanel1.add(username);
                username.setBounds(160, 140, 170, 30);

                jLabel3.setFont(new Font("Segoe UI", 1, 14));
                jLabel3.setForeground(new Color(255, 162, 18));
                jLabel3.setText("Username");
                jPanel1.add(jLabel3);
                jLabel3.setBounds(30, 140, 89, 30);

                jLabel4.setIcon(new ImageIcon(getClass().getResource("/icons/user (3).png")));
                jPanel1.add(jLabel4);
                jLabel4.setBounds(70, 20, 70, 90);

                jLabel5.setIcon(new ImageIcon(getClass().getResource("/icons/stack-of-books.png")));
                jPanel1.add(jLabel5);
                jLabel5.setBounds(230, 30, 78, 64);

                jPanel2.add(jPanel1);
                jPanel1.setBounds(130, 200, 355, 340);

                jLabel1.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                jLabel1.setForeground(new Color(204, 0, 0));
                jLabel1.setText("<HTML><center>Bienvenue dans votre logiciel de gestion de bibliotheque, <BR>Veuillez entrer votre username ainsi que votre mot de passe !</center></HTML>");
                jLabel1.setToolTipText("");
                jPanel2.add(jLabel1);
                jLabel1.setBounds(50, 50, 530, 82);

                getContentPane().add(jPanel2);

                pack();
        }

        class ListenSign implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {

                        String t1 = username.getText();
                        String t2 = String.valueOf(Password.getPassword());

                        if (!t1.equals(user) || !t2.equals(mdp)) {
                                toast t = new toast("Username ou mot de passe incorrect, Verifiez ! ", 620, 700);
                                t.showtoast();
                        } else {
                                toast t = new toast("Bienvenue ! ", 700, 700);
                                t.showtoast();

                                dispose();
                                Acceuil n = new Acceuil();
                                n.setVisible(true);
                                n.setLocationRelativeTo(null);
                        }
                }

        }

        private JPasswordField Password;
        private JButton Sign;
        private JLabel jLabel1;
        private JLabel jLabel2;
        private JLabel jLabel3;
        private JLabel jLabel4;
        private JLabel jLabel5;
        private JPanel jPanel1;
        private JPanel jPanel2;
        private JTextField username;
        private String user = "admin";
        private String mdp = "admin";
}
