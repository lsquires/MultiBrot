import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * Created by Home on 15/01/2015.
*/
public class Main extends JPanel {

    private static JFrame frame;
    //public Image img;


    public static BufferedImage leftimg;
    public static BufferedImage rightimg;

    public int basenumber;
    public int basenumerinumber;
    public int baserecinumber;
    public int branchIteration;
    public int[][] image;
    public static int pwidth;
    public static int pheight;
    public float range;
    public int version;
    public int typeOfFractal = 0; // 0 = mandelbrot, 1 = julia
    public double juliac = 0;
    public double juliaci = 0;


    public Main(int type, int basen, int basenumer, int basedem, int iter, int ra, int typeOfRender, double jc, double jci) {
        typeOfFractal = type;

        basenumber = basen;
        baserecinumber = basedem;
        basenumerinumber = basenumer;
        branchIteration = iter;
        range = ra;

        juliac = jc;
        juliaci = jci;

        version = typeOfRender; //0=normal, 1 = edges, 2=diff


        redo();
        repaint();


    }

    public void redo() {
        BufferedImage tempimg = null;
        if (typeOfFractal == 0) {
            tempimg = leftimg;
        } else {
            tempimg = rightimg;
        }

        if (version == 0) {
            image = new int[pwidth][pheight];
            float biggestfinalWeight = 0;
            Graphics g = tempimg.getGraphics();
            for (int x = 0; x < pwidth; x++) {
                for (int y = 0; y < pheight; y++) {
                    double x3 = (x * ((2 * range) / pwidth)) - range;
                    double y3 = (y * ((2 * range) / pheight)) - range;
                    int finalWeight;
                    if (typeOfFractal == 0) {
                        finalWeight = evaluatePoint(0, 0, 0, branchIteration, 0, x3, y3);
                    } else {
                        finalWeight = evaluatePointj(x3, y3, 0, branchIteration, 0, juliac, juliaci);
                    }

                    image[x][y] = finalWeight;
                    if (finalWeight > biggestfinalWeight) {
                        biggestfinalWeight = finalWeight;
                    }
                }
            }

            for (int x = 0; x < pwidth; x++) {
                for (int y = 0; y < pheight; y++) {


                    if (image[x][y] == 0) {
                        g.setColor(Color.BLACK);
                    } else {
                        g.setColor(new Color(
                                Math.min(1, Math.max(0, ((image[x][y] / biggestfinalWeight) * 2.5f + 0.4f))),
                                Math.min(1, Math.max(0, (image[x][y] / biggestfinalWeight))),
                                Math.min(1, Math.max(0, ((((image[x][y])) / biggestfinalWeight)) * 2f - 0.5f))));
                    }

                    g.drawRect(x, y, 1, 1);
                }
            }


        } else if (version == 1) {

            image = new int[pwidth][pheight];
            float biggestfinalWeight = 0;
            Graphics g = tempimg.getGraphics();
            for (int x = 0; x < pwidth; x++) {
                for (int y = 0; y < pheight; y++) {
                    double x3 = (x * ((2 * range) / pwidth)) - range;
                    double y3 = (y * ((2 * range) / pheight)) - range;
                    int finalWeight;
                    if (typeOfFractal == 0) {
                        finalWeight = evaluatePoint(0, 0, 0, branchIteration, 0, x3, y3);
                    } else {
                        finalWeight = evaluatePointj(x3, y3, 0, branchIteration, 0, juliac, juliaci);
                    }

                    image[x][y] = finalWeight;
                    if (finalWeight > biggestfinalWeight) {
                        biggestfinalWeight = finalWeight;
                    }
                }
            }

            int[][] newimage = new int[pwidth][pheight];

            for (int x = 1; x < pwidth - 1; x++) {
                for (int y = 1; y < pheight - 1; y++) {

                    newimage[x][y] = 0;

                    if ((image[x][y] - image[x - 1][y]) != 0) {
                        newimage[x][y]++;
                    }
                    if ((image[x][y] - image[x + 1][y]) != 0) {
                        newimage[x][y]++;
                    }
                    if ((image[x][y] - image[x][y - 1]) != 0) {
                        newimage[x][y]++;
                    }
                    if ((image[x][y] - image[x][y + 1]) != 0) {
                        newimage[x][y]++;
                    }
                    if (newimage[x][y] > 0) {
                        newimage[x][y]++;
                    }

                }
            }
            for (int x = 0; x < pwidth; x++) {
                for (int y = 0; y < pheight; y++) {


                    if (newimage[x][y] == 0) {
                        g.setColor(Color.BLACK);
                    } else if (newimage[x][y] == 2) {
                        g.setColor(new Color(0f, 0.075f, 0.25f));
                    } else if (newimage[x][y] == 3) {
                        g.setColor(new Color(0f, 0.2f, 0.5f));
                    } else if (newimage[x][y] == 4) {
                        g.setColor(new Color(0f, 0.4f, 0.6f));
                    } else if (newimage[x][y] == 5) {
                        g.setColor(new Color(0f, 0.5f, 0.7f));
                    }


                    g.drawRect(x, y, 1, 1);
                }
            }

        } else if (version == 2) {
            image = new int[pwidth][pheight];
            float biggestfinalWeight = 0;
            Graphics g = tempimg.getGraphics();
            for (int x = 0; x < pwidth; x++) {
                for (int y = 0; y < pheight; y++) {
                    double x3 = (x * ((2 * range) / pwidth)) - range;
                    double y3 = (y * ((2 * range) / pheight)) - range;
                    int finalWeight;
                    if (typeOfFractal == 0) {
                        finalWeight = evaluatePoint(0, 0, 0, branchIteration, 0, x3, y3);
                    } else {
                        finalWeight = evaluatePointj(x3, y3, 0, branchIteration, 0, juliac, juliaci);
                    }

                    image[x][y] = finalWeight;
                    if (finalWeight > biggestfinalWeight) {
                        biggestfinalWeight = finalWeight;
                    }
                }
            }
            int[][] newimage = new int[pwidth][pheight];

            for (int x = 1; x < pwidth - 1; x++) {
                for (int y = 1; y < pheight - 1; y++) {

                    newimage[x][y] = 0;

                    if ((image[x][y] - image[x - 1][y]) != 0) {
                        newimage[x][y]++;
                    }
                    if ((image[x][y] - image[x + 1][y]) != 0) {
                        newimage[x][y]++;
                    }
                    if ((image[x][y] - image[x][y - 1]) != 0) {
                        newimage[x][y]++;
                    }
                    if ((image[x][y] - image[x][y + 1]) != 0) {
                        newimage[x][y]++;
                    }
                    if (newimage[x][y] > 0) {
                        newimage[x][y]++;
                    }

                }
            }
            for (int x = 0; x < pwidth; x++) {
                for (int y = 0; y < pheight; y++) {
                    double x3 = (x * ((2 * range) / pwidth)) - range;
                    double y3 = (y * ((2 * range) / pheight)) - range;
                    int finalWeight;
                    if (typeOfFractal == 0) {
                        finalWeight = evaluatePoint(0, 0, 0, branchIteration, 0, x3, y3);
                    } else {
                        finalWeight = evaluatePointj(x3, y3, 0, branchIteration, 0, juliac, juliaci);
                    }

                    if (newimage[x][y] == 0) {
                        newimage[x][y] = (finalWeight);
                    } else {
                        newimage[x][y] = newimage[x][y] + (finalWeight * 5);
                    }
                }
            }
            for (int x = 0; x < pwidth; x++) {
                for (int y = 0; y < pheight; y++) {


                    if (newimage[x][y] == 0) {
                        g.setColor(Color.BLACK);
                    } else if (newimage[x][y] == 1) {
                        g.setColor(new Color(0.65f, 0f, 0f));
                    } else if (newimage[x][y] == 2) {
                        g.setColor(new Color(0f, 0.075f, 0.25f));
                    } else if (newimage[x][y] == 3) {
                        g.setColor(new Color(0f, 0.2f, 0.5f));
                    } else if (newimage[x][y] == 4) {
                        g.setColor(new Color(0f, 0.4f, 0.6f));
                    } else if (newimage[x][y] == 5) {
                        g.setColor(new Color(0f, 0.5f, 0.7f));
                    } else if (newimage[x][y] >= 6) {
                        g.setColor(new Color(0.8f, 0f, 0.3f));
                    }

                    g.drawRect(x, y, 1, 1);
                }
            }

        }


        if (typeOfFractal == 0) {
            leftimg = tempimg;
        } else {
            rightimg = tempimg;
        }


    }

    public double nthroot(int n, double A) {
        return nthroot(n, A, .001);
    }

    public double nthroot(int n, double A, double p) {
        if (A < 0) {
            System.err.println("A < 0");// we handle only real positive numbers
            return -1;
        } else if (A == 0) {
            return 0;
        }
        double x_prev = A;
        double x = A / n;  // starting "guessed" value...
        while (Math.abs(x - x_prev) > p) {
            x_prev = x;
            x = ((n - 1.0) * x + A / Math.pow(x, n - 1.0)) / n;
        }
        return x;
    }

    private int evaluatePointj(double x, double y, int currentDepth, int maxDepth, int total, double c, double ci) {


        double magnitude = (x * x + y * y);

        if (magnitude > 16) {
            return 0;
        } else if (currentDepth == maxDepth) {
            return 1;
        } else {

            magnitude = Math.sqrt(magnitude);


            double angle = Math.atan2(y, x);

            double xSquared = Math.pow(magnitude, basenumber) * (Math.cos((angle * (double) (basenumber))));
            double ySquared = Math.pow(magnitude, basenumber) * (Math.sin((angle * (double) (basenumber))));

            double newx = Math.pow(magnitude, basenumerinumber) * (Math.cos((angle * (double) (basenumerinumber))));
            double newy = Math.pow(magnitude, basenumerinumber) * (Math.sin((angle * (double) (basenumerinumber))));

            angle = Math.atan2(newy, newx);

            double magnituderoot = nthroot(baserecinumber, Math.sqrt(newx * newx + newy * newy));


            for (int i = 0; i < ((version == 2) ? (1) : (baserecinumber)); i++) {


                double rootx = magnituderoot * (Math.cos((angle + i * 2f * Math.PI) / (double) (baserecinumber)));
                double rooty = magnituderoot * (Math.sin((angle + i * 2f * Math.PI) / (double) (baserecinumber)));

                double x1 = (xSquared * rootx - ySquared * rooty) + c;
                double y1 = (xSquared * rooty + rootx * ySquared) + ci;

                total += evaluatePoint(x1, y1, currentDepth + 1, maxDepth, 0, c, ci);
            }


            return total;
        }
    }

    private int evaluatePoint(double x, double y, int currentDepth, int maxDepth, int total, double c, double ci) {


        //Two new points


        double magnitude = (x * x + y * y);

        if (magnitude > 64) {
            return 0;
        } else if (currentDepth == maxDepth) {
            return 1;
        } else {

            magnitude = Math.sqrt(magnitude);


            double angle = Math.atan2(y, x);

            double xSquared = Math.pow(magnitude, basenumber) * (Math.cos((angle * (double) (basenumber))));
            double ySquared = Math.pow(magnitude, basenumber) * (Math.sin((angle * (double) (basenumber))));

            double newx = Math.pow(magnitude, basenumerinumber) * (Math.cos((angle * (double) (basenumerinumber))));
            double newy = Math.pow(magnitude, basenumerinumber) * (Math.sin((angle * (double) (basenumerinumber))));

            angle = Math.atan2(newy, newx);

            double magnituderoot = nthroot(baserecinumber, Math.sqrt(newx * newx + newy * newy));


            for (int i = 0; i < ((version == 2) ? (1) : (baserecinumber)); i++) {


                double rootx = magnituderoot * (Math.cos((angle + i * 2f * Math.PI) / (double) (baserecinumber)));
                double rooty = magnituderoot * (Math.sin((angle + i * 2f * Math.PI) / (double) (baserecinumber)));

                double x1 = (xSquared * rootx - ySquared * rooty) + c;
                double y1 = (xSquared * rooty + rootx * ySquared) + ci;

                total += evaluatePoint(x1, y1, currentDepth + 1, maxDepth, 0, c, ci);
            }


            return total;
        }
    }


    public void paintComponent(Graphics g) {
        //g.drawImage(img, 0, 0, null);

    }


    public static double nthroot2(int n, double A) {
        return nthroot2(n, A, .001);
    }

    public static double nthroot2(int n, double A, double p) {
        if (A < 0) {
            System.err.println("A < 0");// we handle only real positive numbers
            return -1;
        } else if (A == 0) {
            return 0;
        }
        double x_prev = A;
        double x = A / n;  // starting "guessed" value...
        while (Math.abs(x - x_prev) > p) {
            x_prev = x;
            x = ((n - 1.0) * x + A / Math.pow(x, n - 1.0)) / n;
        }
        return x;
    }

}