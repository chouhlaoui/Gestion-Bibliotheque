import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Statistics extends JFrame {
        class livre implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        dispose();
                        CreateNewBooks n = new CreateNewBooks();
                        n.setVisible(true);
                        n.setLocationRelativeTo(null);

                }

        }

        class Home implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                        dispose();
                        Acceuil n = new Acceuil();
                        n.setVisible(true);
                        n.setLocationRelativeTo(null);
                }

        }

        class abonnes implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        dispose();
                        CreateNewMembers n = new CreateNewMembers();
                        n.setVisible(true);
                        n.setLocationRelativeTo(null);

                }

        }

        class emprunt implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        EmpruntRetour n = new EmpruntRetour();
                        n.setVisible(true);
                        n.setLocationRelativeTo(null);
                }

        }

        public void RemplirJTable1() {
                ModelTable3.setRowCount(0);

                ResultSet rst;
                Statement st;

                String requete = "select * from nondispolivre";
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                ModelTable3.addRow(new Object[] {
                                                rst.getString("titre"), rst.getString("auteur")
                                });

                        }
                } catch (SQLException ex) {

                }
        }

        public void RemplirJTable2() {
                ModelTable2.setRowCount(0);

                ResultSet rst;
                Statement st;

                String requete = "select * from ancienabonne";
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                ModelTable2.addRow(new Object[] { rst.getString("NomPrenom") });

                        }
                } catch (SQLException ex) {

                }
        }

        public Statistics() {
                initComponents();
        }

        private void initComponents() {

                jPanel3 = new JPanel();
                jLabel1 = new JLabel();
                LivreNonDispoScroll = new JScrollPane(LivreNonDispo);
                jPanel4 = new JPanel();
                jLabel2 = new JLabel();
                AncienScroll = new JScrollPane(AncienTable);
                NavigationPanel = new JPanel();
                Livres = new JButton();
                Emprunt = new JButton();
                Historique = new JButton();
                Abonnés = new JButton();

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setBackground(new Color(255, 255, 255));
                setMaximumSize(new Dimension(1002, 790));
                setMinimumSize(new Dimension(1002, 790));
                setPreferredSize(new Dimension(1002, 790));
                setSize(new Dimension(1002, 790));

                jPanel3.setBorder(BorderFactory.createEtchedBorder());

                jLabel1.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                jLabel1.setText("Livres non plus disponibles");

                GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(94, 94, 94)
                                                                                                .addComponent(jLabel1,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                160,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(22, 22, 22)
                                                                                                .addComponent(LivreNonDispoScroll,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                311,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(23, Short.MAX_VALUE)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(22, 22, 22)
                                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 26,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(LivreNonDispoScroll,
                                                                                GroupLayout.PREFERRED_SIZE, 197,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(25, Short.MAX_VALUE)));

                jPanel4.setBorder(BorderFactory.createEtchedBorder());

                jLabel2.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                jLabel2.setText("          Anciens membres");

                GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addGap(94, 94, 94)
                                                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 160,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(24, Short.MAX_VALUE)
                                                                .addComponent(AncienScroll, GroupLayout.PREFERRED_SIZE,
                                                                                311,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(21, 21, 21)));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 26,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(AncienScroll, GroupLayout.PREFERRED_SIZE,
                                                                                211,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(51, 51, 51)));

                NavigationPanel.setBorder(BorderFactory.createEtchedBorder());

                Livres.setText("Livres");
                Livres.setMaximumSize(new Dimension(92, 31));
                Livres.setMinimumSize(new Dimension(92, 31));
                Livres.setPreferredSize(new Dimension(92, 31));
                Livres.addActionListener(new livre());

                Emprunt.setText("Emprunt");
                Emprunt.setMaximumSize(new Dimension(92, 31));
                Emprunt.setMinimumSize(new Dimension(92, 31));
                Emprunt.setPreferredSize(new Dimension(92, 31));
                Emprunt.addActionListener(new emprunt());

                Historique.setText("Acceuil");
                Historique.setMaximumSize(new Dimension(92, 31));
                Historique.setMinimumSize(new Dimension(92, 31));
                Historique.setPreferredSize(new Dimension(92, 31));
                Historique.addActionListener(new Home());

                Abonnés.setText("Abonnés");
                Abonnés.setMaximumSize(new Dimension(92, 31));
                Abonnés.setMinimumSize(new Dimension(92, 31));
                Abonnés.setPreferredSize(new Dimension(92, 31));
                Abonnés.addActionListener(new abonnes());
                RemplirJTable1();
                RemplirJTable2();
                GroupLayout NavigationPanelLayout = new GroupLayout(NavigationPanel);
                NavigationPanel.setLayout(NavigationPanelLayout);
                NavigationPanelLayout.setHorizontalGroup(
                                NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(NavigationPanelLayout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(Livres, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                                36,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(Emprunt, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(29, 29, 29)
                                                                .addComponent(Abonnés, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(Historique, GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)));
                NavigationPanelLayout.setVerticalGroup(
                                NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(NavigationPanelLayout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addGroup(NavigationPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Emprunt,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Livres,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Historique,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Abonnés,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(24, Short.MAX_VALUE)));

                GroupLayout layout = new GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(62, 62, 62)
                                                                                                .addComponent(jPanel3,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(151, 151, 151)
                                                                                                .addComponent(jPanel4,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(223, 223, 223)
                                                                                                .addComponent(NavigationPanel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(69, Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(jPanel3,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel4,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addGap(43, 43, 43)
                                                                .addComponent(NavigationPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(30, Short.MAX_VALUE)));

                pack();
        }

        private JScrollPane AncienScroll;
        private JScrollPane LivreNonDispoScroll;
        private JLabel jLabel1;
        private JLabel jLabel2;
        private JPanel jPanel2;
        private JPanel jPanel3;
        private JPanel jPanel4;
        private JButton Abonnés;
        private JButton Emprunt;
        private JButton Historique;
        private JButton Livres;
        private JPanel NavigationPanel;

        String[] columns2 = { "Nom et Prenom" };
        DefaultTableModel ModelTable2 = new DefaultTableModel(columns2, 0);
        private JTable AncienTable = new JTable(ModelTable2);

        String[] columns3 = { "Titre", "Auteurs" };
        DefaultTableModel ModelTable3 = new DefaultTableModel(columns3, 0);
        private JTable LivreNonDispo = new JTable(ModelTable3);

        public Connecting bd = new Connecting();
        Statement st;
        ResultSet rst;
}
