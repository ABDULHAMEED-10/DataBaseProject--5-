import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;

public class PaymentGUI extends JFrame {
    static Random rand = new Random();
    static int randomNumber = rand.nextInt(100000);
    static int search;
    static String Name_Quantity_array[][];
    static int itemID;

    //////////////////////////////
    private JComboBox payment_type;
    private JTextField payment_id_text;

    ////////////////////////////////
    private JButton done_button;
    ////////////////////////////////
    private JLabel main_label;
    private JLabel payment_id_label;
    private JLabel payment_label;
    private JPanel panel;
    private JButton back;

    public PaymentGUI() {
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
        main_label=new JLabel("PAYMENT");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(350,40,800,50);
        main_label.setForeground(Color.WHITE);
        //////////////////////////////////////
        String [] array = {"","On table","Jazz cash","Easy Paisa"};
        payment_type= new JComboBox (array);
        payment_type.setBounds(430,180,200,30);

        payment_id_text = new JTextField();
        payment_id_text.setBounds(430,230,200,30);
        /////////////////////////////////
        done_button=new JButton("DONE");
        done_button.setBounds(725,500,120,30);
        done_button.setBackground(Color.BLACK);
        done_button.setForeground(Color.WHITE);
        ////////////////////////////////
        payment_label =new JLabel("PAYMENT TYPE");
        payment_label.setBounds(260,182,200,30);
        payment_id_label =new JLabel("PAYMENT ID");
        payment_id_label.setBounds(260,232,200,30);
        ////////////////////////////////////////////////////////
        back = new JButton("Back");
        back.setBounds(45,500,120,30);
        /////////////////////////////////////////////
        panel.add(payment_type);
        panel.add(done_button);
        panel.add(main_label);
        panel.add(payment_label);
        panel.add(payment_id_label);
        panel.add(payment_id_text);
        panel.add(back);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("payment ");
        setVisible(true);

        done_button.addActionListener(new MyHandler());
        back.addActionListener(new MyHandler());

        try{

            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();
            ResultSet result = stmt.executeQuery("select PAYMENT_ID from PAYMENT where PAYMENT_ID="+randomNumber+"");
            while(result.next()){
                search = result.getInt(1);

            }
        }
        catch(Exception g) {
            g.printStackTrace();
        }
        if(search==0){
            payment_id_text.setText(String.valueOf(randomNumber));
        }
        else {
            randomNumber = rand.nextInt(100000);
            payment_id_text.setText(String.valueOf(randomNumber));
        }


        ////////////////////////////////////////////////////

    }
    class MyHandler implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==done_button){

                try {
                    Connection orcl = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    Statement stmt = orcl.createStatement();
                    int ct = stmt.executeUpdate("INSERT INTO CUSTOMER Values("+SaleAllDetailsGUI.getCNIC()+",'"+SaleAllDetailsGUI.getCust_name()+"',"+SaleAllDetailsGUI.getCust_age()+","+SaleAllDetailsGUI.getCust_Ph_num()+")");
                    int count1 = stmt.executeUpdate("INSERT INTO Payment Values("+payment_id_text.getText()+",'"+payment_type.getSelectedItem()+"')");
                    int count2 = stmt.executeUpdate("INSERT INTO Bill Values("+BillGeneration.getBill_code()+","+BillGeneration.getAmount()+",'"+BillGeneration.getDate()+"',"+SaleAllDetailsGUI.getCNIC()+",'"+BillGeneration.getTime()+"',"+payment_id_text.getText()+")");
                    int count3 = stmt.executeUpdate("INSERT INTO Sales_record Values('" + SaleItemDetails.get_type() + "'," + SaleItemDetails.getQuantity() + "," + BillGeneration.getBill_code() + "," + SaleItemDetails.getId() + ")");
                    int count4 = stmt.executeUpdate("INSERT INTO Buys Values("+SaleItemDetails.getId()+","+SaleAllDetailsGUI.getCNIC()+") ");
                    JOptionPane.showMessageDialog(null,"Payment Done");

                }
                catch(Exception g){
                    System.out.println(g.toString());
                }
                try {
                    Name_Quantity_array=new String[20][2];
                    Connection orcl = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
                    Statement stmt = orcl.createStatement();
                    for(int i =0; i<Name_Quantity_array.length-1;i++){

                        if(SaleItemDetails.forCart[i][0]!=null){
                            // storing name
                            Name_Quantity_array[i][0]=SaleItemDetails.forCart[i][0];
                            //storing quantity
                            Name_Quantity_array[i][1]=SaleItemDetails.forCart[i][2];
                            ResultSet result = stmt.executeQuery("Select Quantity from items where I_name = '"+Name_Quantity_array[i][0]+"' ");
                            int quantity =0;
                            while (result.next()) {
                                quantity = result.getInt("Quantity");
                            }
                            quantity = quantity - Integer.parseInt(Name_Quantity_array[i][1]);
                            //deleting
                            if(quantity<=0){
                                int ct1 = stmt.executeUpdate("Delete from items where I_name = '"+Name_Quantity_array[i][0]+"' ");
                            }

                            ResultSet result2 = stmt.executeQuery("select item_id from items where I_name='"+Name_Quantity_array[i][0]+"'");
                            while(result2.next()){
                                itemID = result2.getInt(1);
                            }
                            int ct = stmt.executeUpdate("Update ITEMS SET Quantity = "+quantity+" where item_id = "+itemID+" ");

                        }
                    }

                }catch (Exception g){
                    System.out.println(g.toString());
                }

                setVisible(false);
                new MainMenuGUI();

            }
            if(e.getSource()==back){
                setVisible(false);
                new SaleItemDetails();
            }
        }
    }
}
