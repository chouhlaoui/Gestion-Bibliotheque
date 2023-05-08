import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Calendar;

import javax.swing.*;

public class EmpruntRetour extends JFrame {

        public EmpruntRetour() {
                initComponents();
        }

        public void RemplirJCombo() {
                EmpruntLivre.removeAllItems();
                int count = 1;
                EmpruntLivre.insertItemAt("Veuillez choisir le livre désiré", 0);
                EmpruntLivre.setSelectedIndex(0);
                String requete = "select distinct titre from livre where NBCopie > 0";
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                EmpruntLivre.insertItemAt(rst.getString("titre"), count);
                                count++;
                        }
                } catch (SQLException ex) {

                }
        }

        public void RemplirRetour() {
                RetourLivre.removeAllItems();
                int count = 1;
                RetourLivre.insertItemAt("Veuillez choisir le livre désiré", 0);
                RetourLivre.setSelectedIndex(0);
                String requete = "select distinct titre from livre inner JOIN emprunt where (LivID = IDLivre) and (Retour is NULL)";
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                RetourLivre.insertItemAt(rst.getString("titre"), count);
                                count++;
                        }
                } catch (SQLException ex) {

                }
        }

        public boolean isConfirmation() {
                return Confirmation;
        }

        public void SetConfirmation(Boolean a) {
                Confirmation = a;
        }

        private void initComponents() {

                TabbedPanel = new JTabbedPane();
                EmpruntPanel = new JPanel();
                jLabel9 = new JLabel();
                jLabel10 = new JLabel();
                EmpruntID = new JTextField();
                EmpruntLivre = new JComboBox();
                jLabel11 = new JLabel();
                jLabel12 = new JLabel();
                EmpruntDatePick = new com.toedter.calendar.JDateChooser();
                EmpruntValider = new JButton();
                RetourPanel = new JPanel();
                jLabel4 = new JLabel();
                jLabel6 = new JLabel();
                RetourID = new JTextField();
                RetourLivre = new JComboBox();
                jLabel7 = new JLabel();
                jLabel8 = new JLabel();
                RetourDatePick = new com.toedter.calendar.JDateChooser();
                RetourValider = new JButton();

                setLocation(500, 250);

                setBackground(new Color(255, 255, 255));
                setMaximumSize(new Dimension(450, 450));
                setMinimumSize(new Dimension(450, 450));
                setName("Emprunt ou Retour des livres"); // NOI18N
                setPreferredSize(new Dimension(450, 450));
                setSize(new Dimension(450, 450));

                addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                                Confirmation = true;
                        }
                });

                EmpruntPanel.setBackground(new Color(255, 255, 255));

                RemplirJCombo();

                jLabel9.setBackground(new Color(255, 255, 255));
                jLabel9.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                jLabel9.setForeground(new Color(0, 153, 153));
                jLabel9.setText("Emprunt des Livres");

                jLabel10.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                jLabel10.setText("Identifiant abonné");

                jLabel11.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                jLabel11.setText("Livre");

                jLabel12.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                jLabel12.setText("Date d'emprunt");

                EmpruntValider.setBackground(new Color(0, 153, 153));
                EmpruntValider.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                EmpruntValider.setForeground(new Color(255, 255, 255));
                EmpruntValider.setText("Valider");
                EmpruntValider.addActionListener(new Emprunt_Valider());

                GroupLayout EmpruntPanelLayout = new GroupLayout(EmpruntPanel);
                EmpruntPanel.setLayout(EmpruntPanelLayout);
                EmpruntPanelLayout.setHorizontalGroup(
                                EmpruntPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, EmpruntPanelLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap(57, Short.MAX_VALUE)
                                                                .addGroup(EmpruntPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(EmpruntPanelLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel12,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                123,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(55, 55, 55)
                                                                                                .addComponent(EmpruntDatePick,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                150,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                EmpruntPanelLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGroup(EmpruntPanelLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                                .addComponent(jLabel10,
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                123,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jLabel11,
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                123,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGap(36, 36, 36)
                                                                                                                .addGroup(EmpruntPanelLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(EmpruntLivre,
                                                                                                                                                0,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(EmpruntID))
                                                                                                                .addGap(49, 49, 49))))
                                                .addGroup(EmpruntPanelLayout.createSequentialGroup()
                                                                .addGap(141, 141, 141)
                                                                .addComponent(jLabel9,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                170,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING, EmpruntPanelLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(EmpruntValider,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                86,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(183, 183, 183)));
                EmpruntPanelLayout.setVerticalGroup(
                                EmpruntPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(EmpruntPanelLayout.createSequentialGroup()
                                                                .addGap(52, 52, 52)
                                                                .addComponent(jLabel9,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                54, Short.MAX_VALUE)
                                                                .addGroup(EmpruntPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel10,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                22,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(EmpruntID))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                46, Short.MAX_VALUE)
                                                                .addGroup(EmpruntPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(EmpruntLivre,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel11,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                22,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                47, Short.MAX_VALUE)
                                                                .addGroup(EmpruntPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel12,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                22,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(EmpruntDatePick,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(45, 45, 45)
                                                                .addComponent(EmpruntValider,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                31,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(59, 59, 59)));

                TabbedPanel.addTab("Emprunt d'un livre", EmpruntPanel);

                RetourPanel.setBackground(new Color(255, 255, 255));

                jLabel4.setBackground(new Color(255, 255, 255));
                jLabel4.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                jLabel4.setForeground(new Color(0, 153, 153));
                jLabel4.setText("Retour des Livres");

                jLabel6.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                jLabel6.setText("Identifiant abonné");

                RemplirRetour();

                jLabel7.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                jLabel7.setText("Livre");

                jLabel8.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                jLabel8.setText("Date du retour");

                RetourValider.setBackground(new Color(0, 153, 153));
                RetourValider.setFont(new Font("Segoe UI", 1, 12)); // NOI18N
                RetourValider.setForeground(new Color(255, 255, 255));
                RetourValider.setText("Valider");
                RetourValider.addActionListener(new Retour_Valider());

                GroupLayout RetourPanelLayout = new GroupLayout(RetourPanel);
                RetourPanel.setLayout(RetourPanelLayout);
                RetourPanelLayout.setHorizontalGroup(
                                RetourPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, RetourPanelLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap(57, Short.MAX_VALUE)
                                                                .addGroup(RetourPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(RetourPanelLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel8,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                123,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(55, 55, 55)
                                                                                                .addComponent(RetourDatePick,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                150,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                RetourPanelLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGroup(RetourPanelLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                                .addComponent(jLabel6,
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                123,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jLabel7,
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                123,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGap(36, 36, 36)
                                                                                                                .addGroup(RetourPanelLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(RetourLivre,
                                                                                                                                                0,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(RetourID))
                                                                                                                .addGap(49, 49, 49))))
                                                .addGroup(RetourPanelLayout.createSequentialGroup()
                                                                .addGap(141, 141, 141)
                                                                .addComponent(jLabel4,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                170,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING, RetourPanelLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(RetourValider,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                86,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(183, 183, 183)));
                RetourPanelLayout.setVerticalGroup(
                                RetourPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(RetourPanelLayout.createSequentialGroup()
                                                                .addGap(52, 52, 52)
                                                                .addComponent(jLabel4,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                54, Short.MAX_VALUE)
                                                                .addGroup(RetourPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel6,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                22,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(RetourID))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                46, Short.MAX_VALUE)
                                                                .addGroup(RetourPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(RetourLivre,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel7,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                22,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                47, Short.MAX_VALUE)
                                                                .addGroup(RetourPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel8,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                22,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(RetourDatePick,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(45, 45, 45)
                                                                .addComponent(RetourValider,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                31,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(59, 59, 59)));

                TabbedPanel.addTab("Retour d'un livre", RetourPanel);

                GroupLayout layout = new GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(TabbedPanel));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(TabbedPanel));

                pack();
        }

        public class Retour_Valider implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        try {
                                int idLu = Integer.parseInt(RetourID.getText());
                                String Livre = RetourLivre.getSelectedItem().toString();
                                final Calendar c = RetourDatePick.getCalendar();

                                if (Livre.equals(RetourLivre.getItemAt(0).toString()) || (c == null)) {
                                        JOptionPane.showMessageDialog(null, "Veuillez remplir tout le formulaire !",
                                                        "Echec",
                                                        JOptionPane.ERROR_MESSAGE);

                                } else {

                                        String Date = c.get(Calendar.DATE) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
                                                        + c.get(Calendar.YEAR);

                                        String requete = "select * from livre where titre = '" + Livre + "' ";
                                        st = bd.BaseDeDonnees().createStatement();
                                        rst = st.executeQuery(requete);

                                        if (rst.next()) {
                                                String lectureBD = rst.getString("IDLivre");
                                                int Livreid = Integer.parseInt(lectureBD);

                                                lectureBD = rst.getString("NBCopie");
                                                int nbcopie = Integer.parseInt(lectureBD) + 1;

                                                requete = "select * from emprunt where (AbID = " + idLu
                                                                + ") and (LivID=" + Livreid + ")";
                                                rst = st.executeQuery(requete);

                                                if (rst.next()) {
                                                        requete = "update emprunt set Retour =STR_TO_DATE('" + Date
                                                                        + "', '%d-%m-%Y') where (AbID = " + idLu
                                                                        + ") and (LivID=" + Livreid + ")";

                                                        Statement s1 = bd.BaseDeDonnees().createStatement();
                                                        int resultat1 = s1.executeUpdate(requete);

                                                        requete = "update livre set NBCopie = " + nbcopie
                                                                        + " where IDLivre = " + Livreid;

                                                        Statement s2 = bd.BaseDeDonnees().createStatement();
                                                        int resultat2 = st.executeUpdate(requete);

                                                        if ((resultat1 < 1) || (resultat2 < 1)) {
                                                                s1.cancel();
                                                                s2.cancel();
                                                                s1.close();
                                                                s2.close();
                                                                JOptionPane.showMessageDialog(null, "Echec !",
                                                                                "Echec",
                                                                                JOptionPane.ERROR_MESSAGE);
                                                        } else {
                                                                s1.close();
                                                                s2.close();
                                                                RemplirJCombo();
                                                                RemplirRetour();
                                                                RetourID.setText(null);
                                                                RetourDatePick.setCalendar(null);
                                                                JOptionPane.showMessageDialog(null,
                                                                                "Merci pour votre fidelité",
                                                                                "Succés",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                Confirmation = true;
                                                        }
                                                } else {
                                                        JOptionPane.showMessageDialog(null,
                                                                        "Veuillez verifier l'identifiant de l'abonné ou le nom du livre !",
                                                                        "Echec",
                                                                        JOptionPane.ERROR_MESSAGE);
                                                }

                                        }

                                }

                        } catch (NumberFormatException en) {
                                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier",
                                                "Entrée Invalide",
                                                JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException ex) {
                        }
                }

        }

        public class Emprunt_Valider implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        try {
                                int idLu = Integer.parseInt(EmpruntID.getText());
                                String Livre = EmpruntLivre.getSelectedItem().toString();
                                final Calendar c = EmpruntDatePick.getCalendar();

                                if (Livre.equals(EmpruntLivre.getItemAt(0).toString()) || (c == null)) {
                                        JOptionPane.showMessageDialog(null, "Veuillez remplir tout le formulaire !",
                                                        "Echec",
                                                        JOptionPane.ERROR_MESSAGE);

                                } else {
                                        String Date = c.get(Calendar.DATE) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
                                                        + c.get(Calendar.YEAR);

                                        String requete = "select * from livre where titre = '" + Livre + "' ";
                                        st = bd.BaseDeDonnees().createStatement();
                                        rst = st.executeQuery(requete);

                                        if (rst.next()) {
                                                String lectureBD = rst.getString("NBCopie");
                                                int nbcopie = Integer.parseInt(lectureBD) - 1;
                                                lectureBD = rst.getString("IDLivre");
                                                int Livreid = Integer.parseInt(lectureBD);

                                                requete = "select * from abonne where id = " + idLu;
                                                rst = st.executeQuery(requete);

                                                if (rst.next()) {
                                                        String TestEmprunt = "select * from emprunt where (AbID ="
                                                                        + idLu
                                                                        + ") and (LivID=" + Livreid
                                                                        + ") and (Retour is null)";
                                                        rst = st.executeQuery(TestEmprunt);
                                                        if (rst.next()) {
                                                                JOptionPane.showMessageDialog(null,
                                                                                "Vous ne pouvez pas prendre ce livre car vous l'avez pris et vous ne l'avez pas encore rendu",
                                                                                "Echec",
                                                                                JOptionPane.ERROR_MESSAGE);
                                                        } else {

                                                                requete = "update livre set NBCopie = " + nbcopie
                                                                                + " where titre = '"
                                                                                + Livre
                                                                                + "'";
                                                                int resultat1 = st.executeUpdate(requete);

                                                                if (resultat1 > 0) {

                                                                        requete = "INSERT INTO emprunt(AbID,LivID,Sortie) VALUES ("
                                                                                        + idLu + ","
                                                                                        + Livreid + ","
                                                                                        + "STR_TO_DATE('" + Date
                                                                                        + "', '%d-%m-%Y'))";

                                                                        Statement st2 = bd.BaseDeDonnees()
                                                                                        .createStatement();
                                                                        int resultat2 = st2.executeUpdate(requete);

                                                                        if (resultat2 > 0) {
                                                                                RemplirJCombo();
                                                                                RemplirRetour();
                                                                                EmpruntID.setText(null);
                                                                                EmpruntDatePick.setCalendar(null);
                                                                                JOptionPane.showMessageDialog(null,
                                                                                                "Bonne Lecture !",
                                                                                                "Succes",
                                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                                st2.close();
                                                                                Confirmation = true;
                                                                        } else {
                                                                                st.cancel();
                                                                                JOptionPane.showMessageDialog(null,
                                                                                                "Echec d'emprunt",
                                                                                                "Echec",
                                                                                                JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                } else {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                        "Problème survenu",
                                                                                        "Echec",
                                                                                        JOptionPane.ERROR_MESSAGE);

                                                                }

                                                        }

                                                } else {
                                                        JOptionPane.showMessageDialog(null,
                                                                        "Identifiant saisi non existant",
                                                                        "Echec",
                                                                        JOptionPane.ERROR_MESSAGE);
                                                }

                                        }

                                }

                        } catch (NumberFormatException en) {
                                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier",
                                                "Entrée Invalide",
                                                JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException ex) {
                        }
                }

        }

        private Boolean Confirmation = false;
        private com.toedter.calendar.JDateChooser EmpruntDatePick;
        private JTextField EmpruntID;
        private JComboBox EmpruntLivre;
        private JPanel EmpruntPanel;
        private JButton EmpruntValider;
        private com.toedter.calendar.JDateChooser RetourDatePick;
        private JTextField RetourID;
        private JComboBox RetourLivre;
        private JPanel RetourPanel;
        private JButton RetourValider;
        private JTabbedPane TabbedPanel;
        private JLabel jLabel10;
        private JLabel jLabel11;
        private JLabel jLabel12;
        private JLabel jLabel4;
        private JLabel jLabel6;
        private JLabel jLabel7;
        private JLabel jLabel8;
        private JLabel jLabel9;
        ResultSet rst;
        Statement st;
        private Connecting bd = new Connecting();
}
