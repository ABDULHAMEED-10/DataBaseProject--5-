import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ViewMedicines extends JFrame {

    static String[][] array = new String[17][6];
    ////////////////////////////////////

    private JLabel table_header;
    private JTable my_table;


    /////////////////////////////////////////////
    private JPanel panel;

    private JLabel main_label;

    ////////////////////////////////////////

    private JButton Done_button;
    private JButton main_menu_button;

    public ViewMedicines() {
        try{
            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();
            ResultSet result = stmt.executeQuery("select items.i_name ,medicine.med_formula,items.quantity,items.price,medicine.medicine_category_med_category,medicine.exp_date from Medicine  inner join items on Medicine.item_id=Items.item_id inner join medicine_category on medicine.medicine_category_med_category=medicine_category.med_category");
            int i=0;
            while(result.next()){
                array[i][0] = result.getString(1);
                array[i][1] = result.getString(2);
                array[i][2] = String.valueOf(result.getInt(3));
                array[i][3] = String.valueOf(result.getInt(4));
                array[i][4] = result.getString(5);
                array[i][5] = result.getString(6);
                i++;
            }
        }
        catch(Exception g) {
            System.out.println(g.toString());
        }
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
        main_label=new JLabel("MEDICINE DATA");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(325,40,800,50);
        main_label.setForeground(Color.WHITE);
//table          ////////////////////////////////////////////

        String[] column1={"MEDICINE NAME","FORMULA","QUANTITY","PRICE","CATEGORY","EXPIRY DATE"};
        table_header=new JLabel("    MEDICINE NAME               FORMULA                       QUANTITY                       PRICE                    CATEGORY                  EXPIRY DATE");
        table_header.setBounds(90,172,1000,30);
        table_header.setForeground(Color.WHITE);
        my_table=new JTable(array,column1);
        my_table.setBounds(90,200,750,280);
        my_table.setBackground(Color.WHITE);
///////////////////////////////////////////////////////////


//next button
        Done_button=new JButton("DONE");
        Done_button.setBounds(725,500,120,30);
        Done_button.setBackground(Color.BLACK);
        Done_button.setForeground(Color.WHITE);
        Done_button.addActionListener(new MyHandler());

/////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.addActionListener(new MyHandler());
        main_menu_button.setBounds(45,500,120,30);
        main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////


        panel.add(main_label);
        panel.add(main_menu_button);
        panel.add(Done_button);
        panel.add(my_table);
        panel.add(table_header);
        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("View Data");
        setVisible(true);

    }
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            if(e.getSource()==Done_button){
                setVisible(false);
                new MainMenuGUI();

            }

            if(e.getSource()==main_menu_button){
                setVisible(false);
                new MainMenuGUI();
            }

        }
    }
}
