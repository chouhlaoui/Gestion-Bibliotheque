import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CreateNewMembers {

    private JFrame frame = new JFrame();
    private JScrollPane Scroll;

    private JTextField IDSeDe = new JTextField();
    private JTextField ID = new JTextField();
    private JTextField Nom = new JTextField();
    private JComboBox Profession = new JComboBox();

    private JButton SearchButton = new JButton("Recherche");
    private JButton DeleteButton = new JButton("Suppression");
    private JButton AddButton = new JButton("Ajout");
    private JButton ModifyButton = new JButton("Modification");

    String[] columns = { "IDENTIFIANT", "Nom et Prenom", "Profession" };
    DefaultTableModel ModelTable = new DefaultTableModel(columns, 0);
    private JTable AbonneActuel = new JTable(ModelTable);

    public Connecting bd = new Connecting();
    ResultSet rst;
    Statement st;

    void remplir() {
        ModelTable.setRowCount(0);
        String requete = "select * from abonne";
        try {
            st = bd.BaseDeDonnees().createStatement();
            rst = st.executeQuery(requete);
            while (rst.next()) {
                ModelTable.addRow(new Object[] {
                        rst.getString("id"), rst.getString("NomPrenom"), rst.getString("Proffession")
                });

            }
        } catch (SQLException ex) {

        }
    }

    public CreateNewMembers() {
        frame.setTitle("Gestion des Membres");
        frame.setSize(850, 700);
        frame.setLocationRelativeTo(null);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

        // Responsible of the list of Books
        JPanel MainPanel = new JPanel();

        // Sort the table according the column you choose
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(ModelTable);
        AbonneActuel.setRowSorter(sorter);
        sorter.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(1, SortOrder.ASCENDING)));

        JTableHeader header = AbonneActuel.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        Scroll = new JScrollPane(AbonneActuel);
        Scroll.setMaximumSize(new Dimension(300, 650));
        Scroll.setPreferredSize(new Dimension(300, 650));
        AbonneActuel.setAutoCreateRowSorter(true);
        MainPanel.add(Scroll);

        remplir();

        // Responsible of the control part
        JPanel SecondaryPanel = new JPanel();
        SecondaryPanel.setLayout(new GridLayout(3, 1));

        // -------------------------------------------------------------------------------

        JPanel Panel1 = new JPanel();
        Panel1.setLayout(new BoxLayout(Panel1, BoxLayout.X_AXIS));

        JPanel Search_Add_Panel = new JPanel();
        Search_Add_Panel.setLayout(new GridBagLayout());

        GridBagConstraints c2 = new GridBagConstraints();
        c2.insets = new Insets(3, 3, 3, 3);

        c2.gridx = 0;
        c2.gridy = 0;
        c2.ipadx = 5;
        c2.ipady = 5;

        SearchButton.setMaximumSize(new Dimension(100, 30));
        DeleteButton.setMaximumSize(new Dimension(120, 30));
        SearchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        DeleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        Search_Add_Panel.add(SearchButton, c2);
        Search_Add_Panel.add(Box.createVerticalStrut(10));

        c2.gridy = 1;
        Search_Add_Panel.add(DeleteButton, c2);

        IDSeDe.setUI(new hint("Identifiant de l'abonné :", true));
        IDSeDe.setMaximumSize(new Dimension(200, 25));
        IDSeDe.setMinimumSize(new Dimension(200, 25));
        IDSeDe.setPreferredSize(new Dimension(200, 25));

        Panel1.add(IDSeDe);
        Panel1.add(Box.createHorizontalStrut(10));
        Panel1.add(Search_Add_Panel);

        // -------------------------------------------------------------------------------

        JPanel Panel2 = new JPanel();
        Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.X_AXIS));

        ID.setMaximumSize(new Dimension(200, 25));
        Nom.setMaximumSize(new Dimension(200, 25));
        Profession.setMaximumSize(new Dimension(200, 25));
        ID.setPreferredSize(new Dimension(200, 25));
        Nom.setPreferredSize(new Dimension(200, 25));
        Profession.setPreferredSize(new Dimension(200, 25));

        ID.setUI(new hint("Identifiant de l'abonné :", true));
        Nom.setUI(new hint("Nom et prenom de l'abonné :", true));

        JPanel Panel21 = new JPanel();
        Panel21.setLayout(new BoxLayout(Panel21, BoxLayout.Y_AXIS));
        Panel21.add(ID);
        Panel21.add(Box.createVerticalStrut(10));
        Panel21.add(Nom);
        Panel21.add(Box.createVerticalStrut(10));

        Profession.insertItemAt("Choisir Le profession : ", 0);
        Profession.insertItemAt("Elève", 1);
        Profession.insertItemAt("Enseignant", 2);
        Profession.insertItemAt("Etudiant", 3);
        Profession.insertItemAt("Autre", 4);
        Profession.setSelectedIndex(0);

        Panel21.add(Profession);

        AddButton.setMaximumSize(new Dimension(100, 30));
        ModifyButton.setMaximumSize(new Dimension(120, 30));
        AddButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ModifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel Panel22 = new JPanel();
        Panel22.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        c1.insets = new Insets(3, 3, 3, 3);

        c1.gridx = 0;
        c1.gridy = 0;
        c1.ipadx = 5;
        c1.ipady = 5;

        Panel22.add(AddButton, c1);
        Panel22.add(Box.createVerticalStrut(10));

        c1.gridy = 1;
        Panel22.add(ModifyButton, c1);

        Panel2.add(Panel21);
        Panel2.add(Box.createHorizontalStrut(10));
        Panel2.add(Panel22);

        // -------------------------------------------------------------------------------

        JPanel NavigationPanel = new JPanel();
        NavigationPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);

        JButton Home = new JButton("Acceuil");
        Home.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        NavigationPanel.add(Home, c);

        JButton Reservation = new JButton("Emprunt");
        Reservation.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 1;
        NavigationPanel.add(Reservation, c);

        JButton Books = new JButton("Livres");
        Books.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 2;
        NavigationPanel.add(Books, c);

        // -------------------------------------------------------------------------------

        SecondaryPanel.add(Panel1);
        SecondaryPanel.add(Panel2);
        SecondaryPanel.add(NavigationPanel);

        p.add(MainPanel);
        p.add(SecondaryPanel);

        // -------------------------------------------------------------------------------
        SearchButton.addActionListener(new Search());
        AddButton.addActionListener(new Add());
        DeleteButton.addActionListener(new Delete());
        ModifyButton.addActionListener(new Modify());
        Reservation.addActionListener(new Reservation());
        Home.addActionListener(new Home());
        Books.addActionListener(new Books());

        // -------------------------------------------------------------------------------

        frame.add(p);
        frame.setVisible(true);
    }

    public Boolean Test(int n) {
        ResultSet rst;
        Statement st;

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
                            "L'identifiant " + ID + " correspond à l'abonnée " + rst.getString("NomPrenom")
                                    + " qui a comme profession " + rst.getString("proffession"),
                            "Résultat recherche", JOptionPane.INFORMATION_MESSAGE);
                    IDSeDe.setText(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Cet identifiant n'est pas utilisé", "Résultat recherche",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier", "Entrée Invalide",
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
                        JOptionPane.showMessageDialog(null, "Veuillez remplier tous les champs", "Erreur Saisie",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter ce nouveau abonné",
                                "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            Statement st = bd.BaseDeDonnees().createStatement();
                            String qr = "INSERT INTO abonne (id, NomPrenom, Proffession) VALUES (" + I + ", '" + name
                                    + "', '" + Job + "')";
                            int res = st.executeUpdate(qr);
                            if (res > 0) {
                                JOptionPane.showMessageDialog(null, "Insertion bien effectuée", "Succes",
                                        JOptionPane.INFORMATION_MESSAGE);
                                initialiserChamps();

                            } else {
                                JOptionPane.showMessageDialog(null, "Echec de l'insertion", "Echec",
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
                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier", "Entrée Invalide",
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

        @Override
        public void actionPerformed(ActionEvent e) {
            // il faut penser à la dependance
            try {
                ID = Integer.parseInt(CreateNewMembers.this.IDSeDe.getText().toString());

                if (Test(ID) == true) {
                    int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce membre ?",
                            "Confirmation de la suppression", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        try {

                            st = bd.BaseDeDonnees().createStatement();
                            rst = st.executeQuery("select * from emprunt where AbID = " + ID);

                            if (rst.next()) {
                                rst = st.executeQuery("select * from emprunt where Retour is null");
                                if (rst.next()) {
                                    choice = JOptionPane.showConfirmDialog(null,
                                            "Cet abonné n'a pas encore rendu un ou plusieurs livres, Voulez-vous vraiment le supprimer ?",
                                            "Confirmation de la suppression", JOptionPane.YES_NO_OPTION);

                                    if (choice == JOptionPane.YES_OPTION) {
                                        st.executeUpdate("delete from emprunt where AbID =" + ID);
                                        supprime();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Suppression annulée", "Annulé",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        IDSeDe.setText(null);
                                    }
                                } else {
                                    st.executeUpdate("delete from emprunt where AbID =" + ID);
                                    supprime();
                                }

                            } else {
                                supprime();
                            }

                        } catch (SQLException ex) {
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cet identifiant n'est pas utilisé", "Entrée Invalide",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier", "Entrée Invalide",
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
                    int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier ce membre ?",
                            "Confirmation de la modification", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            st = bd.BaseDeDonnees().createStatement();

                            if (name.isEmpty() && !Job.equals(Profession.getItemAt(0))) {
                                String qr = "update abonne set Proffession='" + Job + "' where id='" + ID + "'";
                                int result = st.executeUpdate(qr);
                                if (result > 0) {
                                    JOptionPane.showMessageDialog(null, "Moodification bien effectuée", "Modification",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    initialiserChamps();
                                }

                            } else if (!name.isEmpty() && Job.equals(Profession.getItemAt(0))) {
                                String qr = "update abonne set NomPrenom='" + name + "' where id=" + ID;
                                int result = st.executeUpdate(qr);
                                if (result > 0) {
                                    JOptionPane.showMessageDialog(null, "Modification bien effectuée", "Modification",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    initialiserChamps();
                                }

                            } else if (!name.isEmpty() && !Job.equals(Profession.getItemAt(0))) {
                                String qr = "update abonne set NomPrenom='" + name + "' where id=" + ID;
                                int result = st.executeUpdate(qr);
                                qr = "update abonne set Proffession='" + Job + "' where id='" + ID + "'";
                                result = st.executeUpdate(qr);
                                if (result > 0) {
                                    JOptionPane.showMessageDialog(null, "Modification bien effectuée", "Modification",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    initialiserChamps();
                                }

                            } else if (name.isEmpty() && Job.equals(Profession.getItemAt(0))) {
                                JOptionPane.showMessageDialog(null, "Rien à modifier", "Modification",
                                        JOptionPane.INFORMATION_MESSAGE);
                                CreateNewMembers.this.ID.setText(null);
                            }
                        } catch (SQLException ex) {
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cet identifiant n'est pas utilisé", "Entrée Invalide",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "L'identifiant doit etre un entier", "Entrée Invalide",
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
            frame.dispose();
            Acceuil n = new Acceuil();
            n.setVisible(true);
            n.setLocationRelativeTo(null);
        }

    }

    class Books implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            CreateNewBooks n = new CreateNewBooks();
            n.setVisible(true);
            n.setLocationRelativeTo(null);

        }

    }

}
