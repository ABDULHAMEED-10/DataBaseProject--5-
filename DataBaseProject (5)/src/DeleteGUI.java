import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteGUI extends JFrame {
    static String forSearch;
    //////////////////////////////
    private JComboBox Delete_type_text;
    private JTextField product_name_text;
    ////////////////////////////////
    private JButton done_button;
    private JButton back_button;
    ////////////////////////////////
    private JLabel main_label;
    private JLabel Delete_type_label;
    private JLabel product_name_label;
    private JPanel panel;
    static int id;

    public DeleteGUI() {
        panel=new JPanel();

        ImageIcon img = new ImageIcon("main_menu2.jpg");
        panel=new JPanel(){

            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(),getHeight(),null);
            }
        };
        panel.setLayout(null);
        //////////////////////////////////////
        main_label=new JLabel("DELETE");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(350,40,800,50);
        main_label.setForeground(Color.WHITE);
        //////////////////////////////////////
        String [] array = {"","MEDICINE","DEVICES","OTHER_ITEMS"};
        Delete_type_text= new JComboBox (array);
        Delete_type_text.setBounds(430,180,200,30);

        product_name_text = new JTextField();
        product_name_text.setBounds(430,230,200,30);
        /////////////////////////////////
        done_button=new JButton("DONE");
        done_button.setBounds(725,500,120,30);
        done_button.setBackground(Color.BLACK);
        done_button.setForeground(Color.WHITE);
        
        back_button=new JButton("BACK");
        back_button.setBounds(50,500,120,30);
        back_button.setBackground(Color.BLACK);
        back_button.setForeground(Color.WHITE);
        ////////////////////////////////
        product_name_label =new JLabel("PRODUCT TYPE");
        product_name_label.setBounds(260,182,200,30);
        Delete_type_label =new JLabel("PRODUCT NAME");
        Delete_type_label.setBounds(260,232,200,30);
        ////////////////////////////////////////////////////////
        panel.add(Delete_type_text);
        panel.add(done_button);
        panel.add(back_button);
        panel.add(main_label);
        panel.add(product_name_label);
        panel.add(Delete_type_label);
        panel.add(product_name_text);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("DELETE");
        setVisible(true);

        done_button.addActionListener(new MyHandler());
        back_button.addActionListener(new MyHandler());

        }

    class MyHandler implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == done_button) {
                String Product_name = product_name_text.getText();

                try{
                    java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    Statement stmt = mycon.createStatement();
                    ResultSet result = stmt.executeQuery("Select I_name from items where I_name='"+Product_name+"'");
                    while(result.next()){
                        forSearch = result.getString(1);
                    }
                    Statement stmt2 = mycon.createStatement();
                    int ct1 = stmt2.executeUpdate("Delete from items where I_name = '"+Product_name+"' ");
                }
                catch(Exception g) {
                    System.out.println(g.toString());
                }

                if(forSearch!=null){
                    JOptionPane.showMessageDialog(null, "Product found and Deleted");
                    product_name_text.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Product not found ");
                    product_name_text.setText("");
                }

            }

            if (e.getSource() == back_button) {
                setVisible(false);
                new SalePurchaseGUI();

            }

        }
    }
}
    

