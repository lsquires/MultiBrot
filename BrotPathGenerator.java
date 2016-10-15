import java.awt.*;

/**
 * Created by Home on 04/03/2015.
 */
public class BrotPathGenerator implements Runnable  {

    private final GUI gui;
    private long thisID;
    public double xstart;
    public double ystart;
    public boolean run;

    public BrotPathGenerator(GUI a,double x,double y) {
        gui = a;

        xstart = x;
        ystart = y;
        run = true;
    }

    @Override
    public void run() {
        run = true;
        generateBrotFractalPath();

    }


    private void generateBrotFractalPath() {

        int pwidth = gui.brot.size;
        gui.brot.remakePathImage();


        Graphics g = gui.brot.pathimage.getGraphics();

        double escape1 =(Double)gui.escaperadiusspinner5.getValue();
        double escape = escape1*escape1;
        int basenumerinumber =(Integer)gui.pspinner2.getValue();
        int baserecinumber = (Integer)gui.qspinner3.getValue();
        int branchIteration = (Integer)gui.pathjiterationsSpinner.getValue();
        gui.progressBar1.setMinimum(0);
        gui.progressBar1.setMaximum(pwidth);

            if(gui.currentPathThread.getId() == thisID) {

                    evaluatePointPath(escape, g, 0, 0, 0, branchIteration, 0, xstart, ystart, basenumerinumber, baserecinumber, 0, 0);
            }else
            {
                run = false;
            }


            }





    private int evaluatePointPath(double escape,Graphics g,double x, double y, int currentDepth, int maxDepth,int total,double c,double ci,int basenumerinumber, int baserecinumber,double prevx,double prevy) {

        if(run) {
            double magnitude = (x * x + y * y);
            if (currentDepth == 0) {
                g.setColor(Color.LIGHT_GRAY);
                //(((double)((e.getX())-250f)*range)/250f)+xoffset;
                g.drawOval((int) ((((x - gui.xoffset) * gui.brot.halfsize) / gui.range) + gui.brot.halfsize - 2), (int) (gui.brot.halfsize - (int) (((y - gui.yoffset) * gui.brot.halfsize) / gui.range) - 2), 4, 4);
            } else if (magnitude > escape) {
                g.setColor(new Color(Math.max(0, Math.min(1, 0.25f + 3f * ((float) (maxDepth - (currentDepth)) / (float) (maxDepth)) / 4f)), 0.1f, 0.1f));

                //(((double)((e.getX())-250f)*range)/250f)+xoffset;
                g.drawOval((int) ((((x - gui.xoffset) * gui.brot.halfsize) / gui.range) + gui.brot.halfsize - 2), (int) (gui.brot.halfsize - (int) (((y - gui.yoffset) * gui.brot.halfsize) / gui.range) - 2), 4, 4);
            } else if (currentDepth == maxDepth) {
                g.setColor(Color.GREEN);
                g.drawOval((int) ((((x - gui.xoffset) * gui.brot.halfsize) / gui.range) + gui.brot.halfsize - 2), (int) (gui.brot.halfsize - (int) (((y - gui.yoffset) * gui.brot.halfsize) / gui.range) - 2), 4, 4);
            } else {
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval((int) ((((x - gui.xoffset) * gui.brot.halfsize) / gui.range) + gui.brot.halfsize - 2), (int) (gui.brot.halfsize - (int) (((y - gui.yoffset) * gui.brot.halfsize) / gui.range) - 2), 4, 4);
            }


            if (magnitude > escape && currentDepth != maxDepth) {
                g.setColor(new Color(Math.max(0, Math.min(1, 0.25f + 3f * ((float) (maxDepth - (currentDepth)) / (float) (maxDepth)) / 4f)), 0.1f, 0.1f));

                g.drawLine((int) ((((x - gui.xoffset) * gui.brot.halfsize) / gui.range) + gui.brot.halfsize), (int) (gui.brot.halfsize - (int) (((y - gui.yoffset) * gui.brot.halfsize) / gui.range)), (int) ((((prevx - gui.xoffset) * gui.brot.halfsize) / gui.range) + gui.brot.halfsize), (int) (gui.brot.halfsize - (int) (((prevy - gui.yoffset) * gui.brot.halfsize) / gui.range)));
            } else {
                g.setColor(Color.GRAY);
                g.drawLine((int) ((((x - gui.xoffset) * gui.brot.halfsize) / gui.range) + gui.brot.halfsize), (int) (gui.brot.halfsize - (int) (((y - gui.yoffset) * gui.brot.halfsize) / gui.range)), (int) ((((prevx - gui.xoffset) * gui.brot.halfsize) / gui.range) + gui.brot.halfsize), (int) (gui.brot.halfsize - (int) (((prevy - gui.yoffset) * gui.brot.halfsize) / gui.range)));
            }


            if (magnitude > escape) {

                return 0;
            } else if (currentDepth == maxDepth) {
                return 1;
            } else {

                magnitude = Math.sqrt(magnitude);


                double angle = Math.atan2(y, x);


                double newx = Math.pow(magnitude, basenumerinumber) * (Math.cos((angle * (double) (basenumerinumber))));
                double newy = Math.pow(magnitude, basenumerinumber) * (Math.sin((angle * (double) (basenumerinumber))));

                angle = Math.atan2(newy, newx);

                double magnituderoot = nthroot2(baserecinumber, Math.sqrt(newx * newx + newy * newy));

                for (int i = 0; i < ((baserecinumber)); i++) {
                    double rootx = magnituderoot * (Math.cos((angle + i * 2f * Math.PI) / (double) (baserecinumber))) + c;
                    double rooty = magnituderoot * (Math.sin((angle + i * 2f * Math.PI) / (double) (baserecinumber))) + ci;


                    evaluatePointPath(escape, g, rootx, rooty, currentDepth + 1, maxDepth, 0, c, ci, basenumerinumber, baserecinumber, x, y);
                }
                gui.brot.repaint();


                return total;
            }
        }else
        {
            return 0;
        }

    }

    public double nthroot2(int n, double A) {
        return nthroot2(n, A, .001);
    }
    public double nthroot2(int n, double A, double p) {
        if(A < 0) {
            System.err.println("A < 0");// we handle only real positive numbers
            return -1;
        } else if(A == 0) {
            return 0;
        }
        double x_prev = A;
        double x = A / n;  // starting "guessed" value...
        while(Math.abs(x - x_prev) > p) {
            x_prev = x;
            x = ((n - 1.0) * x + A / Math.pow(x, n - 1.0)) / n;
        }
        return x;
    }

    public void attatch(long id) {
        thisID =id;
    }

    public void abort() {
        run = false;
    }
}
