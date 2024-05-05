
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTypeGUI extends JFrame{
    private JButton Medicine;
    private JButton Devices;
    private JButton Others;

    private JButton Back;
    private JButton mainMenu;



    private JPanel panel;

    public JLabel store_name_label;
//    public JLabel main_menu_label;


    public UpdateTypeGUI(){


        ImageIcon img = new ImageIcon("items.jpg");
        panel=new JPanel()
        {

            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(),getHeight(),null);

            }
        };

        panel.setLayout(null);
        Medicine=new JButton("Medicines");
        Devices=new JButton("Devices");
        Others=new JButton("Others");
        Back = new JButton("Back");
        mainMenu=new JButton("Main Menu");


        Medicine.setBounds(102,460,132,45);
        Devices.setBounds(327,460,133,45);
        Others.setBounds(574,460,132,45);
        Back.setBounds(20,25,120,30);
        mainMenu.setBounds(20,60,120,30);



        //////////////////////////////////////////////////////////
        Font f2=new Font("arial",Font.BOLD,34);
        store_name_label=new JLabel("MEDICAL STORE");
        store_name_label.setFont(f2);
        store_name_label.setBounds(263,35,800,50);
        store_name_label.setForeground(Color.black);
        ////////////////////////////////////////////


        /////////////////////////
        panel.add(Medicine);
        panel.add(Devices);
        panel.add(Others);
        panel.add(store_name_label);
        panel.add(Back);
        panel.add(mainMenu);
        ///////////////////////////
        Medicine.setBackground(Color.BLACK);
        Medicine.setForeground(Color.WHITE);

        Devices.setForeground(Color.WHITE);
        Devices.setBackground(Color.BLACK);

        Others.setForeground(Color.WHITE);
        Others.setBackground(Color.BLACK);




        /////////////////////////////////////////////////

        Medicine.addActionListener(new MyHandler());
        Devices.addActionListener(new MyHandler());
        Others.addActionListener(new MyHandler());
        Back.addActionListener(new MyHandler());
        mainMenu.addActionListener(new MyHandler());

        ///////////////////////////////////////////////////
        add(panel);
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Update");
        setVisible(true);
        /////////////////////////////////////////

    }
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Medicine){
                setVisible(false);
                new UpdatePurchaseItemDetails();

            }
            if(e.getSource()==Devices){
                setVisible(false);
                new UpdatePurchaseDeviceDetailsGUI();
            }
            if(e.getSource()==Others){
                setVisible(false);
                new UpdatePurchaseOtherDetailsGUI();

            }
            if(e.getSource()==Back){
                setVisible(false);
                new SalePurchaseGUI();
            }
            if(e.getSource()==mainMenu){
                setVisible(false);
                new MainMenuGUI();
            }

        }
    }
}

