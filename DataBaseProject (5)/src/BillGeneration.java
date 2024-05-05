import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
public class BillGeneration extends JFrame {

    static Random rand = new Random();
    static int randomNumber = rand.nextInt(100000);

    static int bill_code;
    static double amount=SaleItemDetails.getGrandTotal();


    static String datee = String.valueOf(LocalDate.now());


    static String timee = String.valueOf(LocalTime.now());
    static int search;


    private JTextField Bill_code_text;
    private JTextField cnic_text;


////////////////////////////////////
    private String[][] product_data;
    private JLabel table_header;
    private JLabel cnic_label;
    private JTable my_table;


/////////////////////////////////////////////
    private JPanel panel;

    private JLabel main_label;
    private JLabel Bill_Code_label;
    ////////////////////////////////////////

    private JButton payment_button;


    private JButton main_menu_button;

    public BillGeneration(){

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
        main_label=new JLabel("BILL GENERATION");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(325,40,800,50);
        main_label.setForeground(Color.WHITE);
//table          ////////////////////////////////////////////

        String[] column1={"NAME","TYPE","QUANTITY","PRICE PER UNIT","TOTAL PRICE"};
        table_header=new JLabel("   PRODUCT NAME            TYPE                           QUANTITY                 PRICE PER UNIT             TOTAL PRICE");
        table_header.setBounds(133,172,800,30);
        table_header.setForeground(Color.WHITE);
        product_data = new String[17][5];

        for(int i =0; i<SaleItemDetails.forCart.length-1;i++){

            if(SaleItemDetails.forCart[i][0]!=null){
                product_data[i][0]=String.valueOf(SaleItemDetails.forCart[i][0]);
                product_data[i][1]=String.valueOf(SaleItemDetails.forCart[i][1]);
                product_data[i][2]=String.valueOf(SaleItemDetails.forCart[i][2]);
                product_data[i][3]=String.valueOf(SaleItemDetails.forCart[i][3]);
                product_data[i][4]=String.valueOf(SaleItemDetails.forCart[i][4]);

            }
        }
        my_table=new JTable(product_data,column1);
        my_table.setBounds(133,200,618,280);
        my_table.setBackground(Color.WHITE);
///////////////////////////////////////////////////////////
// Text Boxes




        Bill_code_text = new JTextField();
        Bill_code_text.setBounds(250,500,150,30);
        Bill_code_text.setBorder(null);

        cnic_text = new JTextField();
        cnic_text.setBounds(500,500,200,30);
        cnic_text.setBorder(null);

////////////////////////////////////////////

        try{
            int i=0;
            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();
            ResultSet result = stmt.executeQuery("select Bill_code from Bill where Bill_code="+randomNumber+"");
            //insertion of data
            while(result.next()){
                search = result.getInt(1);
                i++;
            }
        }
        catch(Exception g) {
            System.out.println(g.toString());;
        }
        if(search==0){
            Bill_code_text.setText(String.valueOf(randomNumber));
        }
        else{
            randomNumber = rand.nextInt(100000);
            Bill_code_text.setText(String.valueOf(randomNumber));
        }



//////////////////////////////////////////////////////////////
// LABELS

        Bill_Code_label  = new JLabel("BILL CODE");
        Bill_Code_label.setBounds(170,500,150,30);

        cnic_label = new JLabel("BUYER CNIC");
        cnic_label.setBounds(405,500,200,30);


//////////////////////////////////////////////////////////////
// back button


//next button
        payment_button=new JButton("PAYMENT");
        payment_button.setBounds(725,500,120,30);
        payment_button.setBackground(Color.BLACK);
        payment_button.setForeground(Color.WHITE);
///////////////////////////////////////////////////////

        payment_button.addActionListener(new MyHandler());

/////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.addActionListener(new MyHandler());
        main_menu_button.setBounds(45,500,120,30);
        main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////

        panel.add(main_label);
        panel.add(main_menu_button);
        panel.add(payment_button);
        panel.add(Bill_Code_label);
        panel.add(Bill_code_text);
        panel.add(my_table);
        panel.add(table_header);
        panel.add(cnic_label);
        panel.add(cnic_text);
        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("BILL");
        setVisible(true);


        cnic_text.setText(String.valueOf(SaleAllDetailsGUI.getCNIC()));
        bill_code=randomNumber;


    }


    public static int getBill_code(){
        return bill_code;
    }

    public static double getAmount(){
        return amount;
    }
    public static String  getDate(){
        return datee;
    }
    public static String getTime(){
        return timee;
    }






    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==payment_button){
                JOptionPane.showMessageDialog(null,"Collect Rupees"+SaleItemDetails.getGrandTotal());
                setVisible(false);
                new PaymentGUI();
            }


            if(e.getSource()==main_menu_button){
                setVisible(false);
                new MainMenuGUI();
            }

        }
    }
}
