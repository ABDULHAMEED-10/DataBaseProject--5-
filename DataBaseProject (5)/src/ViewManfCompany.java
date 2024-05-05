import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewManfCompany extends JFrame{
    static String[][] array = new String[17][3];

    ////////////////////////////////////
    private JLabel table_header;
    private JTable my_table;
    /////////////////////////////////////////////
    private JPanel panel;
    private JLabel main_label;
    ////////////////////////////////////////

    private JButton done_button;
    private JButton main_menu_button;


    public ViewManfCompany() {

        try{
            java.sql.Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
            Statement stmt = mycon.createStatement();
            ResultSet result = stmt.executeQuery("select * from MANUFACTURING_COMPANY");
            int i=0;
            while(result.next()){
                array[i][1] = String.valueOf(result.getInt(1));
                array[i][0] = result.getString(2);
                array[i][2] = result.getString(3);
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
        main_label=new JLabel("MANUFACTURER DATA");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(325,40,800,50);
        main_label.setForeground(Color.WHITE);
//table          ////////////////////////////////////////////

        String[] column1={"COMPANY NAME","ID","LOCATION"};
        table_header=new JLabel("   COMPANY NAME                                                    COMPANY ID                                         LOCATION ");
        table_header.setBounds(133,172,800,30);
        table_header.setForeground(Color.WHITE);
        my_table=new JTable(array,column1);
        my_table.setBounds(133,200,618,280);
        my_table.setBackground(Color.WHITE);

///////////////////////////////////////////////////////////


//next button
        done_button=new JButton("DONE");
        done_button.setBounds(725,500,120,30);
        done_button.setBackground(Color.BLACK);
        done_button.setForeground(Color.WHITE);
        done_button.addActionListener(new MyHandler());

/////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.addActionListener(new MyHandler());
        main_menu_button.setBounds(45,500,120,30);
        main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////


        panel.add(main_label);
        panel.add(main_menu_button);
        panel.add(done_button);
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


            if (e.getSource() == done_button) {
                setVisible(false);
                new ForCompanyGUI();
            }
            if (e.getSource() == main_menu_button) {
                setVisible(false);
                new MainMenuGUI();
            }
        }

    }
}
