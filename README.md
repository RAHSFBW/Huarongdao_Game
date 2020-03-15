# Huarongdao_Game
language：JAVA



idea
-----

    1、由于一开始文件路径写错决定采取文字表述各个棋子，故首先在person类,设置好每个棋子的名字字体和背景颜色，然后在Hua_Rong_Road类中具体实现。
    2、Hua_Rong_Road作为具体实现华容道的主类具体要考虑，窗口的大小，各个棋子的大小，如何控制棋子移动，如何改善游戏者的体验，以及关于规则的实现，进行讨论，决定在众多控制中选择了mousePressed，然后添加了restart，和help功能实现使游戏更加贴合玩家。
    3、经过讨论为了增加趣味性，棋子用通过ImageIcon，setIcon添加了图片，一开始图片无法显示，小组成员通过向老师询问才得以解决问题，但效果不好，虽然在网上查了很多资料，问了很多人依旧不知道如何解决。
    4、添加MAIN类运行小游戏。
    5、一开始没有设置message记录步数，这与规则违背，经过讨论决定改善。
    6、从4399小游戏上借鉴了一些比较有意思的文字，如恭喜少侠，成功帮曹操脱险，日后必大富大贵。
    7、盘面编码 ：盘面的状态（节点）数量是十分有限的，状态总数不会超过50万种。(横刀立马为例) 曹操的走法只有12种，任你如何排放，只有12种，不是20种。 横将（关羽）的排法最多只有11种 接下来对4个竖将排列（组合）， 最后易得盘面编码：各种棋子的编码值乘以码权，然后取和。 码权只需遵照排列规律，随你定，是比较简单的。可设兵的码权为1，竖将则是15，横将则为15*210，曹操为15*210*11。
    
code part    
-------
#####  组件的实现
`JButton restart=new JButton("重新开始");`
`JButton Help =new JButton("游戏帮助");`
 	    `getContentPane().setLayout(null);`
       ` getContentPane().add(restart);//getContentPane()得到一个内容画板`
      `  restart.setBounds(50,320,100,55);`
      `  restart.addActionListener(this);`
      `  add(Help);`
       ` Help.setBounds(180,325,100,25);`
       ` Help.addActionListener(this);`
      `  add(message);`
       ` message.setBounds(175,400,120,25);`
#####  相关控制实现
`left=new JButton();
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
    
    //鼠标按压控制
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
}`
#####  其他
`public void win()
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
`
       
