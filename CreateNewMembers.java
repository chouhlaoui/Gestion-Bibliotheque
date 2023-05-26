import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CreateNewMembers extends javax.swing.JFrame {

        void remplir() {
                ModelTable.setRowCount(0);
                String requete = "select * from abonne";
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        while (rst.next()) {
                                ModelTable.addRow(new Object[] {
                                                rst.getString("id"), rst.getString("NomPrenom"),
                                                rst.getString("Proffession")
                                });

                        }
                } catch (SQLException ex) {

                }
        }

        public CreateNewMembers() {
                initComponents();
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                Scroll = new JScrollPane(AbonneActuel);
                AbonneActuel = new javax.swing.JTable();
                jPanel2 = new javax.swing.JPanel();
                SearchButton = new javax.swing.JButton();
                DeleteButton = new javax.swing.JButton();
                IDSeDe = new javax.swing.JTextField();
                NavigationPanel = new javax.swing.JPanel();
                Home = new javax.swing.JButton();
                Reservation = new javax.swing.JButton();
                History = new javax.swing.JButton();
                Books = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                Nom = new javax.swing.JTextField();
                ID = new javax.swing.JTextField();
                Profession = new javax.swing.JComboBox<>();
                AddButton = new javax.swing.JButton();
                ModifyButton = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setMaximumSize(new java.awt.Dimension(1002, 770));
                setMinimumSize(new java.awt.Dimension(1002, 770));
                setPreferredSize(new java.awt.Dimension(1002, 770));
                setSize(new java.awt.Dimension(1002, 770));

                jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(ModelTable);
                AbonneActuel.setRowSorter(sorter);
                sorter.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(1, SortOrder.ASCENDING)));

                remplir();

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(Scroll,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                391, Short.MAX_VALUE)
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(Scroll,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                688, Short.MAX_VALUE)
                                                                .addContainerGap()));

                jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                SearchButton.setText("Rechercher");
                SearchButton.setMaximumSize(new java.awt.Dimension(92, 31));
                SearchButton.setMinimumSize(new java.awt.Dimension(92, 31));
                SearchButton.setPreferredSize(new java.awt.Dimension(92, 31));

                DeleteButton.setText("Supprimer");
                DeleteButton.setMaximumSize(new java.awt.Dimension(92, 31));
                DeleteButton.setMinimumSize(new java.awt.Dimension(92, 31));
                DeleteButton.setPreferredSize(new java.awt.Dimension(92, 31));

                IDSeDe.setUI(new hint("Identifiant de l'abonné :", true));
                ID.setUI(new hint("Identifiant de l'abonné :", true));
                Nom.setUI(new hint("Nom et prenom de l'abonné :", true));

                javax.swing.GroupLayout firstLayout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(firstLayout);
                firstLayout.setHorizontalGroup(
                                firstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstLayout
                                                                .createSequentialGroup()
                                                                .addGroup(firstLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(firstLayout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(DeleteButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                115,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(firstLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(75, 75, 75)
                                                                                                .addComponent(IDSeDe,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                169,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                92,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(SearchButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                115,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(31, 31, 31)));
                firstLayout.setVerticalGroup(
                                firstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(firstLayout.createSequentialGroup()
                                                                .addGroup(firstLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(firstLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(54, 54, 54)
                                                                                                .addComponent(SearchButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(firstLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(84, 84, 84)
                                                                                                .addComponent(IDSeDe,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(10, 10, 10)
                                                                .addComponent(DeleteButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(54, Short.MAX_VALUE)));

                NavigationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                Profession.insertItemAt("Choisir Le profession : ", 0);
                Profession.insertItemAt("Elève", 1);
                Profession.insertItemAt("Enseignant", 2);
                Profession.insertItemAt("Etudiant", 3);
                Profession.insertItemAt("Autre", 4);
                Profession.setSelectedIndex(0);

                Home.setText("Acceuil");
                Home.setMaximumSize(new java.awt.Dimension(92, 31));
                Home.setMinimumSize(new java.awt.Dimension(92, 31));
                Home.setPreferredSize(new java.awt.Dimension(92, 31));

                Reservation.setText("Emprunt");
                Reservation.setMaximumSize(new java.awt.Dimension(92, 31));
                Reservation.setMinimumSize(new java.awt.Dimension(92, 31));
                Reservation.setPreferredSize(new java.awt.Dimension(92, 31));

                History.setText("Historique");
                History.setMaximumSize(new java.awt.Dimension(92, 31));
                History.setMinimumSize(new java.awt.Dimension(92, 31));
                History.setPreferredSize(new java.awt.Dimension(92, 31));

                Books.setText("Livres");
                Books.setMaximumSize(new java.awt.Dimension(92, 31));
                Books.setMinimumSize(new java.awt.Dimension(92, 31));
                Books.setPreferredSize(new java.awt.Dimension(92, 31));

                // -------------------------------------------------------------------------------
                SearchButton.addActionListener(new Search());
                AddButton.addActionListener(new Add());
                DeleteButton.addActionListener(new Delete());
                ModifyButton.addActionListener(new Modify());
                Reservation.addActionListener(new Reservation());
                Home.addActionListener(new Home());
                Books.addActionListener(new Books());
                History.addActionListener(new History());

                // -------------------------------------------------------------------------------

                javax.swing.GroupLayout NavigationPanelLayout = new javax.swing.GroupLayout(NavigationPanel);
                NavigationPanel.setLayout(NavigationPanelLayout);
                NavigationPanelLayout.setHorizontalGroup(
                                NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(NavigationPanelLayout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(Home,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                36,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(Reservation,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(29, 29, 29)
                                                                .addComponent(Books,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)
                                                                .addComponent(History,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)));
                NavigationPanelLayout.setVerticalGroup(
                                NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(NavigationPanelLayout.createSequentialGroup()
                                                                .addGap(19, 19, 19)
                                                                .addGroup(NavigationPanelLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Reservation,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Home,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(History,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(Books,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(24, Short.MAX_VALUE)));

                jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                AddButton.setText("Ajouter");
                AddButton.setMaximumSize(new java.awt.Dimension(92, 31));
                AddButton.setMinimumSize(new java.awt.Dimension(92, 31));
                AddButton.setPreferredSize(new java.awt.Dimension(92, 31));

                ModifyButton.setText("Modifier");
                ModifyButton.setMaximumSize(new java.awt.Dimension(92, 31));
                ModifyButton.setMinimumSize(new java.awt.Dimension(92, 31));
                ModifyButton.setPreferredSize(new java.awt.Dimension(92, 31));

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(36, 36, 36)
                                                                .addGroup(jPanel3Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(Nom)
                                                                                .addComponent(ID)
                                                                                .addComponent(Profession, 0, 185,
                                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                74,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(AddButton,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                115,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(ModifyButton,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                115,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(46, 46, 46)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(36, 36, 36)
                                                                                                .addComponent(ID,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(35, 35, 35)
                                                                                                .addComponent(Nom,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(31, 31, 31)
                                                                                                .addComponent(Profession,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                32,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(67, 67, 67)
                                                                                                .addComponent(AddButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(34, 34, 34)
                                                                                                .addComponent(ModifyButton,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(43, Short.MAX_VALUE)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(28, 28, 28)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                layout.createSequentialGroup()
                                                                                                                                                .addComponent(NavigationPanel,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGap(21, 21, 21))
                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                layout.createSequentialGroup()
                                                                                                                                                .addComponent(jPanel3,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGap(53, 53, 53))))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGap(70, 70, 70)
                                                                                                .addComponent(jPanel2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 35, Short.MAX_VALUE)))));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(24, Short.MAX_VALUE))
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(44, 44, 44)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(56, 56, 56)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(NavigationPanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(47, 47, 47)));

                pack();
        }

        public Boolean Test(int n) {
                ResultSet rst;

                String requete = "select * from abonne where id =" + n;
                try {
                        st = bd.BaseDeDonnees().createStatement();
                        rst = st.executeQuery(requete);
                        if (rst.next()) { // Check if result set is not empty
                                return true;
                        } else {
                                return false;
                        }
                } catch (SQLException ex) {
                        return false;
                }
        }

        class Search implements ActionListener {
                private int ID;

                @Override
                public void actionPerformed(ActionEvent e) {

                        try {
                                ID = Integer.parseInt(CreateNewMembers.this.IDSeDe.getText().toString());

                                ResultSet rst;
                                Statement st;

                                String requete = "select * from abonne where id ='" + ID + "'";

                                st = bd.BaseDeDonnees().createStatement();
                                rst = st.executeQuery(requete);

                                if (rst.next()) {
                                        JOptionPane.showMessageDialog(null,
                                                        "L'identifiant " + ID + " correspond à l'abonnée "
                                                                        + rst.getString("NomPrenom")
                                                                        + " qui a comme profession "
                                                                        + rst.getString("proffession"),
                                                        "Résultat recherche", JOptionPane.INFORMATION_MESSAGE);
                                        IDSeDe.setText(null);

                                } else {
                                        JOptionPane.showMessageDialog(null, "Cet identifiant n'est pas utilisé",
                                                        "Résultat recherche",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        } catch (NumberFormatException exception) {
                                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier",
                                                "Entrée Invalide",
                                                JOptionPane.ERROR_MESSAGE);
                        }

                        catch (SQLException ex) {
                        }

                }

        }

        void initialiserChamps() {
                Nom.setText(null);
                ID.setText(null);
                Profession.setSelectedIndex(0);
                remplir();
        }

        class Add implements ActionListener {
                private int I;
                private String name;
                private String Job;

                @Override
                public void actionPerformed(ActionEvent e) {

                        try {
                                I = Integer.parseInt(CreateNewMembers.this.ID.getText().toString());
                                name = Nom.getText().toString();
                                Job = Profession.getSelectedItem().toString();

                                if (Test(I) == false) {
                                        if (name.isEmpty() || Job.equals(Profession.getItemAt(0))) {
                                                JOptionPane.showMessageDialog(null, "Veuillez remplier tous les champs",
                                                                "Erreur Saisie",
                                                                JOptionPane.ERROR_MESSAGE);
                                        } else {
                                                int choice = JOptionPane.showConfirmDialog(null,
                                                                "Voulez-vous ajouter ce nouveau abonné",
                                                                "Confirmation", JOptionPane.YES_NO_OPTION);
                                                if (choice == JOptionPane.YES_OPTION) {
                                                        Statement st = bd.BaseDeDonnees().createStatement();
                                                        String qr = "INSERT INTO abonne (id, NomPrenom, Proffession) VALUES ("
                                                                        + I + ", '" + name
                                                                        + "', '" + Job + "')";
                                                        int res = st.executeUpdate(qr);
                                                        if (res > 0) {
                                                                JOptionPane.showMessageDialog(null,
                                                                                "Insertion bien effectuée", "Succes",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                initialiserChamps();

                                                        } else {
                                                                JOptionPane.showMessageDialog(null,
                                                                                "Echec de l'insertion", "Echec",
                                                                                JOptionPane.ERROR_MESSAGE);
                                                        }
                                                } else {
                                                        initialiserChamps();
                                                }
                                        }

                                } else {
                                        JOptionPane.showMessageDialog(null, "ID already used", "Invalid Input",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        } catch (NumberFormatException exception) {
                                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier",
                                                "Entrée Invalide",
                                                JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException ex) {
                        }

                }

        }

        class Delete implements ActionListener {
                private int ID;

                void supprime() throws SQLException {
                        st = bd.BaseDeDonnees().createStatement();
                        int res = st.executeUpdate("delete from abonne where id =" + ID);
                        if (res > 0) {
                                JOptionPane.showMessageDialog(null, "Suppression bien effectuée", "Succes",
                                                JOptionPane.INFORMATION_MESSAGE);
                                IDSeDe.setText(null);
                                remplir();
                        } else {
                                JOptionPane.showMessageDialog(null, "Echec de la Suppression", "Echec",
                                                JOptionPane.ERROR_MESSAGE);
                                IDSeDe.setText(null);
                        }
                }

                void updatetables(int id) throws SQLException {
                        Statement st2 = bd.BaseDeDonnees().createStatement();

                        try {
                                st2.executeUpdate("delete from emprunt where Abid =" + ID);

                        } catch (Exception exception) {
                        }
                }

                void insert(int id) throws SQLException {
                        Statement st1 = bd.BaseDeDonnees().createStatement();
                        Statement st12 = bd.BaseDeDonnees().createStatement();

                        try {

                                rst = st12.executeQuery("select * from abonne where id = " + ID);
                                if (rst.next()) {
                                        st1.executeUpdate("insert into ancienabonne (NomPrenom)values (\""
                                                        + rst.getString("NomPrenom") + "\")");
                                }

                        } catch (Exception exception) {
                        }
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                        // il faut penser à la dependance
                        try {
                                ID = Integer.parseInt(CreateNewMembers.this.IDSeDe.getText().toString());

                                if (Test(ID) == true) {
                                        int choice = JOptionPane.showConfirmDialog(null,
                                                        "Voulez-vous vraiment supprimer ce membre ?",
                                                        "Confirmation de la suppression", JOptionPane.YES_NO_OPTION);

                                        if (choice == JOptionPane.YES_OPTION) {
                                                try {

                                                        st = bd.BaseDeDonnees().createStatement();
                                                        rst = st.executeQuery(
                                                                        "select * from emprunt where AbID = " + ID);

                                                        if (rst.next()) {
                                                                rst = st.executeQuery(
                                                                                "select * from emprunt where Retour is null");
                                                                if (rst.next()) {
                                                                        choice = JOptionPane.showConfirmDialog(null,
                                                                                        "Cet abonné n'a pas encore rendu un ou plusieurs livres, Voulez-vous vraiment le supprimer ?",
                                                                                        "Confirmation de la suppression",
                                                                                        JOptionPane.YES_NO_OPTION);

                                                                        if (choice == JOptionPane.YES_OPTION) {
                                                                                insert(ID);
                                                                                updatetables(ID);
                                                                                supprime();
                                                                        } else {
                                                                                JOptionPane.showMessageDialog(null,
                                                                                                "Suppression annulée",
                                                                                                "Annulé",
                                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                                IDSeDe.setText(null);
                                                                        }
                                                                } else {
                                                                        insert(ID);
                                                                        supprime();
                                                                }

                                                        } else {
                                                                supprime();
                                                        }

                                                } catch (SQLException ex) {
                                                }
                                        }
                                } else {
                                        JOptionPane.showMessageDialog(null, "Cet identifiant n'est pas utilisé",
                                                        "Entrée Invalide",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        } catch (NumberFormatException exception) {
                                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier",
                                                "Entrée Invalide",
                                                JOptionPane.ERROR_MESSAGE);
                        }

                }

        }

        class Modify implements ActionListener {
                private int ID;
                private String name;
                private String Job;
                private Statement st;

                @Override
                public void actionPerformed(ActionEvent e) {
                        // il faut penser à la dependance
                        try {
                                ID = Integer.parseInt(CreateNewMembers.this.ID.getText().toString());
                                name = Nom.getText().toString();
                                Job = Profession.getSelectedItem().toString();

                                if (Test(ID) == true) {
                                        int choice = JOptionPane.showConfirmDialog(null,
                                                        "Voulez-vous vraiment modifier ce membre ?",
                                                        "Confirmation de la modification", JOptionPane.YES_NO_OPTION);
                                        if (choice == JOptionPane.YES_OPTION) {
                                                try {
                                                        st = bd.BaseDeDonnees().createStatement();

                                                        if (name.isEmpty() && !Job.equals(Profession.getItemAt(0))) {
                                                                String qr = "update abonne set Proffession='" + Job
                                                                                + "' where id='" + ID + "'";
                                                                int result = st.executeUpdate(qr);
                                                                if (result > 0) {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                        "Moodification bien effectuée",
                                                                                        "Modification",
                                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                        initialiserChamps();
                                                                }

                                                        } else if (!name.isEmpty()
                                                                        && Job.equals(Profession.getItemAt(0))) {
                                                                String qr = "update abonne set NomPrenom='" + name
                                                                                + "' where id=" + ID;
                                                                int result = st.executeUpdate(qr);
                                                                if (result > 0) {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                        "Modification bien effectuée",
                                                                                        "Modification",
                                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                        initialiserChamps();
                                                                }

                                                        } else if (!name.isEmpty()
                                                                        && !Job.equals(Profession.getItemAt(0))) {
                                                                String qr = "update abonne set NomPrenom='" + name
                                                                                + "' where id=" + ID;
                                                                int result = st.executeUpdate(qr);
                                                                qr = "update abonne set Proffession='" + Job
                                                                                + "' where id='" + ID + "'";
                                                                result = st.executeUpdate(qr);
                                                                if (result > 0) {
                                                                        JOptionPane.showMessageDialog(null,
                                                                                        "Modification bien effectuée",
                                                                                        "Modification",
                                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                        initialiserChamps();
                                                                }

                                                        } else if (name.isEmpty()
                                                                        && Job.equals(Profession.getItemAt(0))) {
                                                                JOptionPane.showMessageDialog(null, "Rien à modifier",
                                                                                "Modification",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                CreateNewMembers.this.ID.setText(null);
                                                        }
                                                } catch (SQLException ex) {
                                                }
                                        }
                                } else {
                                        JOptionPane.showMessageDialog(null, "Cet identifiant n'est pas utilisé",
                                                        "Entrée Invalide",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        } catch (NumberFormatException exception) {
                                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier",
                                                "Entrée Invalide",
                                                JOptionPane.ERROR_MESSAGE);
                        }

                }

        }

        class Reservation implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                        EmpruntRetour n = new EmpruntRetour();
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

        class Books implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                        dispose();
                        CreateNewBooks n = new CreateNewBooks();
                        n.setVisible(true);
                        n.setLocationRelativeTo(null);

                }

        }

        class History implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                        dispose();
                        Statistics n = new Statistics();
                        n.setVisible(true);
                        n.setLocationRelativeTo(null);

                }

        }

        // Variables declaration - do not modify
        private javax.swing.JButton AddButton;
        private javax.swing.JButton DeleteButton;
        private javax.swing.JButton History;
        private javax.swing.JButton Home;
        private javax.swing.JTextField ID;
        private javax.swing.JTextField IDSeDe;
        private javax.swing.JButton Books;
        private javax.swing.JButton ModifyButton;
        private javax.swing.JPanel NavigationPanel;
        private javax.swing.JTextField Nom;
        private javax.swing.JComboBox Profession;
        private javax.swing.JButton Reservation;
        private javax.swing.JButton SearchButton;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private JScrollPane Scroll;
        String[] columns = { "IDENTIFIANT", "Nom et Prenom", "Profession" };
        DefaultTableModel ModelTable = new DefaultTableModel(columns, 0);
        private JTable AbonneActuel = new JTable(ModelTable);

        public Connecting bd = new Connecting();
        ResultSet rst;
        Statement st;
}
