import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Acceuil extends JFrame {

        class check implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        String client = NomAbonné.getSelectedItem().toString();
                        String livre = TitreLivre.getSelectedItem().toString();
                        if (!(client.equals(NomAbonné.getItemAt(0).toString()))
                                        && !(livre.equals(TitreLivre.getItemAt(0).toString()))) {

                                String Requete = "select * from acceuil where (NomPrenom = '" + client
                                                + "') and (titre = '" + livre + "')";
                                init();

                                RemplirRecherche(Requete);

                        } else if (!(client.equals(NomAbonné.getItemAt(0).toString()))
                                        && (livre.equals(TitreLivre.getItemAt(0).toString()))) {

                                String Requete = "select * from acceuil where (NomPrenom = '" + client
                                                + "') ";
                                init();

                                RemplirRecherche(Requete);

                        } else if ((client.equals(NomAbonné.getItemAt(0).toString()))
                                        && !(livre.equals(TitreLivre.getItemAt(0).toString()))) {

                                String Requete = "select * from acceuil where (titre = '" + livre + "')";
                                init();

                                RemplirRecherche(Requete);

                        } else {
                                NomAbonné.setSelectedIndex(0);
                                TitreLivre.setSelectedIndex(0);
                                ModelTableRecherche.setRowCount(0);

                        }

                }

        }

        class verifier implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        String requete = "select * from livre where (NBCopie > 0) and (titre='"
                                        + TitreCheck.getSelectedItem().toString() + "')";
                        try {
                                st = bd.BaseDeDonnees().createStatement();
                                rst = st.executeQuery(requete);
                                if (rst.next()) {
                                        init();

                                        JOptionPane.showMessageDialog(null,
                                                        "Le livre " + TitreCheck.getSelectedItem().toString()
                                                                        + " est disponible",
                                                        "Résultat recherche", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                        init();

                                        JOptionPane.showMessageDialog(null,
                                                        "Le livre " + TitreCheck.getSelectedItem().toString()
                                                                        + " n'est pas disponible pour le moment ",
                                                        "Résultat recherche", JOptionPane.ERROR_MESSAGE);
                                }

                        } catch (SQLException ex) {

                        }
                }

        }

        class livre implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        dispose();
                        CreateNewBooks n = new CreateNewBooks();
                        n.setVisible(true);
                        n.setLocationRelativeTo(null);

                }

        }

        class stats implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Statistics();
                }

        }

        class abonnes implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        dispose();
                        CreateNewMembers n = new CreateNewMembers();

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

        public Acceuil() {
                initComponents();
        }

        void init() {
                NomAbonné.setSelectedIndex(0);
                TitreCheck.setSelectedIndex(0);
                TitreLivre.setSelectedIndex(0);
        }

        public void RemplirJComboAbonnes() {
                NomAbonné.removeAllItems();
                int count = 1;
                NomAbonné.insertItemAt("Veuillez choisir le client à verifier", 0);
                NomAbonné.setSelectedIndex(0);
                String requete = "select distinct NomPrenom from abonne";
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                NomAbonné.insertItemAt(rst.getString("NomPrenom"), count);
                                count++;
                        }
                } catch (SQLException ex) {

                }
        }

        public void RemplirJComboTitle() {
                TitreCheck.removeAllItems();
                int count = 1;
                TitreCheck.insertItemAt("Veuillez choisir le livre à verifier", 0);
                TitreCheck.setSelectedIndex(0);
                String requete = "select distinct * from livre";
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                TitreCheck.insertItemAt(rst.getString("titre"), count);
                                count++;
                        }
                } catch (SQLException ex) {

                }
        }

        public void RemplirJComboTitre() {
                TitreLivre.removeAllItems();
                int count = 1;
                TitreLivre.insertItemAt("Veuillez choisir le livre à verifier", 0);
                TitreLivre.setSelectedIndex(0);
                String requete = "select distinct titre from livre";
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                TitreLivre.insertItemAt(rst.getString("titre"), count);
                                count++;
                        }
                } catch (SQLException ex) {

                }
        }

        public void RemplirDispo() {
                ModelTableDispo.setRowCount(0);

                ResultSet rst;
                Statement st;

                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery("select * from livre where NBCopie>0");
                        while (rst.next()) {
                                ModelTableDispo.addRow(new Object[] {
                                                rst.getString("IDLivre"), rst.getString("titre")
                                });

                        }
                } catch (SQLException ex) {

                }
        }

        public void RemplirEmprunt() {
                ModelTableEmprunt.setRowCount(0);

                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery("SELECT * FROM acceuil");
                        while (rst.next()) {
                                ModelTableEmprunt.addRow(new Object[] {
                                                rst.getString("Sortie"), rst.getString("IDLivre"),
                                                rst.getString("titre"), rst.getString("id"), rst.getString("NomPrenom")
                                });

                        }
                } catch (SQLException ex) {

                }
        }

        public void RemplirRecherche(String requete) {
                ModelTableRecherche.setRowCount(0);

                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                ModelTableRecherche.addRow(new Object[] {
                                                rst.getString("Sortie"), rst.getString("IDLivre"),
                                                rst.getString("titre"), rst.getString("id"), rst.getString("NomPrenom")
                                });

                        }
                } catch (SQLException ex) {

                }
        }

        private void initComponents() {

                LivresEmprunt = new JPanel();
                Titre1 = new JLabel();
                RechercheEmprunt = new JPanel();
                NomAbonné = new JComboBox();
                TitreLivre = new JComboBox();
                Recherche1 = new JButton();
                ScrollRecherche = new JScrollPane(TableRecherche);
                ScrollEmprunt = new JScrollPane(TableEmprunt);
                ScrollDispo = new JScrollPane(TableDispo);
                NavigationPanel = new JPanel();
                Emprunt = new JButton();
                Abonnés = new JButton();
                Livres = new JButton();
                Statistiques = new JButton();
                SousTitre1 = new JLabel();
                LivreDispo = new JPanel();
                RechercheDispo = new JPanel();
                TitreCheck = new JComboBox();
                Recherche2 = new JButton();
                Titre2 = new JLabel();
                SousTitre2 = new JLabel();

                setBackground(new Color(255, 255, 255));
                setMaximumSize(new Dimension(850, 740));
                setMinimumSize(new Dimension(850, 740));
                setName("Acceuil");
                setPreferredSize(new Dimension(850, 740));
                setSize(new Dimension(850, 740));
                getContentPane().setLayout(
                                new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

                LivresEmprunt.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
                LivresEmprunt.setPreferredSize(new Dimension(600, 700));

                Titre1.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                Titre1.setForeground(new Color(0, 153, 153));
                Titre1.setHorizontalAlignment(SwingConstants.CENTER);
                Titre1.setText("Livres empruntés");

                RechercheEmprunt.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

                RemplirJComboAbonnes();
                RemplirJComboTitre();
                RemplirJComboTitle();

                Recherche1.setText("Recherche");
                Recherche1.addActionListener(new check());

                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(ModelTableRecherche);
                TableRecherche.setRowSorter(sorter);
                sorter.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));

                TableRecherche.setMaximumSize(new Dimension(100, 100));
                TableRecherche.setMinimumSize(new Dimension(100, 100));
                TableRecherche.setPreferredSize(new Dimension(100, 100));
                ScrollRecherche.setViewportView(TableRecherche);
                if (TableRecherche.getColumnModel().getColumnCount() > 0) {
                        TableRecherche.getColumnModel().getColumn(0).setResizable(false);
                        TableRecherche.getColumnModel().getColumn(0).setPreferredWidth(30);
                        TableRecherche.getColumnModel().getColumn(1).setResizable(false);
                        TableRecherche.getColumnModel().getColumn(1).setPreferredWidth(20);
                        TableRecherche.getColumnModel().getColumn(3).setResizable(false);
                        TableRecherche.getColumnModel().getColumn(3).setPreferredWidth(20);
                }

                GroupLayout RechercheEmpruntLayout = new GroupLayout(RechercheEmprunt);
                RechercheEmprunt.setLayout(RechercheEmpruntLayout);
                RechercheEmpruntLayout.setHorizontalGroup(
                                RechercheEmpruntLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(RechercheEmpruntLayout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addGroup(RechercheEmpruntLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(RechercheEmpruntLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(ScrollRecherche,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                452,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(23,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(RechercheEmpruntLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(NomAbonné,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                157,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(TitreLivre,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                157,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(26, 26, 26)
                                                                                                .addComponent(Recherche1)
                                                                                                .addGap(17, 17, 17)))));
                RechercheEmpruntLayout.setVerticalGroup(
                                RechercheEmpruntLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(RechercheEmpruntLayout.createSequentialGroup()
                                                                .addGap(20, 20, 20)
                                                                .addGroup(RechercheEmpruntLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(NomAbonné,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                32,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(TitreLivre,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                32,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Recherche1))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ScrollRecherche,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                152,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(12, Short.MAX_VALUE)));

                TableRowSorter<DefaultTableModel> sorterEmprunt = new TableRowSorter<DefaultTableModel>(
                                ModelTableEmprunt);
                TableEmprunt.setRowSorter(sorterEmprunt);
                sorterEmprunt.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));

                RemplirEmprunt();

                ScrollEmprunt.setViewportView(TableEmprunt);
                if (TableEmprunt.getColumnModel().getColumnCount() > 0) {
                        TableEmprunt.getColumnModel().getColumn(0).setResizable(false);
                        TableEmprunt.getColumnModel().getColumn(0).setPreferredWidth(30);
                        TableEmprunt.getColumnModel().getColumn(1).setResizable(false);
                        TableEmprunt.getColumnModel().getColumn(1).setPreferredWidth(25);
                        TableEmprunt.getColumnModel().getColumn(3).setResizable(false);
                        TableEmprunt.getColumnModel().getColumn(3).setPreferredWidth(25);
                }

                NavigationPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
                NavigationPanel.setMaximumSize(new Dimension(428, 58));
                NavigationPanel.setMinimumSize(new Dimension(428, 58));

                Emprunt.setText("Emprunt");
                Emprunt.addActionListener(new emprunt());

                Abonnés.setText("Abonnés");
                Abonnés.addActionListener(new abonnes());

                Livres.setText("Livres");
                Livres.addActionListener(new livre());

                Statistiques.setText("Historique");
                Statistiques.addActionListener(new stats());

                GroupLayout NavigationPanelLayout = new GroupLayout(NavigationPanel);
                NavigationPanel.setLayout(NavigationPanelLayout);
                NavigationPanelLayout.setHorizontalGroup(
                                NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(NavigationPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(Emprunt)
                                                                .addGap(33, 33, 33)
                                                                .addComponent(Abonnés)
                                                                .addGap(33, 33, 33)
                                                                .addComponent(Livres)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                33,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(Statistiques)
                                                                .addContainerGap()));
                NavigationPanelLayout.setVerticalGroup(
                                NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(NavigationPanelLayout.createSequentialGroup()
                                                                .addGap(14, 14, 14)
                                                                .addGroup(NavigationPanelLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Emprunt)
                                                                                .addGroup(NavigationPanelLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(Livres)
                                                                                                .addComponent(Statistiques)
                                                                                                .addComponent(Abonnés)))
                                                                .addContainerGap(19, Short.MAX_VALUE)));

                SousTitre1.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                SousTitre1.setForeground(new Color(255, 102, 51));
                SousTitre1.setHorizontalAlignment(SwingConstants.CENTER);
                SousTitre1.setText("Recherche");

                GroupLayout LivresEmpruntLayout = new GroupLayout(LivresEmprunt);
                LivresEmprunt.setLayout(LivresEmpruntLayout);
                LivresEmpruntLayout.setHorizontalGroup(
                                LivresEmpruntLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(LivresEmpruntLayout.createSequentialGroup()
                                                                .addGap(200, 200, 200)
                                                                .addComponent(Titre1,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                159,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                LivresEmpruntLayout.createSequentialGroup()
                                                                                .addContainerGap(49, Short.MAX_VALUE)
                                                                                .addGroup(LivresEmpruntLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(SousTitre1,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                91,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(ScrollEmprunt,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                495,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(RechercheEmprunt,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(49, 49, 49))
                                                .addGroup(LivresEmpruntLayout.createSequentialGroup()
                                                                .addGap(85, 85, 85)
                                                                .addComponent(NavigationPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                LivresEmpruntLayout.setVerticalGroup(
                                LivresEmpruntLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(LivresEmpruntLayout.createSequentialGroup()
                                                                .addGap(22, 22, 22)
                                                                .addComponent(Titre1,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                41,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(SousTitre1,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                28,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(RechercheEmprunt,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ScrollEmprunt,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                220,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(NavigationPanel,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(22, 22, 22)));

                getContentPane().add(LivresEmprunt);

                RechercheDispo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

                Recherche2.setText("Recherche");
                Recherche2.addActionListener(new verifier());

                GroupLayout RechercheDispoLayout = new GroupLayout(RechercheDispo);
                RechercheDispo.setLayout(RechercheDispoLayout);
                RechercheDispoLayout.setHorizontalGroup(
                                RechercheDispoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(RechercheDispoLayout.createSequentialGroup()
                                                                .addGroup(RechercheDispoLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(RechercheDispoLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(31, 31, 31)
                                                                                                .addComponent(TitreCheck,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                129,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(RechercheDispoLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(58, 58, 58)
                                                                                                .addComponent(Recherche2)))
                                                                .addContainerGap(34, Short.MAX_VALUE)));
                RechercheDispoLayout.setVerticalGroup(
                                RechercheDispoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(RechercheDispoLayout.createSequentialGroup()
                                                                .addGap(26, 26, 26)
                                                                .addComponent(TitreCheck,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                32,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(26, 26, 26)
                                                                .addComponent(Recherche2)
                                                                .addContainerGap(28, Short.MAX_VALUE)));

                TableRowSorter<DefaultTableModel> sorterDispo = new TableRowSorter<DefaultTableModel>(
                                ModelTableDispo);
                TableDispo.setRowSorter(sorterDispo);
                sorterDispo.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(1, SortOrder.ASCENDING)));

                RemplirDispo();

                ScrollDispo.setViewportView(TableDispo);
                if (TableDispo.getColumnModel().getColumnCount() > 0) {
                        TableDispo.getColumnModel().getColumn(0).setResizable(false);
                        TableDispo.getColumnModel().getColumn(0).setPreferredWidth(25);
                }

                Titre2.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                Titre2.setForeground(new Color(0, 153, 153));
                Titre2.setHorizontalAlignment(SwingConstants.CENTER);
                Titre2.setText("Livres disponibles");

                SousTitre2.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
                SousTitre2.setForeground(new Color(255, 102, 51));
                SousTitre2.setHorizontalAlignment(SwingConstants.CENTER);
                SousTitre2.setText("Recherche");

                GroupLayout LivreDispoLayout = new GroupLayout(LivreDispo);
                LivreDispo.setLayout(LivreDispoLayout);
                LivreDispoLayout.setHorizontalGroup(
                                LivreDispoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, LivreDispoLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap(34, Short.MAX_VALUE)
                                                                .addGroup(LivreDispoLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(ScrollDispo,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                196,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(LivreDispoLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                LivreDispoLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(Titre2,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                159,
                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(43, 43, 43))
                                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                                LivreDispoLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(LivreDispoLayout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(RechercheDispo,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(SousTitre2,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                91,
                                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                                                .addGap(20, 20, 20))))));
                LivreDispoLayout.setVerticalGroup(
                                LivreDispoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(LivreDispoLayout.createSequentialGroup()
                                                                .addGap(27, 27, 27)
                                                                .addComponent(Titre2,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                41,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(26, 26, 26)
                                                                .addComponent(SousTitre2,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                28,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(RechercheDispo,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(ScrollDispo,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                388, Short.MAX_VALUE)
                                                                .addGap(24, 24, 24)));

                getContentPane().add(LivreDispo);

                pack();
        }

        ResultSet rst;
        Statement st;
        private Connecting bd = new Connecting();

        private JButton Abonnés;
        private JButton Emprunt;
        private JPanel LivreDispo;
        private JButton Livres;
        private JPanel LivresEmprunt;
        private JPanel NavigationPanel;
        private JComboBox NomAbonné;
        private JButton Recherche1;
        private JButton Recherche2;
        private JPanel RechercheDispo;
        private JPanel RechercheEmprunt;
        private JScrollPane ScrollDispo;
        private JScrollPane ScrollEmprunt;
        private JScrollPane ScrollRecherche;
        private JLabel SousTitre1;
        private JLabel SousTitre2;
        private JButton Statistiques;
        private JLabel Titre1;
        private JLabel Titre2;
        private JComboBox TitreCheck;
        private JComboBox TitreLivre;

        String[] columnsRecherche = { "<HTML><center>Date <BR>d'emprunt</center></HTML>",
                        "<HTML><center>Identifiant<BR>Livre</center></HTML>",
                        "<HTML><center>Titre <BR>du livre</center></HTML>",
                        "<HTML><center>Identifiant<BR> abonné</center></HTML>",
                        "<HTML><center>Nom et Prenom <BR>abonné</center></HTML>" };

        DefaultTableModel ModelTableRecherche = new DefaultTableModel(columnsRecherche, 0);
        private JTable TableRecherche = new JTable(ModelTableRecherche);

        String[] columnsEmprunt = {
                        "<HTML><center>Date <BR>d'emprunt</center></HTML>",
                        "<HTML><center>Identifiant<BR>Livre</center></HTML>",
                        "<HTML><center>Titre <BR>du livre</center></HTML>",
                        "<HTML><center>Identifiant<BR> abonné</center></HTML>",
                        "<HTML><center>Nom et Prenom <BR>abonné</center></HTML>" };

        DefaultTableModel ModelTableEmprunt = new DefaultTableModel(columnsEmprunt, 0);
        private JTable TableEmprunt = new JTable(ModelTableEmprunt);

        String[] columnDispo = { "<HTML><center>Identifiant<BR>Livre</center></HTML>", "Titre du livre" };
        DefaultTableModel ModelTableDispo = new DefaultTableModel(columnDispo, 0);
        private JTable TableDispo = new JTable(ModelTableDispo);
}
