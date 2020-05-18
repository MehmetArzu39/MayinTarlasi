
package mayintarlasi;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class setMayinLocation extends MayinRule implements ActionListener {

    private int length;
    private int mayinSayisi;
    private JButton[][] cell;
    private int[][] mayinDizisi;
    private int count;

    public setMayinLocation(int length, int mayinSayisi) {
        this.length = length;
        this.mayinSayisi = mayinSayisi;
        int x, y;
        //Hücreler verilen boyutlarda tekrardan doldurulur.
        cell = new JButton[length][length];
        //Mayinlarýn konumlarý için  hücrelerin boyutu ile ayný boyutta boþ mayýn dizisi oluþturulur.
        mayinDizisi = new int[length][length];
        count = this.length * this.length - this.mayinSayisi;
        for (int i = 0; i < mayinDizisi.length; i++) {
            for (int j = 0; j < mayinDizisi.length; j++) {
                mayinDizisi[i][j] = 0;
            }
        }
        Random random = new Random();
        //mayinDizisinin içi random konumlarý belirlenir.
        for (int j = 0; j < mayinSayisi; j++) {
            x = random.nextInt(length);
            y = random.nextInt(length);
            if (mayinDizisi[x][y] < 0) {
                j--;
                continue;
            }
            mayinDizisi[x][y] = -100;
            try {
                mayinDizisi[x - 1][y]++;
                mayinDizisi[x + 1][y]++;
                mayinDizisi[x][y - 1]++;
                mayinDizisi[x][y + 1]++;
                mayinDizisi[x - 1][y - 1]++;
                mayinDizisi[x - 1][y + 1]++;
                mayinDizisi[x + 1][y - 1]++;
                mayinDizisi[x + 1][y + 1]++;
            } catch (Exception e) {
                System.out.println( e.getMessage() + "setMayinLocation err.");
            }
        }
    }


    public void actionPerformed(ActionEvent e) {

        JButton btn = (JButton) e.getSource();
        Point location = getArrayLocation(btn);
        if (mayinDizisi[location.x][location.y] < 0) {
            cell[location.x][location.y].setText(" ");
            finishGame();
        } else if (mayinDizisi[location.x][location.y] > 0) {
            if (cell[location.x][location.y].getActionCommand() == "put") {
                if (mayinDizisi[location.x][location.y] == 1) {
                    cell[location.x][location.y].setOpaque(true);
                    cell[location.x][location.y].setText("" + mayinDizisi[location.x][location.y]);
                    cell[location.x][location.y].setBackground(Color.PINK);
                }
                if (mayinDizisi[location.x][location.y] == 2) {
                    cell[location.x][location.y].setOpaque(true);
                    cell[location.x][location.y].setText("" + mayinDizisi[location.x][location.y]);
                    cell[location.x][location.y].setBackground(Color.magenta);
                }
                if (mayinDizisi[location.x][location.y] == 3) {
                    cell[location.x][location.y].setOpaque(true);
                    cell[location.x][location.y].setText("" + mayinDizisi[location.x][location.y]);
                    cell[location.x][location.y].setBackground(Color.RED);
                }
                if (mayinDizisi[location.x][location.y] > 3) {
                    cell[location.x][location.y].setOpaque(true);
                    cell[location.x][location.y].setText("" + mayinDizisi[location.x][location.y]);
                    cell[location.x][location.y].setBackground(Color.lightGray);
                }
                count--;
                if (count == 0)
                    JOptionPane.showMessageDialog(null, "Tebrikler.Bu zorlu görevi baþardýnýz.", "Kazaným", JOptionPane.INFORMATION_MESSAGE);

            }
        } else if (mayinDizisi[location.x][location.y] == 0) {
            openTheCell(location.x, location.y);
        }

    }

    /**
     * Oyunda mayýna denk gelinirse oyunun sonlandýrýlýmasý.
     */
    @Override
    public void finishGame() {
        for (int i = 0; i < mayinDizisi.length; i++) {
            for (int j = 0; j < mayinDizisi.length; j++) {
                if (mayinDizisi[i][j] < 0) {
                    cell[i][j].setText(" ");
                    //icon sýnýfýndan yeni nesne oluþturulur. Mayýn koordinatlarýna uygun þekilde resim edilir.
                    ImageIcon image = mayinImage();
                    cell[i][j].setOpaque(true);
                    cell[i][j].setBackground(Color.RED);
                    cell[i][j].setIcon(image);
                } else if (mayinDizisi[i][j] == 0) {
                    cell[i][j].setText(" ");
                    cell[i][j].setOpaque(true);
                    cell[i][j].setBackground(Color.WHITE);
                } else {
                    cell[i][j].setText("" + mayinDizisi[i][j]);
                    if (mayinDizisi[i][j] == 1) {
                        //Hücreler derecelerine göre renklendirilir.
                        cell[i][j].setText("" + mayinDizisi[i][j]);
                        cell[i][j].setOpaque(true);
                        cell[i][j].setBackground(Color.pink);
                    }
                    if (mayinDizisi[i][j] == 2) {
                        //Hücreler derecelerine göre renklendirilir.
                        cell[i][j].setText("" + mayinDizisi[i][j]);
                        cell[i][j].setOpaque(true);
                        cell[i][j].setBackground(Color.magenta);
                    }
                    if (mayinDizisi[i][j] == 3) {
                        //Hücreler derecelerine göre renklendirilir.
                        cell[i][j].setText("" + mayinDizisi[i][j]);
                        cell[i][j].setOpaque(true);
                        cell[i][j].setBackground(Color.RED);
                    }
                    if (mayinDizisi[i][j] > 3) {
                        //Hücreler derecelerine göre renklendirilir.
                        cell[i][j].setText("" + mayinDizisi[i][j]);
                        cell[i][j].setOpaque(true);
                        cell[i][j].setBackground(Color.lightGray);
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Hatalý hamle yaptýnýz. Patladýnýz :(", "Ölüm", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * MayinRule
     * @param x
     * @param y
     */
    @Override
    public void openTheCell(int x, int y) {
        if (x < 0 || x > cell.length - 1 || y < 0 || y > cell.length - 1)
            return;
        if (cell[x][y].getText() == "  ") {
            //secilen hücre acýlýrken, hücre matrisindeki deðerler kontrol edilir.
            //Kontrol sonucuna göre renklendirme iþlemi yapýlýr.
            if (mayinDizisi[x][y] > 0) {
                if (mayinDizisi[x][y] == 1) {
                    cell[x][y].setText("" + mayinDizisi[x][y]);
                    cell[x][y].setOpaque(true);
                    cell[x][y].setBackground(Color.PINK);
                }
                if (mayinDizisi[x][y] == 2) {
                    cell[x][y].setText("" + mayinDizisi[x][y]);
                    cell[x][y].setOpaque(true);
                    cell[x][y].setBackground(Color.magenta);
                }
                if (mayinDizisi[x][y] == 3) {
                    cell[x][y].setText("" + mayinDizisi[x][y]);
                    cell[x][y].setOpaque(true);
                    cell[x][y].setBackground(Color.RED);
                }
                if (mayinDizisi[x][y] > 3) {
                    cell[x][y].setText("" + mayinDizisi[x][y]);
                    cell[x][y].setOpaque(true);
                    cell[x][y].setBackground(Color.lightGray);
                }
                count--;
                if (count == 0)
                    JOptionPane.showMessageDialog(null, "Kazandýn", "Sonuc", JOptionPane.INFORMATION_MESSAGE);
            }

            if (mayinDizisi[x][y] == 0) {
                cell[x][y].setText(" ");
                cell[x][y].setOpaque(true);
                cell[x][y].setBackground(Color.WHITE);
                count--;
                if (count == 0)
                    JOptionPane.showMessageDialog(null, "Kazandýn", "Sonuc", JOptionPane.INFORMATION_MESSAGE);

                openTheCell(x - 1, y - 1);
                openTheCell(x + 1, y - 1);
                openTheCell(x + 1, y);
                openTheCell(x - 1, y);
                openTheCell(x - 1, y + 1);
                openTheCell(x + 1, y + 1);
                openTheCell(x, y + 1);
                openTheCell(x, y - 1);
            }

        }
    }

    public JPanel getContent() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        for (int j = 0; j < cell.length; j++) {
            for (int k = 0; k < cell[j].length; k++) {
                int n = j * cell[j].length + k + 1;
                cell[j][k] = new JButton("  ");
                cell[j][k].addActionListener(this);
                cell[j][k].setActionCommand("put");
                if(k< cell[j].length - 1)
                    gridBagConstraints.gridwidth = 1;
                else
                    gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
                panel.add(cell[j][k], gridBagConstraints);
            }
        }
        return panel;
    }

    public Point getArrayLocation(JButton button) {
        Point point = new Point(-1, -1);
        for (int j = 0; j < cell.length; j++) {
            for (int k = 0; k < cell[j].length; k++) {
                if (cell[j][k] == button) {
                    point.setLocation(j, k);
                    return point;
                }
            }
        }
        return point;
    }

    public ImageIcon mayinImage(){
        java.net.URL dosyaYolu = getClass().getResource("ref/mayin.jpg");
        ImageIcon resim = new ImageIcon(dosyaYolu);
        return resim;
    }
}