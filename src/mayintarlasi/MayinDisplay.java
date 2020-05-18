package mayintarlasi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MayinDisplay extends MayinRule {
    JFrame frame = new JFrame("Mayin Tarlasi");

    /**
     * Yeni bir frame ile main uygulamasý baþlatýlýr.
     * @param args
     */
    public static void main(String[] args) {
        //Boþ bir frame oluþturulur.
        MayinDisplay mayinDisplay = new MayinDisplay();
        mayinDisplay.newBlankFrame();

    }

    /**
     * Yeni Oyun oluþtur
     */
    public void newBlankFrame() {
        //Yeni frame hücreler ile doldurulur.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        //frame içi mayýn hücreleri ile doldurulmasý için "setMayinLocation" fonksiyonuna gönderilir.
        //setMayinLocation length ve mayinSayisi adýnda 2 adet parametre alýr. Bu parametreler hücre boyutu ile mayýn sayýsýný belirlemektedir.
        frame.getContentPane().add(new setMayinLocation(10, 8).getContent());
        frame.setVisible(true);
        // menubar nesnesi oluþturulur.
        JMenuBar menu = new JMenuBar();
        menu.setSize(550, 100);
        mainBar(menu);
        frame.setLocation(600, 250);
        frame.setJMenuBar(menu);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Menu Oluþturulmasý
     * @param menuBar
     */
    public void mainBar(JMenuBar menuBar) {
        //Menüde oyun seçenekleri ve çýkýþ butonlarý bulunmaktadýr.
        JMenu main = new JMenu("Oyun");
        menuBar.add(main);
        JMenuItem kolay = new JMenuItem("Yeni Oyun (kolay)");
        main.add(kolay);
        JMenuItem zor = new JMenuItem("Yeni Oyun (zor)");
        main.add(zor);
        JMenuItem cikis = new JMenuItem("Cikis");
        main.add(cikis);

        cikis.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /**
         * Kolay Oyun Oluþtur.
         */
        kolay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MayinDisplay al = new MayinDisplay();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(false);
                //Yeni frame oluþturulur. Bu frame oyunun zorluðuna göre deðiþmektedir.
                JFrame frm = new JFrame("Mayin Tarlasi");
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frm.getContentPane().add(new setMayinLocation(10, 8).getContent());
                frm.setSize(800, 600);
                frm.setVisible(true);
                JMenuBar menuBar = new JMenuBar();
                frm.setJMenuBar(menuBar);
                frm.setLocation(600, 250);
                al.mainBar(menuBar);
                al.frame = frm;
            }
        });
        /**
         * Zor Oyun Oluþtur
         */
        zor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MayinDisplay display = new MayinDisplay();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(false);
                //Yeni frame oluþturulur. Bu frame oyunun zorluðuna göre deðiþmektedir.
                JFrame frm = new JFrame("Mayin Tarlasi");
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frm.getContentPane().add(new setMayinLocation(16, 55).getContent());
                frm.setSize(1050, 700);
                frm.setVisible(true);
                JMenuBar menuBar = new JMenuBar();
                frm.setJMenuBar(menuBar);
                frm.setLocation(600, 250);
                display.mainBar(menuBar);
                display.frame = frm;
            }
        });
    }
}