import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel{

    public BufferedImage image;
    public Graphics imageGraphics;
    public GUI gui;
    public BufferedImage pathimage;
    public BufferedImage rectimage;

    public int size;


    public double halfsize;
   // public Graphics imageGraphics;

    public ImagePanel() {
        size = 500;

        setSize(size,size);
        image = new BufferedImage(size,size,BufferedImage.TYPE_3BYTE_BGR);
        pathimage= new BufferedImage(size,size,BufferedImage.TYPE_4BYTE_ABGR);
        imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.drawRect(0,0,size,size);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        if(gui!= null ) {
            if(gui.renderPath) {
                g.drawImage(pathimage, 0, 0, null);
            }
            if(gui.renderRect) {
                g.setColor(Color.cyan);

                //g.drawOval(gui.firstmousedragx, gui.firstmousedragy, 2, 2);]

                g.drawLine(gui.firstmousedragx, gui.firstmousedragy, gui.firstmousedragx, gui.mousedragy );
                g.drawLine(gui.firstmousedragx,gui.firstmousedragy,gui.mousedragx,gui.firstmousedragy);
                g.drawLine(gui.mousedragx,gui.firstmousedragy,gui.mousedragx,gui.mousedragy);
                g.drawLine(gui.firstmousedragx,gui.mousedragy,gui.mousedragx,gui.mousedragy);
                //g.drawRect(gui.firstmousedragx,gui.firstmousedragy,gui.mousedragx-gui.firstmousedragx,gui.mousedragy-gui.firstmousedragy);
            }
        }
    }


    public void remakePathImage() {
        halfsize = size/2f;

        pathimage= new BufferedImage(size,size,BufferedImage.TYPE_4BYTE_ABGR);
    }

    public void remakeImage() {
        halfsize = size/2f;
        setSize(size,size);
        image = new BufferedImage(size,size,BufferedImage.TYPE_3BYTE_BGR);

        imageGraphics = image.getGraphics();
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.drawRect(0,0,size,size);
    }

}