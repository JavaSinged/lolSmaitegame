package Jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainFrame extends JFrame {

    boolean setAim = true;
    int health = 2000;
    JProgressBar healthBar = new JProgressBar(0, health);

    public mainFrame() {
        super("강타 시뮬레이션");
        setSize(280, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        panel.requestFocus();

        //체력바
        healthBar.setValue(health);
        healthBar.setForeground(Color.red);
        healthBar.setBackground(Color.black);
        healthBar.setStringPainted(true);
        healthBar.setString("2000");
        add(healthBar);

        //정글 몹 생성
        ImageIcon Red = new ImageIcon("C:\\Users\\shinj\\Desktop\\blue.png");
        JLabel RedImage = new JLabel(Red);
        panel.add(RedImage);
        add(panel);

        //소환사 주문 이미지 생성
        ImageIcon smite = new ImageIcon("C:\\Users\\shinj\\Desktop\\smite.png");
        ImageIcon collingSmite = new ImageIcon("C:\\Users\\shinj\\Desktop\\collingSmite.png");

        //버튼 이벤트 처리
        ActionListener smiteMouse = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //마우스 커서 바꾸기
                if (setAim == true) {
                    Toolkit Tcursor = Toolkit.getDefaultToolkit();
                    Image changeCursor = Tcursor.getImage("C:\\Users\\shinj\\Desktop\\aim.png");
                    Cursor cursor = Tcursor.createCustomCursor(changeCursor, new Point(15, 15), "크로스헤어");
                    setCursor(cursor);
                    setAim = false;
                } else {
                    Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                    setCursor(defaultCursor);
                    setAim = true;
                }
            }
        };

        //소환사 주문 버튼 생성
        JButton smiteBtn = new JButton(smite);
        smiteBtn.setPressedIcon(collingSmite);
        smiteBtn.setBorderPainted(false);
        smiteBtn.setFocusPainted(false);
        smiteBtn.setContentAreaFilled(false);
        smiteBtn.addActionListener(smiteMouse);
        add(smiteBtn);

        //마우스 이벤트
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int imageX = Red.getIconWidth();
                int imageY = Red.getIconHeight();
                if(setAim==false){
                    if (x > 0 && x <= imageX) {
                        if (y > 0 && y <= imageY) {
                            if(health<=0){
                                healthBar.setValue(0);
                                System.out.println("레드 처치");
                            }
                            System.out.println("마우스 클릭됨:" + x + "," + y);
                            int smiteDMG = 570;
                            health=health-smiteDMG;
                            String strHealth = Integer.toString(health);
                            healthBar.setString(strHealth);
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int imageX = Red.getIconWidth();
                int imageY = Red.getIconHeight();
                if (x > 0 && x <= imageX) {
                    if (y > 0 && y <= imageY) {
                        System.out.println("마우스 들어옴:" + x + "," + y);
                    }
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int imageX = Red.getIconWidth();
                int imageY = Red.getIconHeight();
                if (x > 0 && x <= imageX) {
                    if (y > 0 && y <= imageY) {
                        System.out.println("마우스 들어옴:" + x + "," + y);
                    }
                }
            }
        });

        //되지않는 키보드 이벤트
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyChar() == 'd') {
                    smiteBtn.doClick();
                }
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
        panel.setFocusable(true);


    }

    //체력 감소
    public void downHealth() {
        if(health<=0){
            healthBar.setValue(0);
            System.out.println("레드 처치");
        }
        while (0 <= health) {
            int downPoint = (int) (Math.random() * 60) + 40;
            health = health - downPoint;
            String strHealth = Integer.toString(health);
            healthBar.setValue(health);
            healthBar.setString(strHealth);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        mainFrame frame = new mainFrame();
        frame.downHealth();
    }
}
