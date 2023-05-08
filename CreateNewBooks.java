import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CreateNewBooks extends JFrame {

    public CreateNewBooks() {
        initComponents();
    }

    private void initComponents() {

        MainPanel = new JPanel();
        Scroll = new JScrollPane(LivreActuel);
        SecondaryPanel = new JPanel();
        Panel1 = new JPanel();
        IDSeDe = new JTextField();
        SearchButton = new JButton();
        DeleteButton = new JButton();
        Panel2 = new JPanel();
        Titre = new JTextField();
        ID = new JTextField();
        Auteur = new JTextField();
        NBCopie = new JTextField();
        Categorie = new JComboBox();
        AddButton = new JButton();
        ModifyButton = new JButton();
        NavigationPanel = new JPanel();
        Home = new JButton();
        Reservation = new JButton();
        Members = new JButton();

        // -------------------------------------------------------------------------------
        SearchButton.addActionListener(new Search());
        AddButton.addActionListener(new Add());
        DeleteButton.addActionListener(new Delete());
        ModifyButton.addActionListener(new Modify());
        Reservation.addActionListener(new Reservation());
        Home.addActionListener(new Home());
        Members.addActionListener(new Members());

        // -------------------------------------------------------------------------------

        Categorie.insertItemAt("Choisir la catégorie du Livre : ", 0);
        Categorie.insertItemAt("Journaux", 1);
        Categorie.insertItemAt("Revues", 2);
        Categorie.insertItemAt("Nouveautés", 3);
        Categorie.insertItemAt("Romans", 4);
        Categorie.insertItemAt("Nouvelles", 4);
        Categorie.insertItemAt("Livres Scolaires", 4);
        Categorie.setSelectedIndex(0);

        // -------------------------------------------------------------------------------
        JTableHeader header = LivreActuel.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        // -------------------------------------------------------------------------------

        setTitle("Gestion des Livres");
        setBackground(new Color(255, 255, 255));
        setMaximumSize(new Dimension(900, 750));
        setMinimumSize(new Dimension(900, 750));
        setPreferredSize(new Dimension(900, 750));
        setSize(new Dimension(900, 750));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));

        MainPanel.setMaximumSize(new Dimension(450, 700));
        MainPanel.setMinimumSize(new Dimension(450, 700));
        MainPanel.setPreferredSize(new Dimension(450, 700));

        GroupLayout MainPanelLayout = new GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(Scroll, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(19, Short.MAX_VALUE)));
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(Scroll, GroupLayout.PREFERRED_SIZE, 661, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(22, Short.MAX_VALUE)));

        RemplirJTable();
        getContentPane().add(MainPanel);

        SecondaryPanel.setMaximumSize(new Dimension(400, 700));
        SecondaryPanel.setMinimumSize(new Dimension(400, 700));
        SecondaryPanel.setPreferredSize(new Dimension(400, 700));

        Panel1.setBorder(BorderFactory.createEtchedBorder());

        SearchButton.setText("Recherche");

        DeleteButton.setText("Suppression");

        GroupLayout Panel1Layout = new GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
                Panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, Panel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(IDSeDe, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(Panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(DeleteButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(SearchButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE))
                                .addGap(27, 27, 27)));
        Panel1Layout.setVerticalGroup(
                Panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Panel1Layout.createSequentialGroup()
                                .addGroup(Panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(Panel1Layout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addComponent(IDSeDe, GroupLayout.PREFERRED_SIZE, 30,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Panel1Layout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addComponent(SearchButton, GroupLayout.PREFERRED_SIZE, 33,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)
                                                .addComponent(DeleteButton, GroupLayout.PREFERRED_SIZE, 31,
                                                        GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(43, Short.MAX_VALUE)));

        Panel2.setBorder(BorderFactory.createEtchedBorder());

        AddButton.setText("Ajout");
        AddButton.setMaximumSize(new Dimension(92, 31));
        AddButton.setMinimumSize(new Dimension(92, 31));
        AddButton.setPreferredSize(new Dimension(92, 31));

        ModifyButton.setText("Modification");
        ModifyButton.setMaximumSize(new Dimension(92, 31));
        ModifyButton.setMinimumSize(new Dimension(92, 31));
        ModifyButton.setPreferredSize(new Dimension(92, 31));

        GroupLayout Panel2Layout = new GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
                Panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Panel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(Panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(Panel2Layout.createSequentialGroup()
                                                .addGroup(
                                                        Panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(ID, GroupLayout.PREFERRED_SIZE, 180,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(Categorie, 0, GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE))
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, Panel2Layout.createSequentialGroup()
                                                .addGroup(Panel2Layout
                                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(Panel2Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(Auteur, GroupLayout.DEFAULT_SIZE, 180,
                                                                        Short.MAX_VALUE)
                                                                .addGap(39, 39, 39))
                                                        .addGroup(Panel2Layout.createSequentialGroup()
                                                                .addGroup(Panel2Layout
                                                                        .createParallelGroup(
                                                                                GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(Titre,
                                                                                GroupLayout.Alignment.LEADING,
                                                                                GroupLayout.DEFAULT_SIZE, 180,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(NBCopie,
                                                                                GroupLayout.Alignment.LEADING,
                                                                                GroupLayout.DEFAULT_SIZE, 180,
                                                                                Short.MAX_VALUE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(
                                                        Panel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(AddButton, GroupLayout.PREFERRED_SIZE,
                                                                        105, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(ModifyButton, GroupLayout.PREFERRED_SIZE,
                                                                        105, GroupLayout.PREFERRED_SIZE))
                                                .addGap(25, 25, 25)))));

        Panel2Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] { Auteur, Categorie, ID, NBCopie, Titre });

        Panel2Layout.setVerticalGroup(
                Panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Panel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(ID, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGroup(Panel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(Panel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(Titre, GroupLayout.PREFERRED_SIZE, 30,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(Auteur, GroupLayout.PREFERRED_SIZE, 30,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(21, 21, 21)
                                                .addComponent(NBCopie, GroupLayout.PREFERRED_SIZE, 30,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Panel2Layout.createSequentialGroup()
                                                .addGap(46, 46, 46)
                                                .addComponent(AddButton, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(ModifyButton, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(Categorie, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(59, Short.MAX_VALUE)));

        Panel2Layout.linkSize(SwingConstants.VERTICAL, new Component[] { Auteur, Categorie, ID, NBCopie, Titre });

        NavigationPanel.setBorder(BorderFactory.createEtchedBorder());

        Home.setText("Acceuil");
        Home.setMaximumSize(new Dimension(92, 31));
        Home.setMinimumSize(new Dimension(92, 31));
        Home.setPreferredSize(new Dimension(92, 31));

        Reservation.setText("Emprunt");
        Reservation.setMaximumSize(new Dimension(92, 31));
        Reservation.setMinimumSize(new Dimension(92, 31));
        Reservation.setPreferredSize(new Dimension(92, 31));

        Members.setText("Abonnés");
        Members.setMaximumSize(new Dimension(92, 31));
        Members.setMinimumSize(new Dimension(92, 31));
        Members.setPreferredSize(new Dimension(92, 31));

        GroupLayout NavigationPanelLayout = new GroupLayout(NavigationPanel);
        NavigationPanel.setLayout(NavigationPanelLayout);
        NavigationPanelLayout.setHorizontalGroup(
                NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, NavigationPanelLayout.createSequentialGroup()
                                .addContainerGap(30, Short.MAX_VALUE)
                                .addComponent(Home, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Reservation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Members, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)));

        NavigationPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] { Home, Members, Reservation });

        NavigationPanelLayout.setVerticalGroup(
                NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(NavigationPanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(NavigationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(Reservation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Home, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Members, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(24, Short.MAX_VALUE)));

        NavigationPanelLayout.linkSize(SwingConstants.VERTICAL, new Component[] { Home, Members, Reservation });

        GroupLayout SecondaryPanelLayout = new GroupLayout(SecondaryPanel);
        SecondaryPanel.setLayout(SecondaryPanelLayout);
        SecondaryPanelLayout.setHorizontalGroup(
                SecondaryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(SecondaryPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(SecondaryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(Panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                        .addComponent(NavigationPanel, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(12, Short.MAX_VALUE)));
        SecondaryPanelLayout.setVerticalGroup(
                SecondaryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(SecondaryPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(Panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NavigationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addGap(33, 33, 33)));

        getContentPane().add(SecondaryPanel);

        pack();
    }

    private JButton AddButton;
    private JTextField Auteur;
    private JComboBox<String> Categorie;
    private JButton DeleteButton;
    private JButton Home;
    private JTextField ID;
    private JTextField IDSeDe;
    private JPanel MainPanel;
    private JButton Members;
    private JButton ModifyButton;
    private JTextField NBCopie;
    private JPanel NavigationPanel;
    private JPanel Panel1;
    private JPanel Panel2;
    private JButton Reservation;
    private JScrollPane Scroll;
    private JButton SearchButton;
    private JPanel SecondaryPanel;
    private JTextField Titre;

    String[] columns = { "<HTML>DLivre<br></HTML>",
            "<HTML>Titre<br></HTML>", "<HTML>Auteur<br><HTML>",
            "<HTML>Categorie<br></HTML>",
            "<HTML><center>Nombre <br>de Copies</center></HTML>" };
    DefaultTableModel ModelTable = new DefaultTableModel(columns, 0);
    private JTable LivreActuel = new JTable(ModelTable);

    public Connecting bd = new Connecting();
    Statement st;
    ResultSet rst;

    public Boolean Test(int n) {

        String requete = "select * from livre where IDLivre =" + n;
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
        private int I;

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                I = Integer.parseInt(IDSeDe.getText());

                String requete = "select * from livre where IDLivre=" + I;

                st = bd.BaseDeDonnees().createStatement();
                rst = st.executeQuery(requete);
                if (rst.next()) {
                    String Author = rst.getString("auteur");

                    if (Author == null) {
                        JOptionPane.showMessageDialog(null,
                                "L'identifiant " + I + " correspond au livre " + rst.getString("titre")
                                        + " qui fait partie de la categorie "
                                        + rst.getString("categorie") + " avec "
                                        + rst.getString("NBCopie") + " copie(s) disponible(s)",
                                "Résultat recherche", JOptionPane.INFORMATION_MESSAGE);
                        IDSeDe.setText(null);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "L'identifiant " + I + " correspond au livre " + rst.getString("titre")
                                        + " écrit par " + rst.getString("auteur")
                                        + " et qui fait partie de la categorie "
                                        + rst.getString("categorie") + " avec "
                                        + rst.getString("NBCopie") + " copie(s) disponible(s)",
                                "Résultat recherche", JOptionPane.INFORMATION_MESSAGE);
                        IDSeDe.setText(null);
                    }

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

    class Add implements ActionListener {
        private int id;
        private String titre;
        private int copie;
        private String auteur;
        private String categorie;

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                id = Integer.parseInt(ID.getText());
                copie = Integer.parseInt(NBCopie.getText());
                titre = Titre.getText();
                auteur = Auteur.getText();
                categorie = Categorie.getSelectedItem().toString();

                if (Test(id) == false) {
                    if (titre.isEmpty() || categorie.equals(Categorie.getItemAt(0))) {
                        JOptionPane.showMessageDialog(null, "Veuillez remplier tous les champs", "Erreur Saisie",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous ajouter ce nouveau abonné",
                                "Confirmation", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_OPTION) {
                            st = bd.BaseDeDonnees().createStatement();
                            if (auteur.isEmpty()) {
                                String qr = "INSERT INTO livre (IDLivre, titre, auteur, categorie, NBCopie) VALUES ("
                                        + id + ",'" + titre + "', NULL, '" + categorie + "'," + copie + ")";
                                int res = st.executeUpdate(qr);
                                if (res > 0) {
                                    JOptionPane.showMessageDialog(null, "Insertion bien effectuée", "Succes",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    initialiser_secondPanel();
                                    st.close();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Echec de l'insertion", "Echec",
                                            JOptionPane.ERROR_MESSAGE);
                                    initialiser_secondPanel();
                                }
                            } else {
                                String qr = "INSERT INTO livre (IDLivre, titre, auteur, categorie, NBCopie) VALUES ("
                                        + id + ",'" + titre + "','" + auteur + "', '" + categorie + "'," + copie + ")";
                                int res = st.executeUpdate(qr);
                                if (res > 0) {
                                    JOptionPane.showMessageDialog(null, "Insertion bien effectuée", "Succes",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    initialiser_secondPanel();
                                    st.close();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Echec de l'insertion", "Echec",
                                            JOptionPane.ERROR_MESSAGE);
                                    initialiser_secondPanel();
                                }
                            }

                        } else {
                            initialiser_secondPanel();
                        }
                    }
                    RemplirJTable();
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
            int res = st.executeUpdate("delete from livre where IDLivre =" + ID);
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Suppression bien effectuée", "Succes",
                        JOptionPane.INFORMATION_MESSAGE);
                IDSeDe.setText(null);
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
                ID = Integer.parseInt(CreateNewBooks.this.IDSeDe.getText().toString());

                if (Test(ID) == true) {
                    int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce LIVRE ?",
                            "Confirmation de la suppression", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            st = bd.BaseDeDonnees().createStatement();
                            rst = st.executeQuery("select * from emprunt where LivID = " + ID);
                            if (rst.next()) {
                                choice = JOptionPane.showConfirmDialog(null,
                                        "Ce livre est deja emprunté, Voulez-vous vraiment le supprimer ?",
                                        "Confirmation de la suppression", JOptionPane.YES_NO_OPTION);
                                if (choice == JOptionPane.YES_OPTION) {
                                    st.executeUpdate("delete from emprunt where LivID =" + ID);
                                    supprime();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Suppression annulée", "Annulé",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    IDSeDe.setText(null);
                                }

                            } else {
                                supprime();
                            }
                        } catch (SQLException ex) {
                        }
                    }
                    RemplirJTable();
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
        private int id;
        private String titre;
        private String copie;
        private String auteur;
        private String categorie;
        private Statement st;

        void execute(String qr) {
            try {
                int res = st.executeUpdate(qr);
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Modification bien effectuée", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                    initialiser_secondPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Echec de la modification", "Echec",
                            JOptionPane.ERROR_MESSAGE);
                    initialiser_secondPanel();
                }
            } catch (Exception e) {
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // il faut penser à la dependance
            try {
                id = Integer.parseInt(ID.getText());
                copie = NBCopie.getText();
                titre = Titre.getText();
                auteur = Auteur.getText();
                categorie = Categorie.getSelectedItem().toString();

                if (Test(id) == true) {
                    int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier ce membre ?",
                            "Confirmation de la modification", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        try {
                            st = bd.BaseDeDonnees().createStatement();

                            if (!titre.isEmpty()) {
                                String qr = "update livre set titre = '" + titre + "' where IDLivre = " + id;
                                execute(qr);
                            }
                            if (!auteur.isEmpty()) {
                                String qr = "update livre set auteur = '" + auteur + "' where IDLivre = " + id;
                                execute(qr);
                            }
                            if (!categorie.equals(Categorie.getItemAt(0).toString())) {
                                String qr = "update livre set categorie = '" + categorie + "' where IDLivre = " + id;
                                execute(qr);
                            }
                            if (!copie.isEmpty()) {
                                String qr = "update livre set NBCopie = '" + Integer.parseInt(copie)
                                        + "' where IDLivre = " + id;
                                execute(qr);
                            }
                            RemplirJTable();
                        } catch (SQLException ex) {
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cet identifiant n'est pas utilisé", "Entrée Invalide",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "L'identifiant et le nombre de copies doivent etre un entier",
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

            if (n.isConfirmation() == true) {
                RemplirJTable();
                n.SetConfirmation(false);
            }
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

    class Members implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new CreateNewMembers();
        }

    }

    public void RemplirJTable() {
        ModelTable.setRowCount(0);

        ResultSet rst;
        Statement st;

        String requete = "select * from livre";
        try {
            st = bd.BaseDeDonnees().createStatement();
            rst = st.executeQuery(requete);
            while (rst.next()) {
                ModelTable.addRow(new Object[] {
                        rst.getString("IDLivre"), rst.getString("titre"),
                        rst.getString("auteur"), rst.getString("categorie"), rst.getString("NBCopie")
                });

            }
        } catch (SQLException ex) {

        }
    }

    void initialiser_secondPanel() {
        Auteur.setText(null);
        Titre.setText(null);
        NBCopie.setText(null);
        ID.setText(null);
        Categorie.setSelectedIndex(0);
    }
}
