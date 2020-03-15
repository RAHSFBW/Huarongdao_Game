import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Hua_Rong_Road extends JFrame implements MouseListener,KeyListener,ActionListener {
    int cnt = 0;
    Person person[]=new Person[10];
    JButton left,right,above,below;
    JButton restart=new JButton("重新开始");
    JButton Help =new JButton("游戏帮助");
    JButton message=new JButton("当前步数："+cnt);
    String name[]={"曹操","关羽","张","刘","周","黄","兵","兵","兵","兵"};
    public Hua_Rong_Road() {
        init();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,320,500);
        setVisible(true);
        validate();
    }
    public void init() {
        getContentPane().setLayout(null);
        getContentPane().add(restart);//getContentPane()得到一个内容画板
        restart.setBounds(50,320,100,55);
        restart.addActionListener(this);

        add(Help);
        Help.setBounds(180,325,100,25);
        Help.addActionListener(this);

        add(message);
        message.setBounds(175,400,120,25);

        for(int k=0;k<name.length;k++) {
            person[k]=new Person(k,name[k]);
            person[k].addMouseListener(this);
            person[k].addKeyListener(this);
            getContentPane().add(person[k]);
        }
        person[0].setBounds(104,54,100,100);
        ImageIcon caocao = new ImageIcon("images/caocao.jpg");
        caocao.setImage(caocao.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT ));
        person[0].setIcon(caocao);

        person[1].setBounds(104,154,100,50);
        ImageIcon guanyu=new ImageIcon("images//guanyu.jpg");
        guanyu.setImage(guanyu.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT ));
        person[1].setIcon(guanyu);

        person[2].setBounds(54, 154,50,100);
        ImageIcon zhangfei=new ImageIcon("images//zhangfei.jpg");
        zhangfei.setImage(zhangfei.getImage().getScaledInstance(50,100,Image.SCALE_DEFAULT ));
        person[2].setIcon(zhangfei);

        person[3].setBounds(204,154,50,100);
        ImageIcon huangzhong=new ImageIcon("images//huangzhong.jpg");
        huangzhong.setImage(huangzhong.getImage().getScaledInstance(50,100,Image.SCALE_DEFAULT ));
        person[3].setIcon(huangzhong);

        person[4].setBounds(54, 54, 50,100);
        ImageIcon machao=new ImageIcon("images//machao.jpg");
        machao.setImage(machao.getImage().getScaledInstance(50,100,Image.SCALE_DEFAULT ));
        person[4].setIcon(machao);

        person[5].setBounds(204, 54, 50,100);
        ImageIcon zhaoyun=new ImageIcon("images//zhaoyun.jpg");
        zhaoyun.setImage(zhaoyun.getImage().getScaledInstance(50,100,Image.SCALE_DEFAULT ));
        person[5].setIcon(zhaoyun);

        person[6].setBounds(54,254,50,50);
        ImageIcon bing1=new ImageIcon("images//daqiao.jpg");
        bing1.setImage(bing1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT ));
        person[6].setIcon(bing1);

        person[7].setBounds(204,254,50,50);
        ImageIcon bing2=new ImageIcon("images//xiaoqiao.jpg");
        bing2.setImage(bing2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT ));
        person[7].setIcon(bing2);

        person[8].setBounds(104,204,50,50);
        ImageIcon bing3=new ImageIcon("images//diaocan.jpg");
        bing3.setImage(bing3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT ));
        person[8].setIcon(bing3);

        person[9].setBounds(154,204,50,50);
        ImageIcon bing4=new ImageIcon("images//zhenji.jpg");
        bing4.setImage(bing4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT ));
        person[9].setIcon(bing4);
        person[9].requestFocus();

        left=new JButton();
        right=new JButton();
        above=new JButton();
        below=new JButton();
        getContentPane().add(left);
        getContentPane().add(right);
        getContentPane().add(above);
        getContentPane().add(below);
        left.setBounds(49,49,5,260);
        right.setBounds(254,49,5,260);
        above.setBounds(49,49,210,5);
        below.setBounds(49,304,210,5);
        validate();
    }



    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyPressed(KeyEvent e) {
        Person man=(Person)e.getSource();
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
            go(man,below);
        if(e.getKeyCode()==KeyEvent.VK_UP)
            go(man,above);
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
            go(man,left);
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
            go(man,right);
    }
    public void mousePressed(MouseEvent e) {
        Person man=(Person)e.getSource();
        int x=-1,y=-1;
        x=e.getX();
        y=e.getY();
        int w=man.getBounds().width;
        int h=man.getBounds().height;
        if(y>h/2)
            go(man,below);
        if(y<h/2)
            go(man,above);
        if(x<w/2)
            go(man,left);
        if(x>w/2)
            go(man,right);
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
    public void mouseClicked(MouseEvent e)  {}
    public void go(Person man,JButton direction) {
        cnt++;
        message.setText("当前步数："+cnt);
        boolean move=true;
        Rectangle manRect=man.getBounds();
        int x=man.getBounds().x;
        int y=man.getBounds().y;
        if(direction==below)
            y=y+50;
        else if(direction==above)
            y=y-50;
        else if(direction==left)
            x=x-50;
        else if(direction==right)
            x=x+50;
        manRect.setLocation(x,y);
        Rectangle directionRect=direction.getBounds();
        for(int k=0;k<10;k++) {
            Rectangle personRect=person[k].getBounds();
            if((manRect.intersects(personRect))&&(man.number!=k))
                move=false;
        }
        if(manRect.intersects(directionRect))
            move=false;
        if(move==true)
            man.setLocation(x,y);
        int cx,cy;//曹操的位置
        cx=person[0].getBounds().x;
        cy=person[0].getBounds().y;
        if(cx==208&&cy==208)
        {
            win();
            return ;

        }
    }

    public void win()
    {
        JOptionPane.showMessageDialog(this, "恭喜少侠，成功帮曹操脱险，日后必大富大贵！\n"
                + "操作"+cnt+"步.震惊天下！");
        JButton winn=new JButton();
        ImageIcon winner=new ImageIcon("timg (1).gif");
        winn.setIcon(winner);
        winn.setBounds(108,108,400,500);
        add(winn);
        setVisible(true);
        for(int k=0;;k++)
            this.remove(person[k]);
    }

    public void actionPerformed(ActionEvent e) {
        JButton b=(JButton)e.getSource();
        if(b==restart){
            dispose();
            new Hua_Rong_Road();
        }
        if (b== Help){
            JOptionPane.showMessageDialog(this, "胜利条件：曹操到达地图中下方位置！\n"
                    + "点击开始游戏后，通过鼠标进行控制\n"
                    + "鼠标操作：玩家通过点击当前角色人物的不同位置进行相应移动。\n"
                    + "注意，不能往左下，右下，左上，右上进行移动。\n"
                    + "西安电子科技大学JAVA程序设计", "开始之前必看", 0);
        }
    }
}
