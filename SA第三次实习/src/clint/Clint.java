package clint;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.xml.namespace.QName;
//import javax.xml.rpc.Call;
import javax.xml.rpc.ServiceException;
//import javax.xml.ws.Service;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.sun.codemodel.internal.JOp;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
/*
*
*
*
*
*
*希望通过这次实习，掌握制作java页面的方法
*
*
*
* BorderLayout，边界布局管理器。它把 Container 按方位分为 5 个区域（东、西、南、北、中），每个区域放置一个组件。
*
*
* */

public class Clint {

    /*首先，搞一下窗口问题，适配所有窗口需要获取窗口大小*/
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize= toolkit.getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;
    int windowsWidth = 500;
    int windowsHeight = 400;
    int windowsLocationX = (screenWidth-windowsWidth)/2;
    int windowsLocationY = (screenHeight-windowsHeight)/2;
    /*以上就是我设置的窗口位置*/


    /*主界面*/
    JFrame jFrame;
    JButton jButton;
    JButton jAdd;
    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    JLabel jLabel5;
    JLabel jLabel6;
    JTextArea jTextArea1;
    JTextArea jTextArea2;
    JTextField jTextLeft;
    JTextField jTextRight;
    JTextField jTextTitle;
    JComboBox jComboBox;
    JRadioButton jRadioButtonSoup;
    JRadioButton jRadioButtonRest;


    /*添加联系人界面*/
    JFrame jFrame1;
    JButton jReturn;
    JLabel jLabel7;
    JLabel jLabel8;
    JLabel jLabel9;
    JTextArea textArea3;
    JTextArea textArea4;
    JTextArea textArea5;


    String manyAccount = "";
    //这是群发的字符串


    String [] Choose = new String[]{"选择联系人","自己","董少","杨洋"};
    String [] nameLeft = new String[]{"0","2291146413","3154955138","779771381"};
    String [] nameRight = new String[]{"0","@qq.com","@qq.com","@qq.com"};


    public Clint(){
        initUI();
    }
    public void initUI(){


        jFrame = new JFrame();
        jButton = new JButton("发送");
        jAdd = new JButton("添加联系人");
        jLabel1 = new JLabel("输入地址");
       // jLabel2 = new JLabel("选择联系人");
        jLabel3 = new JLabel("输入标题");
        jLabel4 = new JLabel("输入正文");
        jLabel5 = new JLabel("选择发送方式");
        jLabel6 = new JLabel("+");
        jTextArea1 = new JTextArea();
        jTextArea2 = new JTextArea();
        jTextLeft = new JTextField();
        jTextRight = new JTextField();
        jTextTitle = new JTextField();
        jComboBox = new JComboBox(Choose);
        jRadioButtonSoup = new JRadioButton("soup");
        jRadioButtonRest = new JRadioButton("rest");
        jFrame.setTitle("邮件发送系统");
        jFrame.setBounds(windowsLocationX,windowsLocationY,windowsWidth,windowsHeight);
        jFrame.getContentPane().setLayout(null);//使用绝对布

        jLabel1.setBounds(10,10,80,30);
       // jLabel2.setBounds(10,65,80,30);
        jLabel3.setBounds(10,120,80,30);
        jLabel4.setBounds(10,150,80,30);
        jLabel5.setBounds(10,330,80,30);
        jLabel6.setBounds(315,10,30,30);
        jComboBox.setBounds(80,65,230,30);
        jRadioButtonSoup.setBounds(100,330,70,30);
        jRadioButtonRest.setBounds(170,330,70,30);
        jButton.setBounds(400,330,90,45);
        jAdd.setBounds(220,93,100,30);
        jTextLeft.setBounds(80,10,230,30);
        jTextRight.setBounds( 330,10,120,30);
        jTextTitle.setBounds(80,120,370,30);
        jTextArea1.setBounds(80,150,370,160);
        jTextArea2.setBounds(330,43,120,75);
        /*设置combobox的缺省值为第0个项*/
        jComboBox.setSelectedIndex(0);
        jRadioButtonSoup.setSelected(true);//设置默认值
        /**/
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    if(e.getItem() != "") {
                        if(!jTextArea2.getText().contains(e.getItem().toString())) {
                            if (jTextArea2.getText() == "") {
                                jTextArea2.append(jComboBox.getSelectedItem().toString());
                            } else {
                                jTextArea2.append(" " + jComboBox.getSelectedItem().toString());
                            }
                            jTextLeft.setText(nameLeft[jComboBox.getSelectedIndex()]);
                            jTextRight.setText(nameRight[jComboBox.getSelectedIndex()]);
                            if(manyAccount == "") {
                                manyAccount += (nameLeft[jComboBox.getSelectedIndex()] + nameRight[jComboBox.getSelectedIndex()]);
                            }else if(manyAccount != "") {
                                manyAccount += (","+nameLeft[jComboBox.getSelectedIndex()] + nameRight[jComboBox.getSelectedIndex()]);
                                System.out.println("manyAccount为:"+manyAccount);
                            }
                        }else{
                            jTextLeft.setText(nameLeft[jComboBox.getSelectedIndex()]);
                            jTextRight.setText(nameRight[jComboBox.getSelectedIndex()]);
                            System.out.println("manyAccount为:"+manyAccount);

                            JOptionPane.showMessageDialog(jFrame,"联系人已经选择了","WARNING",1);
                        }

                    }else {
                        JOptionPane.showMessageDialog(jFrame,"选择错误","选择作物",1);
                    }
                }


            }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String re = jTextLeft.getText()+jTextRight.getText();
                String name1  = jTextTitle.getText();
                String name2 = jTextArea1.getText();
                System.out.println("选择的是"+jTextArea2.getText());
                Clint_soup clint_soup = new Clint_soup();
                JOptionPane.showMessageDialog(jFrame,"确定要发给"+jTextArea2.getText()+"嘛","发送确认",1);
                try {
                    System.out.println("re:"+re);
                    System.out.println("name1:"+name1);
                    System.out.println("name3:"+name2);
                    String [] s = manyAccount.split(",");
                    int num = s.length;
                    if(num == 1) {
                        System.out.println("11111");
                        clint_soup.one_soup(manyAccount, name1, name2);
                        JOptionPane.showMessageDialog(jFrame,"单人发送成功！","返回",1);
                    }else if(num>1){
                        System.out.println("222222");
                        clint_soup.two_soup(manyAccount,name1,name2);
                        JOptionPane.showMessageDialog(jFrame,"多人发送成功！","返回",1);

                    }else{
                        JOptionPane.showMessageDialog(jFrame,"发送有问题啊！","确认",1);
                    }
                }catch (RemoteException r){
                    System.out.println("RemoteException错误");
                }catch (ServiceException s){
                    System.out.println("ServiceException错误");
                }

            }
        });

        jAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame1= new JFrame();
                jReturn = new JButton("OK!");
                jLabel7 = new JLabel("姓名");
                jLabel8 = new JLabel("邮箱地址");
                jLabel9 = new JLabel("邮箱后缀");
                textArea3 = new JTextArea();
                textArea4 = new JTextArea();
                textArea5 = new JTextArea();
                jFrame1.setTitle("添加联系人");
                jFrame1.setLayout(null);

                jFrame1.setBounds(windowsLocationX+100,windowsLocationY+80,300,200);
                jLabel7.setBounds(10,10,80,30);
                jLabel8.setBounds(10,60,80,30);
                jLabel9.setBounds(10,110,80,30);

                textArea3.setBounds(80,10,150,30);
                textArea4.setBounds(80,60,150,30);
                textArea5.setBounds(80,110,150,30);
                textArea3.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                textArea4.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                textArea5.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));


                jReturn.setBounds(220,140,80,30);


                //往字符串数组追加新数据

                jFrame1.add(jReturn);
                jFrame1.add(jLabel7);
                jFrame1.add(jLabel8);
                jFrame1.add(jLabel9);
                jFrame1.add(textArea3);
                jFrame1.add(textArea4);
                jFrame1.add(textArea5);
//                jFrame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame1.setVisible(true);

                jReturn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("新联系人叫："+textArea3.getText());
                        jComboBox.addItem(textArea3.getText());
                        System.out.println("nameLeft新插入了：："+textArea4.getText());
                        Choose = Insert(Choose,textArea3.getText());
                        System.out.println("nameRight新插入了：："+textArea5.getText());
                        nameLeft = Insert(nameLeft,textArea4.getText());
                        nameRight = Insert(nameRight,textArea5.getText());
                        JOptionPane.showMessageDialog(jFrame1,"成功","成功",1);
                    }
                });
            }
        });

        jFrame.add(jLabel1);
       // jFrame.add(jLabel2);
        jFrame.add(jLabel3);
        jFrame.add(jLabel4);
        jFrame.add(jLabel5);
        jFrame.add(jLabel6);
        jFrame.add(jComboBox);
        jFrame.add(jRadioButtonSoup);
        jFrame.add(jRadioButtonRest);
        jFrame.add(jTextLeft);
        jFrame.add(jTextRight);
        jFrame.add(jTextTitle);
        jFrame.add(jTextArea1);
        jFrame.add(jTextArea2);
        jFrame.add(jButton);
        jFrame.add(jAdd);
        jFrame.setVisible(true);

    }

    /*在String数组后面添加数据*/
    private static String[] Insert(String[] arr, String str) {
        int size = arr.length;  //获取数组长度
        String[] tmp = new String[size + 1];  //新建临时字符串数组，在原来基础上长度加一
        for (int i = 0; i < size; i++){  //先遍历将原来的字符串数组数据添加到临时字符串数组
            tmp[i] = arr[i];
        }
        tmp[size] = str;  //在最后添加上需要追加的数据
        return tmp;  //返回拼接完成的字符串数组
    }


    public static void main(String [] args)
    {
        Clint clint = new Clint();
    }


}
