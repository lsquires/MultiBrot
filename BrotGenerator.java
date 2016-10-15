import java.awt.*;

/**
 * Created by Home on 03/03/2015.
 */
public class BrotGenerator implements Runnable {


    private final GUI gui;
    private long thisID;
    public int threads;
    public int activethreads;
    public int[][] image;
    public int[][] principalimage;
    public Graphics g;
    public int finished;
    public int pointsDone;
    public long timestamp;
    private float finalWeight;

    public BrotGenerator(GUI a, int t) {
        gui = a;
        threads = t;
        timestamp = System.currentTimeMillis();
        finalWeight = 1;
    }

    @Override
    public void run() {
        pointsDone = 0;
        int pwidth = gui.brot.size;


        gui.brot.remakeImage();
        double pw2 = pwidth/2f;
        gui.progressBar1.setMinimum(0);
        gui.progressBar1.setValue(pointsDone);
        gui.progressBar1.setMaximum(pwidth);
        image = new int[pwidth][pwidth];
        principalimage = new int[pwidth][pwidth];
        final int typeOfRender = gui.colourSchemeCombo.getSelectedIndex();
        int typeOfColour = gui.colourActualCombo.getSelectedIndex();
        g = gui.brot.imageGraphics;
        finished = 0;
        for(int i = 0 ; i < threads;i++) {
            final int y = i;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    generateBrotFractal((float)(y)/(float)(threads),(float)(y+1)/(float)(threads),(typeOfRender==4 || typeOfRender==5));
                }
            }).start();
        }
        double escape1 =(Double)gui.escaperadiusspinner5.getValue();

        int baserecinumber = (Integer)gui.qspinner3.getValue();
        int branchIteration = (Integer)gui.iterationspinner4.getValue();
        boolean abort = false;

        while(!abort) {
            gui.progressBar1.setValue(pointsDone);
            if (gui.currentThread.getId() != thisID) {
                abort = true;
            }
            if(finished==threads) {
                abort = true;
            }
            float biggestfinalWeight = (float) Math.pow(baserecinumber, branchIteration-1);
            if(typeOfRender==0) {
                finalWeight = (int)Math.pow(baserecinumber, branchIteration-1);

            }else
            if(typeOfRender==1) {
                finalWeight = branchIteration;
                biggestfinalWeight = branchIteration-1;
            }
                for (int x = 0; x < pwidth; x++) {

                    for (int y = 0; y < pwidth; y++) {


                        //All the different colour options for each renderer
                        if(typeOfRender==0) {
                            if (image[x][y] == 0) {
                                g.setColor(Color.BLACK);
                            } else {
                                if(typeOfColour==0) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, ((image[x][y] / biggestfinalWeight) * 2.5f + 0.4f))),
                                            Math.min(1, Math.max(0, (image[x][y] / biggestfinalWeight))),
                                            Math.min(1, Math.max(0, ((((image[x][y])) / biggestfinalWeight)) * 2f - 0.5f))));
                                }else
                                if(typeOfColour==1) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, Math.abs(2*((((image[x][y]+121)%(finalWeight)+1f)) / ((finalWeight) +1f))-1f)   )),
                                            Math.min(1, Math.max(0, Math.abs(2 * ((((image[x][y]+54) % ((finalWeight/23f)) + 1f)) / ((finalWeight/23f) +1f))-1f ))),
                                            Math.min(1, Math.max(0, Math.abs(2*(((image[x][y] % ((3f)) / ((3f) +1f)))   )-1f)))));
                                }
                            }

                        }else
                        if(typeOfRender==1) {
                            if (image[x][y] == branchIteration+1) {
                                g.setColor(Color.BLACK);
                            } else {
                                if(typeOfColour==0) {
                                g.setColor(new Color(
                                        Math.min(1, Math.max(0, ((image[x][y] / biggestfinalWeight) * 2.5f + 0.4f))),
                                        Math.min(1, Math.max(0, ((biggestfinalWeight-image[x][y]) / biggestfinalWeight) *2-2f)),
                                                Math.min(1, Math.max(0, ((((image[x][y])) / biggestfinalWeight)) * 2f - 0.5f))));
                                }else
                                if(typeOfColour==1) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, Math.abs(2*((((image[x][y]+121)%(biggestfinalWeight)+1f)) / ((biggestfinalWeight) +1f))-1f)   )),
                                            Math.min(1, Math.max(0, Math.abs(2 * ((((image[x][y]+54) % ((biggestfinalWeight/2f)) + 1f)) / ((biggestfinalWeight/2f) +1f))-1f ))),
                                            Math.min(1, Math.max(0, Math.abs(2*(((image[x][y] % (((biggestfinalWeight/5f)) + 1f) / (((biggestfinalWeight/5f)) +1f)))   )-1f)))));
                                }
                            }
                        }else
                        if(typeOfRender==2) {

                            if (image[x][y] == -1) {
                                g.setColor(Color.BLACK);
                            } else {
                                if(typeOfColour==0) {
                                g.setColor(new Color(
                                        Math.min(1, Math.max(0, (((float)(image[x][y]) / (finalWeight+1f)) * 2.5f + 0.4f))),
                                        Math.min(1, Math.max(0, ((float) (image[x][y]) / (finalWeight + 1f)))),
                                                Math.min(1, Math.max(0, (((((float) (image[x][y]))) / (finalWeight + 1f))) * 2f - 0.5f))));
                                }else
                                if(typeOfColour==1) {
                                    //System.out.println(image[x][y]);
                                    g.setColor(new Color(

                                            Math.min(1, Math.max(0, Math.abs(2*((((image[x][y]+121f)%(finalWeight)+1f)) / ((finalWeight) +1f))-1f)   )),
                                            Math.min(1, Math.max(0, Math.abs(2 * ((((image[x][y]+54f) % ((finalWeight/3f)) + 1f)) / ((finalWeight/3f) +1f))-1f ))),
                                            Math.min(1, Math.max(0, Math.abs(2 * (((image[x][y] % ((finalWeight / 7f) + 1f) / ((finalWeight / 7f) + 1f)))) - 1f)))));
                                }
                            }
                        }else
                        if(typeOfRender==3 && x>0 && y>0 && x<pwidth-1 && y<pwidth-1) {

                            int value = 0;
                            if((image[x][y]-image[x-1][y])!=0) {
                                value++;
                            }
                            if((image[x][y]-image[x+1][y])!=0) {
                                value++;
                            }
                            if((image[x][y]-image[x][y-1])!=0) {
                                value++;
                            }
                            if((image[x][y]-image[x][y+1])!=0) {
                                value++;
                            }
                            if (value == 0) {
                                g.setColor(Color.BLACK);
                            } else {
                                if(typeOfColour==0) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, ((value / 3f) * 2.5f + 0.4f))),
                                            Math.min(1, Math.max(0, (value / 3f))),
                                            Math.min(1, Math.max(0, ((((value)) / 3f)) * 2f - 0.5f))));
                                }else
                                if(typeOfColour==1) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, Math.abs(2*((((value+121)%(3f)+1f)) / ((3f) +1f))-1f)   )),
                                            Math.min(1, Math.max(0, Math.abs(2 * ((((value+54) % ((3f/23f)) + 1f)) / ((3f/23f) +1f))-1f ))),
                                            Math.min(1, Math.max(0, Math.abs(2*(((value % ((3f)) / ((3f) +1f)))   )-1f)))));
                                }
                            }
                        }else
                        if((typeOfRender==4 || typeOfRender==5) && x>0 && y>0 && x<pwidth-1 && y<pwidth-1) {

                            int value = 0;
                            if((image[x][y]-image[x-1][y])!=0) {
                                value++;
                            }
                            if((image[x][y]-image[x+1][y])!=0) {
                                value++;
                            }
                            if((image[x][y]-image[x][y-1])!=0) {
                                value++;
                            }
                            if((image[x][y]-image[x][y+1])!=0) {
                                value++;
                            }

                            if (principalimage[x][y] !=0) {

                                if(typeOfColour==0) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, ((value / 9f) + 0.6f))),
                                            Math.min(1, Math.max(0, (value / 9f))),
                                            Math.min(1, Math.max(0, ((((value)) / 9f)) - 0.5f))));
                                }else
                                if(typeOfColour==1) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, ((value / 9f) + 0.6f))),
                                            Math.min(1, Math.max(0, (value / 9f))),
                                            Math.min(1, Math.max(0, ((((value)) / 9f)) - 0.5f))));
                                }
                            } else
                            if (value == 0) {
                                g.setColor(Color.BLACK);
                            }else
                                 {
                                if(typeOfColour==0) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, ((value / 3f) * 2.5f + 0.4f))),
                                            Math.min(1, Math.max(0, (value / 3f))),
                                            Math.min(1, Math.max(0, ((((value)) / 3f)) * 2f - 0.5f))));
                                }else
                                if(typeOfColour==1) {
                                    g.setColor(new Color(
                                            Math.min(1, Math.max(0, Math.abs(2*((((value+121)%(3f)+1f)) / ((3f) +1f))-1f)   )),
                                            Math.min(1, Math.max(0, Math.abs(2 * ((((value+54) % ((3f/23f)) + 1f)) / ((3f/23f) +1f))-1f ))),
                                            Math.min(1, Math.max(0, Math.abs(2*(((value % ((3f)) / ((3f) +1f)))   )-1f)))));
                                }
                            }
                        }
                        g.drawRect(x, y, 1, 1);

                    }
                }

                g.setColor(Color.RED);
                int size = (int) (((escape1 - gui.xoffset) * pw2) / gui.range) - (int) (((-gui.xoffset) * pw2) / gui.range);
                g.drawOval((int) ((((-gui.xoffset) * pw2) / gui.range) + pw2 - size), (int)(pw2 - (int) (((-gui.yoffset) * pw2) / gui.range) - size), size * 2, size * 2);
                gui.progressBar1.setValue(pwidth);
                gui.brot.repaint();



        }



        timestamp = System.currentTimeMillis()-timestamp;
        gui.threadsLabel.setText("Threads: " + ((timestamp) / 1000f) + "s");
    }


    private void generateBrotFractal(float start, float end,boolean principal) {

        activethreads = 0;
        int pwidth = gui.brot.size;


        //gui.brot.remakeImage(pwidth);
        boolean abort = false;

        float biggestfinalWeight = 0;
        double range = (Double)gui.renderradiusspinner1.getValue();

        int typeOfFractal = 0; //FIX

        double escape1 =(Double)gui.escaperadiusspinner5.getValue();
        double escape = escape1*escape1;
        int basenumerinumber =(Integer)gui.pspinner2.getValue();
        int baserecinumber = (Integer)gui.qspinner3.getValue();
        int branchIteration = (Integer)gui.iterationspinner4.getValue();

        for(int x = (int)(pwidth*start) ; x < pwidth*end;x++)
        {
               if(gui.currentThread.getId() != thisID)
               {
                   break;
               }
            pointsDone++;
            for(int y = 0 ; y < pwidth;y++)
            {
                double x3 = (x * ((2*range) / pwidth)) - range + gui.xoffset;
                double y3 = (y * ((2*range) / pwidth)) - range - gui.yoffset;
               

                if(true || typeOfFractal==0) //FIX
                {

                            int typeOfRender = gui.colourSchemeCombo.getSelectedIndex();
                            int f = evaluatePoint(escape, x3, y3, 1, branchIteration, 0, x3, y3, basenumerinumber, baserecinumber,typeOfRender, false,null);
                            if((typeOfRender==2 && f!= -1))
                                {
                                    f = f*baserecinumber;
                                }
                              image[x][y]= f;
                            if(principal) {

                                int[] principalPath = new int[branchIteration];
                                if(typeOfRender==4)
                                {
                                    for(int i = 0 ; i < branchIteration;i++)
                                        {
                                            principalPath[i] = 1;
                                        }
                                }else
                                if(typeOfRender==5)
                                {
                                    String[] path = (((String)gui.customBranchcomboBox1.getSelectedItem()).substring(1)+",").split(",");
                                    principalPath[0] = 1;
                                    for(int i = 0 ; i < branchIteration-1;i++)
                                        {
                                            principalPath[i+1] = Integer.parseInt(path[i].replaceAll(",","").trim());
                                        }

                                }

                                int f2 = evaluatePoint(escape, 0, 0, 0, branchIteration, 0, x3, y3, basenumerinumber, baserecinumber, typeOfRender, true,principalPath);
                                principalimage[x][y]= f2;
                            }

                            if(f>finalWeight)
                            {
                                finalWeight = f;
                            }



                }   else
                {
                    //finalWeight = evaluatePointj(x3, y3, 0, branchIteration, 0, juliac, juliaci);
                }



            }
        }


        finished++;
    }


    private int evaluatePoint(double escape,double x, double y, int currentDepth, int maxDepth,int total,double c,double ci,int basenumerinumber, int baserecinumber,int typeofRender,boolean principal, int[] principalPath) {


        double magnitude = (x * x + y * y);

        if(magnitude>escape)
        {

            if(typeofRender==0 || typeofRender==3 || typeofRender==4 || typeofRender==5) {
                return 0;
            }else
            if(typeofRender==1) {
                return (currentDepth);
            }else
            {
                return (baserecinumber*currentDepth);
            }
        }else
        if(currentDepth==maxDepth)
        {
            if(typeofRender==0|| typeofRender==3 || typeofRender==4 || typeofRender==5) {
                return 1;
            }else
            if(typeofRender==1) {
                return maxDepth+1;
            }else
            {
                return -1;
            }

        }else
        {

            magnitude = Math.sqrt(magnitude);


            double angle = Math.atan2(y, x);


            double newx = Math.pow(magnitude,basenumerinumber)*(Math.cos((angle*(double)(basenumerinumber))));
            double newy = Math.pow(magnitude,basenumerinumber)*(Math.sin((angle*(double)(basenumerinumber))));

            angle = Math.atan2(newy, newx);

            double magnituderoot = nthroot2(baserecinumber,Math.sqrt(newx*newx+newy*newy));

            for(int i = ((principal)?(principalPath[currentDepth]-1):(0)) ; i < ((principal)?(principalPath[currentDepth]):(baserecinumber));i++)
            {
                double rootx = magnituderoot*(Math.cos((angle+i*2f*Math.PI)/(double)(baserecinumber)))+c;
                double rooty = magnituderoot*(Math.sin((angle+i*2f*Math.PI)/(double)(baserecinumber)))+ci;

                //double x1=(xSquared*rootx-ySquared*rooty) + c;
                //double y1=(xSquared*rooty+rootx*ySquared) + ci;

                if(typeofRender==0|| typeofRender==3 || typeofRender==4 || typeofRender==5) {
                    total += evaluatePoint(escape,rootx,rooty,currentDepth+1,maxDepth,0,c,ci,basenumerinumber,baserecinumber,typeofRender,principal,principalPath);
                }else
                if(typeofRender==1) {
                    int point = evaluatePoint(escape,rootx,rooty,currentDepth+1,maxDepth,0,c,ci,basenumerinumber,baserecinumber,typeofRender,principal,principalPath);
                    if(point>total)
                        {
                            total = point;
                        }

                }else
                {
                    if( total != -1) {
                        int point = evaluatePoint(escape, rootx, rooty, currentDepth + 1, maxDepth, 0, c, ci, basenumerinumber, baserecinumber, typeofRender,principal,principalPath);
                        if (point == -1) {
                            total = -1;
                        } else {
                            total += (point);
                        }
                    }
                }

            }


            return total;
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
}
