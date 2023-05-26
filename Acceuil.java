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
                        Statistics n = new Statistics();
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

                jPanel1 = new JPanel();
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
                LivreDispo = new JPanel();
                RechercheDispo = new JPanel();
                TitreCheck = new JComboBox();
                Recherche2 = new JButton();
                Titre2 = new JLabel();

                setTitle("Acceuil");
                setMaximumSize(new Dimension(1002, 770));
                setMinimumSize(new Dimension(1002, 770));
                setSize(new Dimension(1002, 770));

                jPanel1.setBorder(BorderFactory.createEtchedBorder());

                Titre1.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                Titre1.setText("Livres empruntés");

                RechercheEmprunt.setBorder(BorderFactory.createEtchedBorder());

                RemplirJComboAbonnes();
                RemplirJComboTitre();
                RemplirJComboTitle();

                Recherche1.setText("Rechercher");
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
                        TableRecherche.getColumnModel().getColumn(0).setPreferredWidth(20);
                        TableRecherche.getColumnModel().getColumn(1).setResizable(false);
                        TableRecherche.getColumnModel().getColumn(1).setPreferredWidth(20);
                        TableRecherche.getColumnModel().getColumn(3).setResizable(false);
                        TableRecherche.getColumnModel().getColumn(3).setPreferredWidth(20);
                }

                GroupLayout RechercheEmpruntLayout = new GroupLayout(RechercheEmprunt);
                RechercheEmprunt.setLayout(RechercheEmpruntLayout);
                RechercheEmpruntLayout.setHorizontalGroup(
                                RechercheEmpruntLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                RechercheEmpruntLayout.createSequentialGroup()
                                                                                .addContainerGap(16, Short.MAX_VALUE)
                                                                                .addGroup(RechercheEmpruntLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addGroup(RechercheEmpruntLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(ScrollRecherche,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                553,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(25, 25, 25))
                                                                                                .addGroup(RechercheEmpruntLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(NomAbonné,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                195,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(28, 28, 28)
                                                                                                                .addComponent(TitreLivre,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                195,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addComponent(Recherche1,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(37, 37, 37)))));
                RechercheEmpruntLayout.setVerticalGroup(
                                RechercheEmpruntLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                RechercheEmpruntLayout.createSequentialGroup()
                                                                                .addGap(22, 22, 22)
                                                                                .addGroup(RechercheEmpruntLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(TitreLivre,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                38,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(Recherche1,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(NomAbonné,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                38,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(
                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                22, Short.MAX_VALUE)
                                                                                .addComponent(ScrollRecherche,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                199,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(14, 14, 14)));

                TableRowSorter<DefaultTableModel> sorterEmprunt = new TableRowSorter<DefaultTableModel>(
                                ModelTableEmprunt);
                TableEmprunt.setRowSorter(sorterEmprunt);
                sorterEmprunt.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING)));

                RemplirEmprunt();

                ScrollEmprunt.setViewportView(TableEmprunt);
                if (TableEmprunt.getColumnModel().getColumnCount() > 0) {
                        TableEmprunt.getColumnModel().getColumn(0).setResizable(false);
                        TableEmprunt.getColumnModel().getColumn(0).setPreferredWidth(20);
                        TableEmprunt.getColumnModel().getColumn(1).setResizable(false);
                        TableEmprunt.getColumnModel().getColumn(1).setPreferredWidth(20);
                        TableEmprunt.getColumnModel().getColumn(3).setResizable(false);
                        TableEmprunt.getColumnModel().getColumn(3).setPreferredWidth(25);
                }
                GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(240, 240, 240)
                                                                                                .addComponent(Titre1,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                181,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(16, 16, 16)
                                                                                                .addComponent(RechercheEmprunt,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(24, 24, 24)
                                                                                                .addComponent(ScrollEmprunt,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                581,
                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(18, Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(Titre1)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(RechercheEmprunt,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(ScrollEmprunt,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                217,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(18, Short.MAX_VALUE)));

                NavigationPanel.setBorder(BorderFactory.createEtchedBorder());

                Livres.setText("Livres");
                Livres.setMaximumSize(new Dimension(92, 31));
                Livres.setMinimumSize(new Dimension(92, 31));
                Livres.setPreferredSize(new Dimension(92, 31));

                Emprunt.setText("Emprunt");
                Emprunt.setMaximumSize(new Dimension(92, 31));
                Emprunt.setMinimumSize(new Dimension(92, 31));
                Emprunt.setPreferredSize(new Dimension(92, 31));

                Statistiques.setText("Historique");
                Statistiques.setMaximumSize(new Dimension(92, 31));
                Statistiques.setMinimumSize(new Dimension(92, 31));
                Statistiques.setPreferredSize(new Dimension(92, 31));

                Abonnés.setText("Abonnés");
                Abonnés.setMaximumSize(new Dimension(92, 31));
                Abonnés.setMinimumSize(new Dimension(92, 31));
                Abonnés.setPreferredSize(new Dimension(92, 31));

                Statistiques.addActionListener(new stats());
                Livres.addActionListener(new livre());
                Abonnés.addActionListener(new abonnes());
                Emprunt.addActionListener(new emprunt());

                GroupLayout NavigationPanelLayout = new GroupLayout(NavigationPanel);
                NavigationPanel.setLayout(NavigationPanelLayout);
                NavigationPanelLayout.setHorizontalGroup(
                                NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(NavigationPanelLayout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(Livres,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                36, Short.MAX_VALUE)
                                                                .addComponent(Emprunt,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(29, 29, 29)
                                                                .addComponent(Abonnés,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(Statistiques,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)));
                NavigationPanelLayout.setVerticalGroup(
                                NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(NavigationPanelLayout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addGroup(NavigationPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Emprunt,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Livres,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Statistiques,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Abonnés,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(24, Short.MAX_VALUE)));

                LivreDispo.setBorder(BorderFactory.createEtchedBorder());

                Titre2.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
                Titre2.setText("Livres disponibles");

                RechercheDispo.setBorder(BorderFactory.createEtchedBorder());

                Recherche2.setText("Vérifier");
                Recherche2.setMaximumSize(new Dimension(92, 31));
                Recherche2.setMinimumSize(new Dimension(92, 31));
                Recherche2.setPreferredSize(new Dimension(92, 31));
                Recherche2.addActionListener(new verifier());

                GroupLayout RechercheDispoLayout = new GroupLayout(RechercheDispo);
                RechercheDispo.setLayout(RechercheDispoLayout);
                RechercheDispoLayout.setHorizontalGroup(
                                RechercheDispoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(RechercheDispoLayout.createSequentialGroup()
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(RechercheDispoLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                RechercheDispoLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(TitreCheck,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                244,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(35, 35, 35))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                RechercheDispoLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(Recherche2,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(109, 109,
                                                                                                                                109)))));
                RechercheDispoLayout.setVerticalGroup(
                                RechercheDispoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                RechercheDispoLayout.createSequentialGroup()
                                                                                .addGap(30, 30, 30)
                                                                                .addComponent(TitreCheck,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                38,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(Recherche2,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addContainerGap(26, Short.MAX_VALUE)));

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

                GroupLayout LivreDispoLayout = new GroupLayout(LivreDispo);
                LivreDispo.setLayout(LivreDispoLayout);
                LivreDispoLayout.setHorizontalGroup(
                                LivreDispoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(LivreDispoLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(LivreDispoLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                LivreDispoLayout.createSequentialGroup()
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                .addComponent(Titre2,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                181,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(47, 47, 47))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                LivreDispoLayout.createSequentialGroup()
                                                                                                                .addComponent(RechercheDispo,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addContainerGap())))
                                                .addGroup(GroupLayout.Alignment.TRAILING, LivreDispoLayout
                                                                .createSequentialGroup()
                                                                .addContainerGap(23, Short.MAX_VALUE)
                                                                .addComponent(ScrollDispo,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                288,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(21, 21, 21)));
                LivreDispoLayout.setVerticalGroup(
                                LivreDispoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(LivreDispoLayout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addComponent(Titre2)
                                                                .addGap(38, 38, 38)
                                                                .addComponent(RechercheDispo,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(ScrollDispo,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                360,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                GroupLayout layout = new GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jPanel1,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(LivreDispo,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(229, 229, 229)
                                                                .addComponent(NavigationPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(LivreDispo,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jPanel1,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(NavigationPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(16, Short.MAX_VALUE)));

                pack();
        }

        ResultSet rst;
        Statement st;
        private Connecting bd = new Connecting();

        private JButton Abonnés;
        private JButton Emprunt;
        private JPanel LivreDispo;
        private JButton Livres;
        private JPanel jPanel1;
        private JPanel NavigationPanel;
        private JComboBox NomAbonné;
        private JButton Recherche1;
        private JButton Recherche2;
        private JPanel RechercheDispo;
        private JPanel RechercheEmprunt;
        private JScrollPane ScrollDispo;
        private JScrollPane ScrollEmprunt;
        private JScrollPane ScrollRecherche;
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
