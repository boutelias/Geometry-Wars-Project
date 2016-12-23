package gui;

import data.GameDA;
import java.util.List;
import javax.swing.JOptionPane;
import model.Player;
import model.companions.Companion;
import org.mindrot.jbcrypt.BCrypt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Maxime
 */
public class StartFrame extends javax.swing.JFrame {

    /**
     * e
     * Creates new form StartFrame
     */
    public StartFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startPanel = new javax.swing.JPanel();
        btnLogIn = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        btnHighscores = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        logInPanel = new javax.swing.JPanel();
        lblUsernameLogIn = new javax.swing.JLabel();
        lblPasswordLogIn = new javax.swing.JLabel();
        txtUsernameLogIn = new javax.swing.JTextField();
        txtPasswordLogIn = new javax.swing.JPasswordField();
        btnConfirmLogIn = new javax.swing.JButton();
        SignUpPanel = new javax.swing.JPanel();
        lblUsernameSignUp = new javax.swing.JLabel();
        lblPasswordSignUp = new javax.swing.JLabel();
        lblConfirmPasswordSignUp = new javax.swing.JLabel();
        txtUsernameSignUp = new javax.swing.JTextField();
        txtPasswordSignUp = new javax.swing.JPasswordField();
        txtConfirmPasswordSignUp = new javax.swing.JPasswordField();
        btnConfirmSignUp = new javax.swing.JButton();
        HighscoresPanel = new javax.swing.JPanel();
        lblHighscores = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        startPanel.setMinimumSize(new java.awt.Dimension(540, 750));
        startPanel.setOpaque(false);
        startPanel.setPreferredSize(new java.awt.Dimension(540, 600));

        btnLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/logIn.png"))); // NOI18N
        btnLogIn.setText("jButton1");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });

        btnSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/signUp.png"))); // NOI18N
        btnSignUp.setText("jButton1");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        btnHighscores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/highscores.png"))); // NOI18N
        btnHighscores.setText("jButton1");
        btnHighscores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHighscoresActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/exit.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.setActionCommand("btnExit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout startPanelLayout = new javax.swing.GroupLayout(startPanel);
        startPanel.setLayout(startPanelLayout);
        startPanelLayout.setHorizontalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHighscores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        startPanelLayout.setVerticalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnHighscores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(350, Short.MAX_VALUE))
        );

        getContentPane().add(startPanel);
        startPanel.setBounds(330, 120, 540, 660);

        logInPanel.setMinimumSize(new java.awt.Dimension(540, 750));
        logInPanel.setOpaque(false);
        logInPanel.setPreferredSize(new java.awt.Dimension(540, 600));

        lblUsernameLogIn.setBackground(new java.awt.Color(0, 255, 0));
        lblUsernameLogIn.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lblUsernameLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/username.png"))); // NOI18N
        lblUsernameLogIn.setText("Username:");

        lblPasswordLogIn.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lblPasswordLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/password.png"))); // NOI18N
        lblPasswordLogIn.setText("Password:");

        txtUsernameLogIn.setBackground(new java.awt.Color(0, 0, 0));
        txtUsernameLogIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtUsernameLogIn.setForeground(new java.awt.Color(255, 255, 255));
        txtUsernameLogIn.setText("Username");
        txtUsernameLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameLogInActionPerformed(evt);
            }
        });

        txtPasswordLogIn.setBackground(new java.awt.Color(0, 0, 0));
        txtPasswordLogIn.setForeground(new java.awt.Color(255, 255, 255));
        txtPasswordLogIn.setText("Password");

        btnConfirmLogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/confirm.png"))); // NOI18N
        btnConfirmLogIn.setText("Confirm");
        btnConfirmLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmLogInActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout logInPanelLayout = new javax.swing.GroupLayout(logInPanel);
        logInPanel.setLayout(logInPanelLayout);
        logInPanelLayout.setHorizontalGroup(
            logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInPanelLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPasswordLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnConfirmLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lblPasswordLogIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUsernameLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lblUsernameLogIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(99, 99, 99))
        );
        logInPanelLayout.setVerticalGroup(
            logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInPanelLayout.createSequentialGroup()
                .addComponent(lblUsernameLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtUsernameLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lblPasswordLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtPasswordLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnConfirmLogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 400, Short.MAX_VALUE))
        );

        getContentPane().add(logInPanel);
        logInPanel.setBounds(330, 150, 540, 620);

        SignUpPanel.setMinimumSize(new java.awt.Dimension(540, 750));
        SignUpPanel.setOpaque(false);
        SignUpPanel.setPreferredSize(new java.awt.Dimension(540, 650));

        lblUsernameSignUp.setBackground(new java.awt.Color(0, 255, 0));
        lblUsernameSignUp.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lblUsernameSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/username.png"))); // NOI18N
        lblUsernameSignUp.setText("Username:");

        lblPasswordSignUp.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lblPasswordSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/password.png"))); // NOI18N
        lblPasswordSignUp.setText("Password:");

        lblConfirmPasswordSignUp.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        lblConfirmPasswordSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/password.png"))); // NOI18N
        lblConfirmPasswordSignUp.setText("Password:");

        txtUsernameSignUp.setBackground(new java.awt.Color(0, 0, 0));
        txtUsernameSignUp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUsernameSignUp.setForeground(new java.awt.Color(255, 255, 255));
        txtUsernameSignUp.setText("Username");
        txtUsernameSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameSignUpActionPerformed(evt);
            }
        });

        txtPasswordSignUp.setBackground(new java.awt.Color(0, 0, 0));
        txtPasswordSignUp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPasswordSignUp.setForeground(new java.awt.Color(255, 255, 255));
        txtPasswordSignUp.setText("Password");

        txtConfirmPasswordSignUp.setBackground(new java.awt.Color(0, 0, 0));
        txtConfirmPasswordSignUp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtConfirmPasswordSignUp.setForeground(new java.awt.Color(255, 255, 255));
        txtConfirmPasswordSignUp.setText("Password");
        txtConfirmPasswordSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmPasswordSignUpActionPerformed(evt);
            }
        });

        btnConfirmSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/confirm.png"))); // NOI18N
        btnConfirmSignUp.setText("Confirm");
        btnConfirmSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmSignUpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SignUpPanelLayout = new javax.swing.GroupLayout(SignUpPanel);
        SignUpPanel.setLayout(SignUpPanelLayout);
        SignUpPanelLayout.setHorizontalGroup(
            SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignUpPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtConfirmPasswordSignUp)
                        .addComponent(lblConfirmPasswordSignUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPasswordSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPasswordSignUp)
                        .addComponent(txtUsernameSignUp)
                        .addComponent(lblUsernameSignUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        SignUpPanelLayout.setVerticalGroup(
            SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignUpPanelLayout.createSequentialGroup()
                .addComponent(lblUsernameSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtUsernameSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblPasswordSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtPasswordSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblConfirmPasswordSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtConfirmPasswordSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnConfirmSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(SignUpPanel);
        SignUpPanel.setBounds(330, 150, 540, 670);

        HighscoresPanel.setMinimumSize(new java.awt.Dimension(540, 750));
        HighscoresPanel.setOpaque(false);
        HighscoresPanel.setPreferredSize(new java.awt.Dimension(540, 600));

        lblHighscores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/highscores.png"))); // NOI18N

        javax.swing.GroupLayout HighscoresPanelLayout = new javax.swing.GroupLayout(HighscoresPanel);
        HighscoresPanel.setLayout(HighscoresPanelLayout);
        HighscoresPanelLayout.setHorizontalGroup(
            HighscoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HighscoresPanelLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(lblHighscores, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(288, Short.MAX_VALUE))
        );
        HighscoresPanelLayout.setVerticalGroup(
            HighscoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HighscoresPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblHighscores, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(650, Short.MAX_VALUE))
        );

        getContentPane().add(HighscoresPanel);
        HighscoresPanel.setBounds(330, 150, 540, 620);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttons/back.png"))); // NOI18N
        btnBack.setText("Back");
        btnBack.setPreferredSize(new java.awt.Dimension(340, 100));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack);
        btnBack.setBounds(10, 550, 150, 40);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/backgrounds/start-bg1.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 800, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        startPanel.setVisible(false);
        logInPanel.setVisible(true);
        btnBack.setVisible(true);
    }//GEN-LAST:event_btnLogInActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        startPanel.setVisible(false);
        SignUpPanel.setVisible(true);
        btnBack.setVisible(true);
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        startPanel.setVisible(true);
        logInPanel.setVisible(false);
        SignUpPanel.setVisible(false);
        HighscoresPanel.setVisible(false);
        btnBack.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void txtUsernameLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameLogInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameLogInActionPerformed

    private void txtUsernameSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameSignUpActionPerformed

    private void txtConfirmPasswordSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmPasswordSignUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmPasswordSignUpActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        startPanel.setVisible(true);
        logInPanel.setVisible(false);
        SignUpPanel.setVisible(false);
        HighscoresPanel.setVisible(false);
        btnBack.setVisible(false);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnHighscoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHighscoresActionPerformed
        new HighscoreFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHighscoresActionPerformed

    private void btnConfirmLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmLogInActionPerformed

        String username = txtUsernameLogIn.getText();
        String pass = new String(txtPasswordLogIn.getPassword());

        boolean exists = false;

        List<Player> players = GameDA.getInstance().getPlayers();
        for (Player p : players) {
            if (p.getUsername().equals(username)) {
                exists = true;
            }
        }

        if (exists == true) {

            Player p = GameDA.getInstance().getPlayer(username);
            String playername = p.getUsername();
            String passFromDb = p.getPassword();

            String hashedPassword = passFromDb;
            String providedPassword = pass;

            if (BCrypt.checkpw(providedPassword, hashedPassword)) {
                JOptionPane.showMessageDialog(null, "You are logged in");
                
                
                //ophalen userid en meegeven naar startgameframe
                
                
                
                
                logInPanel.setVisible(false);
                new StartGameFrame(playername).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Password is not valid");
            }

           
        } else {
            JOptionPane.showMessageDialog(null, "Username does not exist");
        }


    }//GEN-LAST:event_btnConfirmLogInActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnConfirmSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmSignUpActionPerformed

        String username = txtUsernameSignUp.getText();
        String pass1 = new String(txtPasswordSignUp.getPassword());
        String pass2 = new String(txtConfirmPasswordSignUp.getPassword());

        if (pass1.equals(pass2)) {
            //boolean exists = GameDA.getInstance().usernameExist(username);
            boolean exists = false;
            List<Player> players = GameDA.getInstance().getPlayers();

            for (Player p : players) {
                if (p.getUsername().equals(username)) {
                    exists = true;
                }
            }

            if (exists == false) {
                GameDA db = GameDA.getInstance();
                String hashedpass = BCrypt.hashpw(pass1, BCrypt.gensalt());

                db.addNewPlayer(username, hashedpass);
                JOptionPane.showMessageDialog(null, "User has been added!");
                int playerid = db.getPlayerId(username);
                List<model.Character> characters = db.getShips();
                for(model.Character c : characters){
                    int charid = c.getCharId();
                    db.addPlayer_Character(playerid, charid);
                }
                List<CompanionShop> companions = db.getCompanions();
                for(CompanionShop c : companions){
                    int compid = c.getId();
                    db.addPlayer_Companion(playerid, compid);
                }
            }
            if (exists == true) {
                JOptionPane.showMessageDialog(null, "Username already exists");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
        }
    }//GEN-LAST:event_btnConfirmSignUpActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HighscoresPanel;
    private javax.swing.JPanel SignUpPanel;
    private javax.swing.JLabel background;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnConfirmLogIn;
    private javax.swing.JButton btnConfirmSignUp;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHighscores;
    private javax.swing.JButton btnLogIn;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel lblConfirmPasswordSignUp;
    private javax.swing.JLabel lblHighscores;
    private javax.swing.JLabel lblPasswordLogIn;
    private javax.swing.JLabel lblPasswordSignUp;
    private javax.swing.JLabel lblUsernameLogIn;
    private javax.swing.JLabel lblUsernameSignUp;
    private javax.swing.JPanel logInPanel;
    private javax.swing.JPanel startPanel;
    private javax.swing.JPasswordField txtConfirmPasswordSignUp;
    private javax.swing.JPasswordField txtPasswordLogIn;
    private javax.swing.JPasswordField txtPasswordSignUp;
    private javax.swing.JTextField txtUsernameLogIn;
    private javax.swing.JTextField txtUsernameSignUp;
    // End of variables declaration//GEN-END:variables
}
