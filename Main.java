import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * Created by Home on 15/01/2015.
*/
public class Main extends JPanel{

    private static JFrame frame;
    //public Image img;


    public static BufferedImage leftimg;
    public static BufferedImage rightimg;

    public  int basenumber;
    public  int basenumerinumber;
    public  int baserecinumber;
    public int branchIteration;
    public  int[][] image;
    public  static int pwidth;
    public static int pheight;
   // public int progressNo;
    public float range;
   // public boolean branchCutVersion;
    public int version;
    public String dir;
    public static int lastIter;
    public int typeOfFractal = 0; // 0 = mandelbrot, 1 = julia
    public double juliac = 0;
    public double juliaci = 0;



    /*public int[][] numbersToDo = new int[][]{
            {1,2},
            {1,3},
            {2,3},
            {1,4},
            {3,4},
            {1,5},
            {2,5},
            {3,5},
            {4,5},
            {1,6},
            {5,6},
            {1,7},
            {2,7},
            {3,7},
            {4,7},
            {5,7},
            {6,7},
            {1,8},
            {3,8},
            {5,8},
            {7,8}};

    public static double[][] jnumbersToDo = new double[4000][2];
*/

    public Main(int type,int basen,int basenumer,int basedem, int iter,int ra, int typeOfRender,double jc,double jci) {
        typeOfFractal=type;

        basenumber=basen;
        baserecinumber = basedem;
        basenumerinumber = basenumer;
        branchIteration = iter;
        range =ra;

        juliac = jc;
        juliaci = jci;

        version = typeOfRender; //0=normal, 1 = edges, 2=diff

        if(typeOfFractal==0) {
            //dir = "MultiBrots\\";
            //File f2 = new File("E:\\HyperBrot\\Renderings\\"+dir);
            //f2.mkdirs();
            //progressNo = 0;
            //branchCutVersion = false;
            //basenumber = 2;
           // range = 2f;
            //basenumerinumber = numbersToDo[progressNo][0];
            //baserecinumber = numbersToDo[progressNo][1];
            //branchIteration = 1;
            //while (basenumber <= 1) {
                //branchIteration++;
                redo();
                repaint();

                /*if (branchCutVersion) {
                    branchCutVersion = false;
                    progressNo = 0;
                    if (progressNo > 2) {
                        progressNo = i;
                        basenumber = basenumber + 1;
                    }
                    basenumerinumber = numbersToDo[progressNo][0];
                    baserecinumber = numbersToDo[progressNo][1];
                    branchIteration = 1;
                } else if (branchIteration >= 6) {
                    branchCutVersion = true;
                    //branchIteration=50;
                }*/
           // }

        }else
        {
            //progressNo = i;
            //branchCutVersion = true;
            //basenumber = 2;
            //range = 2f;

            //basenumerinumber = 1;
            //baserecinumber = 2;
           // branchIteration = 8;
            //juliac = jnumbersToDo[progressNo][0];
            //juliaci = jnumbersToDo[progressNo][1];
            //DecimalFormat oneDigit = new DecimalFormat("#.##");
            //dir = "Julia\\"+oneDigit.format(juliac)+","+oneDigit.format(juliaci)+" ";
           // File f2 = new File("E:\\HyperBrot\\Renderings\\"+dir);
            //f2.mkdirs();
            //while (progressNo <= lastIter) {

                //branchIteration++;
                redo();
                repaint();

                /*if (branchCutVersion) {
                    branchCutVersion = true;
                    progressNo = progressNo+3;
                    if (progressNo > lastIter) {
                       break;
                    }
                    basenumerinumber = 1;
                    baserecinumber = 2;
                    juliac = jnumbersToDo[progressNo][0];
                    juliaci = jnumbersToDo[progressNo][1];
                    branchIteration = 8;
                    dir = "Julia\\"+oneDigit.format(juliac)+","+oneDigit.format(juliaci)+" ";
                   // File f3 = new File("E:\\HyperBrot\\Renderings\\"+dir);
                    //f3.mkdirs();
                } else if (branchIteration >= 8) {
                    branchCutVersion = true;
                    //branchIteration=50;
                }*/

        }



    }

    public void redo()
    {
        BufferedImage tempimg = null;
        if(typeOfFractal==0)
            {
                tempimg=leftimg;
            }   else
            {
                tempimg=rightimg;
            }







        //histogram = new float[histSize];
       // System.out.println("Running... "+progressNo+"    "+juliac+","+juliaci+"    depth"+branchIteration);

        //boolean branch = branchCutVersion;
       // if(branch)
            //{
                //branchCutVersion=false;
            //}

        if(version==0)
        {
            image = new int[pwidth][pheight];
            float biggestfinalWeight = 0;
            Graphics g = tempimg.getGraphics();
            for(int x = 0 ; x < pwidth;x++)
            {
                for(int y = 0 ; y < pheight;y++)
                {
                    double x3 = (x * ((2*range) / pwidth)) - range;
                    double y3 = (y * ((2*range) / pheight)) - range;
                    int finalWeight;
                    if(typeOfFractal==0)
                    {
                        finalWeight = evaluatePoint(0,0,0,branchIteration,0,x3,y3);
                    }   else
                    {
                        finalWeight = evaluatePointj(x3, y3, 0, branchIteration, 0, juliac, juliaci);
                    }

                    image[x][y]= finalWeight;
                    if( finalWeight>biggestfinalWeight) {
                        biggestfinalWeight=finalWeight;
                    }
                }
            }

            for(int x = 0 ; x < pwidth;x++) {
                for (int y = 0; y < pheight; y++) {



                    if(image[x][y]==0)
                    {
                        g.setColor(Color.BLACK);
                    }else {
                        g.setColor(new Color(
                                Math.min(1, Math.max(0, ((image[x][y]/biggestfinalWeight) * 2.5f + 0.4f))),
                                Math.min(1, Math.max(0,(image[x][y]/biggestfinalWeight))),
                                Math.min(1, Math.max(0, ((((image[x][y]))/biggestfinalWeight))*2f -0.5f))));
                    }

                    g.drawRect(x,y,1,1);
                }
            }


        }else
          if(version==1) {

              image = new int[pwidth][pheight];
              float biggestfinalWeight = 0;
              Graphics g = tempimg.getGraphics();
              for(int x = 0 ; x < pwidth;x++)
              {
                  for(int y = 0 ; y < pheight;y++)
                  {
                      double x3 = (x * ((2*range) / pwidth)) - range;
                      double y3 = (y * ((2*range) / pheight)) - range;
                      int finalWeight;
                      if(typeOfFractal==0)
                      {
                          finalWeight = evaluatePoint(0,0,0,branchIteration,0,x3,y3);
                      }   else
                      {
                          finalWeight = evaluatePointj(x3, y3, 0, branchIteration, 0, juliac, juliaci);
                      }

                      image[x][y]= finalWeight;
                      if( finalWeight>biggestfinalWeight) {
                          biggestfinalWeight=finalWeight;
                      }
                  }
              }

              int[][] newimage = new int[pwidth][pheight];

              for(int x = 1 ; x < pwidth-1;x++) {
                  for (int y = 1; y < pheight-1; y++) {

                      newimage[x][y]=0;

                      if((image[x][y]-image[x-1][y])!=0) {
                          newimage[x][y]++;
                      }
                      if((image[x][y]-image[x+1][y])!=0) {
                          newimage[x][y]++;
                      }
                      if((image[x][y]-image[x][y-1])!=0) {
                          newimage[x][y]++;
                      }
                      if((image[x][y]-image[x][y+1])!=0) {
                          newimage[x][y]++;
                      }
                      if(newimage[x][y]>0)
                      {
                          newimage[x][y]++;
                      }

                  }
              }
              for(int x = 0 ; x < pwidth;x++) {
                  for (int y = 0; y < pheight; y++) {



                      if(newimage[x][y]==0)
                      {
                          g.setColor(Color.BLACK);
                      }else
                      if(newimage[x][y]==2)
                      {
                          g.setColor(new Color(0f,0.075f,0.25f));
                      }else
                      if(newimage[x][y]==3)
                      {
                          g.setColor(new Color(0f,0.2f,0.5f));
                      }else
                      if(newimage[x][y]==4)
                      {
                          g.setColor(new Color(0f,0.4f,0.6f));
                      }else
                      if(newimage[x][y]==5)
                      {
                          g.setColor(new Color(0f,0.5f,0.7f));
                      }


                      g.drawRect(x,y,1,1);
                  }
              }

          }else
          if(version==2)
          {
              image = new int[pwidth][pheight];
              float biggestfinalWeight = 0;
              Graphics g = tempimg.getGraphics();
              for(int x = 0 ; x < pwidth;x++)
              {
                  for(int y = 0 ; y < pheight;y++)
                  {
                      double x3 = (x * ((2*range) / pwidth)) - range;
                      double y3 = (y * ((2*range) / pheight)) - range;
                      int finalWeight;
                      if(typeOfFractal==0)
                      {
                          finalWeight = evaluatePoint(0,0,0,branchIteration,0,x3,y3);
                      }   else
                      {
                          finalWeight = evaluatePointj(x3, y3, 0, branchIteration, 0, juliac, juliaci);
                      }

                      image[x][y]= finalWeight;
                      if( finalWeight>biggestfinalWeight) {
                          biggestfinalWeight=finalWeight;
                      }
                  }
              }
              int[][] newimage = new int[pwidth][pheight];

              for(int x = 1 ; x < pwidth-1;x++) {
                  for (int y = 1; y < pheight-1; y++) {

                      newimage[x][y]=0;

                      if((image[x][y]-image[x-1][y])!=0) {
                          newimage[x][y]++;
                      }
                      if((image[x][y]-image[x+1][y])!=0) {
                          newimage[x][y]++;
                      }
                      if((image[x][y]-image[x][y-1])!=0) {
                          newimage[x][y]++;
                      }
                      if((image[x][y]-image[x][y+1])!=0) {
                          newimage[x][y]++;
                      }
                      if(newimage[x][y]>0)
                      {
                          newimage[x][y]++;
                      }

                  }
              }
              for(int x = 0 ; x < pwidth;x++)
              {
                  for(int y = 0 ; y < pheight;y++)
                  {
                      double x3 = (x * ((2*range) / pwidth)) - range;
                      double y3 = (y * ((2*range) / pheight)) - range;
                      int finalWeight;
                      if(typeOfFractal==0)
                      {
                          finalWeight = evaluatePoint(0,0,0,branchIteration,0,x3,y3);
                      }   else
                      {
                          finalWeight = evaluatePointj(x3,y3,0,branchIteration,0,juliac,juliaci);
                      }

                      if(newimage[x][y]==0)
                      {
                          newimage[x][y]=(finalWeight);
                      }else
                      {
                          newimage[x][y]=newimage[x][y]+(finalWeight*5);
                      }
                  }
              }
              for(int x = 0 ; x < pwidth;x++) {
                  for (int y = 0; y < pheight; y++) {



                      if(newimage[x][y]==0)
                      {
                          g.setColor(Color.BLACK);
                      }else
                      if(newimage[x][y]==1)
                      {
                          g.setColor(new Color(0.65f,0f,0f));
                      }else
                      if(newimage[x][y]==2)
                      {
                          g.setColor(new Color(0f,0.075f,0.25f));
                      }else
                      if(newimage[x][y]==3)
                      {
                          g.setColor(new Color(0f,0.2f,0.5f));
                      }else
                      if(newimage[x][y]==4)
                      {
                          g.setColor(new Color(0f,0.4f,0.6f));
                      }else
                      if(newimage[x][y]==5)
                      {
                          g.setColor(new Color(0f,0.5f,0.7f));
                      }else
                      if(newimage[x][y]>=6)
                      {
                          g.setColor(new Color(0.8f,0f,0.3f));
                      }

                      g.drawRect(x,y,1,1);
                  }
              }

          }



        if(typeOfFractal==0)
        {
           leftimg=tempimg;
        }   else
        {
          rightimg=  tempimg;
        }


    }

    public  double nthroot(int n, double A) {
        return nthroot(n, A, .001);
    }
    public  double nthroot(int n, double A, double p) {
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

    private int evaluatePointj(double x, double y, int currentDepth, int maxDepth,int total,double c,double ci) {


        double magnitude = (x * x + y * y);

        if(magnitude>16)
        {
            return 0;
        }else
        if(currentDepth==maxDepth)
        {
            return 1;
        }else
        {

            magnitude = Math.sqrt(magnitude);


            double angle = Math.atan2(y, x);

            double xSquared = Math.pow(magnitude,basenumber)*(Math.cos((angle*(double)(basenumber))));
            double ySquared = Math.pow(magnitude,basenumber)*(Math.sin((angle*(double)(basenumber))));

            double newx = Math.pow(magnitude,basenumerinumber)*(Math.cos((angle*(double)(basenumerinumber))));
            double newy = Math.pow(magnitude,basenumerinumber)*(Math.sin((angle*(double)(basenumerinumber))));

            angle = Math.atan2(newy, newx);

            double magnituderoot = nthroot(baserecinumber,Math.sqrt(newx*newx+newy*newy));



            for(int i = 0 ; i < ((version==2)?(1):(baserecinumber));i++)
            {


                double rootx = magnituderoot*(Math.cos((angle+i*2f*Math.PI)/(double)(baserecinumber)));
                double rooty = magnituderoot*(Math.sin((angle+i*2f*Math.PI)/(double)(baserecinumber)));

                double x1=(xSquared*rootx-ySquared*rooty) + c;
                double y1=(xSquared*rooty+rootx*ySquared) + ci;

                total += evaluatePoint(x1,y1,currentDepth+1,maxDepth,0,c,ci);
            }


            return total;
        }
    }

    private int evaluatePoint(double x, double y, int currentDepth, int maxDepth,int total,double c,double ci) {



        //Two new points



        double magnitude = (x * x + y * y);

        if(magnitude>64)
        {
            return 0;
        }else
        if(currentDepth==maxDepth)
        {
            return 1;
        }else
        {

            magnitude = Math.sqrt(magnitude);


            double angle = Math.atan2(y, x);

            double xSquared = Math.pow(magnitude,basenumber)*(Math.cos((angle*(double)(basenumber))));
            double ySquared = Math.pow(magnitude,basenumber)*(Math.sin((angle*(double)(basenumber))));

            double newx = Math.pow(magnitude,basenumerinumber)*(Math.cos((angle*(double)(basenumerinumber))));
            double newy = Math.pow(magnitude,basenumerinumber)*(Math.sin((angle*(double)(basenumerinumber))));

            angle = Math.atan2(newy, newx);

            double magnituderoot = nthroot(baserecinumber,Math.sqrt(newx*newx+newy*newy));



            for(int i = 0 ; i < ((version==2)?(1):(baserecinumber));i++)
                {


                    double rootx = magnituderoot*(Math.cos((angle+i*2f*Math.PI)/(double)(baserecinumber)));
                    double rooty = magnituderoot*(Math.sin((angle+i*2f*Math.PI)/(double)(baserecinumber)));

                    double x1=(xSquared*rootx-ySquared*rooty) + c;
                    double y1=(xSquared*rooty+rootx*ySquared) + ci;

                    total += evaluatePoint(x1,y1,currentDepth+1,maxDepth,0,c,ci);
                }


            return total;
        }
    }

    private String generateNumber(float i) {


        if(i<10)
        {
            return "000"+i;
        }else
        if(i<100)
        {
            return "00"+i;
        }else
        if(i<1000)
        {
            return "0"+i;
        }else
        {
            return ""+i;
        }
    }

    private String generat(float i) {


        if(i<10)
        {
            return "0"+i;
        }else
        {
            return ""+i;
        }
    }



    public void paintComponent(Graphics g) {
        //g.drawImage(img, 0, 0, null);

    }





    //public static void main(String[] args) {
       /*File folder = new File("E:\\HyperBrot\\Renderings\\MultiBrots\\");

        for(int i = 0 ; i < folder.listFiles().length;i++)
            {
                File filk = folder.listFiles()[i];
                System.out.println(filk.getName());
                if(!filk.getName().startsWith("1")) {

                    try {
                        File newFile = new File("E:\\HyperBrot\\Renderings\\MultiBrots\\0"+filk.getName());
                        //newFile.mkdir();
                        //System.out.println( newFile.createNewFile());
                        FileOutputStream  out = new FileOutputStream(newFile);
                        byte[] bFile = new byte[(int) filk.length()];
                        FileInputStream  in = new FileInputStream(new File("E:\\HyperBrot\\Renderings\\MultiBrots\\"+filk.getName()));
                        in.read(bFile);
                        out.write(bFile);
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }*/

/*
        int i3=0;
        DecimalFormat oneDigit = new DecimalFormat("#.##");

        for(int i = 0 ; i < 4;i++) {
            BufferedImage img = null;
            int dx=0;
            int dy=0;
            int maxx = 20;
            int maxy = 20;
            if(i==0) {
                maxx = 21;
                maxy = 21;
            }else
            if(i==1) {
                maxx = 20;
                maxy = 21;
                dx= 1;
            }else
            if(i==2) {
                maxx = 21;
                maxy = 20;
                dy=1;
            }else
            if(i==3) {
                maxx = 20;
                maxy = 20;
                dx= 1;
                dy=1;
            }
            System.out.println("Running "+i);
            BufferedImage newimg = new BufferedImage(1000*(maxx), 1000*(maxy), BufferedImage.TYPE_INT_ARGB);
            Graphics newg = newimg.getGraphics();
            for (int xx = 0; xx < maxx; xx++) {
                for (int yy = 0; yy < maxy; yy++) {

                    double c = ((xx+dx) / 10f) - 1f;
                    double ci = ((yy+dy) / 10f) - 1f;

                    if(i==0) {
                         c--;
                         ci--;
                    }else
                    if(i==1) {
                        c++;
                        ci--;
                    }else
                    if(i==2) {
                        c--;
                        ci++;
                    }else
                    if(i==3) {
                        c++;
                        ci++;
                    }

                    String d= "Julia\\" + oneDigit.format(c) + "," + oneDigit.format(ci) + "  xdiff";
                    System.out.println("Set: "+d);
                    try {
                        img = ImageIO.read(new File("E:\\HyperBrot\\Renderings\\" + d + ".png"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    newg.drawImage(img, xx * 1000, (1000*(maxy-1)) - (yy * 1000), null);
                    newg.drawString("________________", (xx * 1000) + 100, (1000*(maxy-1)) - (yy * 1000) + 70);
                    newg.drawString("| c  = " + oneDigit.format(c), (xx * 1000) + 100, (1000*(maxy-1)) - (yy * 1000) + 100);
                    newg.drawString("| ci = " + oneDigit.format(ci) + " :", (xx * 1000) + 100, (1000*(maxy-1)) - (yy * 1000) + 150);
                    i3++;
                }
            }

            File outputfile = new File("E:\\HyperBrot\\Renderings\\final "+i+" julia diff.png");
            try {
                ImageIO.write(newimg, "png", outputfile);
            }catch (Exception e)
            {e.printStackTrace();}
        }
*/
/*

        int maxx =23;
        int maxy =23;
        BufferedImage newimg = new BufferedImage(1000*(maxx), 1000*(maxy), BufferedImage.TYPE_INT_ARGB);
        Graphics newg = newimg.getGraphics();
        DecimalFormat oneDigit = new DecimalFormat("#.##");
        BufferedImage img = null;
        for (int xx = 0; xx < maxx; xx++) {
            for (int yy = 0; yy < maxy; yy++) {

                double c = ((xx) / 10f) - 1.1f;
                double ci = ((yy) / 10f) - 1.1f;

                String d= "Julia\\" + oneDigit.format(c) + "," + oneDigit.format(ci) + "  xdiff";
                System.out.println("Set: "+oneDigit.format(c) + "," + oneDigit.format(ci));
                try {
                    img = ImageIO.read(new File("E:\\HyperBrot\\Renderings\\" + d + ".png"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                newg.drawImage(img, xx * 1000, (1000*(maxy-1)) - (yy * 1000), null);
                newg.drawString("________________", (xx * 1000) + 100, (1000*(maxy-1)) - (yy * 1000) + 70);
                newg.drawString("| c  = " + oneDigit.format(c), (xx * 1000) + 100, (1000*(maxy-1)) - (yy * 1000) + 100);
                newg.drawString("| ci = " + oneDigit.format(ci) + " :", (xx * 1000) + 100, (1000*(maxy-1)) - (yy * 1000) + 150);
            }
        }

        File outputfile = new File("E:\\HyperBrot\\Renderings\\big julia diff.png");
        try {
            ImageIO.write(newimg, "png", outputfile);
        }catch (Exception e)
        {e.printStackTrace();}
*/

/*
        int i3=0;
        for(int xx= 0 ; xx <= 30;xx++) {
            for (int yy = 0; yy <= 10; yy++) {

                jnumbersToDo[i3][0] =(xx/10f)-1f;
                jnumbersToDo[i3][1] =(yy/10f)-2f;
                i3++;
            }
        }
        for(int xx= 0 ; xx <= 10;xx++) {
            for (int yy = 0; yy <= 30; yy++) {

                jnumbersToDo[i3][0] =(xx/10f)+1f;
                jnumbersToDo[i3][1] =(yy/10f)-1f;
                i3++;
            }
        }
        for(int xx= 0 ; xx <= 30;xx++) {
            for (int yy = 0; yy <= 10; yy++) {

                jnumbersToDo[i3][0] =(xx/10f)-2f;
                jnumbersToDo[i3][1] =(yy/10f)+1f;
                i3++;
            }
        }
        for(int xx= 0 ; xx <= 10;xx++) {
            for (int yy = 0; yy <= 30; yy++) {

                jnumbersToDo[i3][0] =(xx/10f)-2f;
                jnumbersToDo[i3][1] =(yy/10f)-2f;
                i3++;
            }
        }
*/
        //pwidth = 500;
        //pheight = 500;

        //leftimg = new BufferedImage(pwidth,pheight,BufferedImage.TYPE_3BYTE_BGR);
        //rightimg = new BufferedImage(pwidth,pheight,BufferedImage.TYPE_3BYTE_BGR);

        //frame = new JFrame("Test");
        //frame.setVisible(true);
        //.setSize(pwidth, pheight);

        //GUI a = new GUI();



       // frame.add(m);

            /*Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    //frame = new JFrame("Test");
                    ///frame.setVisible(true);
                    //frame.setSize(pwidth, pheight);
                    Main m = new Main(0,2,1,2,10,2,0,0,0);
                    File outputfile = new File("E:\\HyperBrot\\Renderings\\multibrot n="+2.5+".png");
                    try {
                        ImageIO.write(leftimg, "png", outputfile);
                    }catch (Exception e)
                    {e.printStackTrace();}

*/



                    //(int type,int basen,int basenumer,int basedem, int iter,int ra, int typeOfRender,double jc,double jci)


                    //frame.add(m);
               // }
           // });
            //t.start();


/*
        evaluatePoint2(0,0,0,6,0,0.3,0.6,1,1,1);
        File outputfile = new File("E:\\HyperBrot\\Renderings\\lines g n="+2+".png");
        try {

            ImageIO.write(leftimg, "png", outputfile);
        }catch (Exception e)
        {e.printStackTrace();}

        leftimg = new BufferedImage(pwidth,pheight,BufferedImage.TYPE_3BYTE_BGR);

        evaluatePoint2(0,0,0,6,0,0.3,0.6,2,1,2);
        outputfile = new File("E:\\HyperBrot\\Renderings\\lines g n="+2.5+".png");
        try {
            ImageIO.write(leftimg, "png", outputfile);
        }catch (Exception e)
        {e.printStackTrace();}*/
   // }

    private static int evaluatePoint2(double x, double y, int currentDepth, int maxDepth,int total,double c,double ci,int basenumber,int basenumerinumber, int baserecinumber) {



        //Two new points

        Graphics g = leftimg.getGraphics();
        if(currentDepth==0)
        {
            g.setColor(Color.RED);
            g.drawOval((int)((x+2)*100)-2,(int)((y+2)*100)-2,4,4);
        }else
        {
            g.setColor(Color.GREEN);
            g.drawOval((int)((x+2)*100)-2,(int)((y+2)*100)-2,4,4);
        }
        double magnitude = (x * x + y * y);

        if(magnitude>64)
        {
            return 0;
        }else
        if(currentDepth==maxDepth)
        {
            return 1;
        }else
        {

            magnitude = Math.sqrt(magnitude);


            double angle = Math.atan2(y, x);

            double xSquared = Math.pow(magnitude,basenumber)*(Math.cos((angle*(double)(basenumber))));
            double ySquared = Math.pow(magnitude,basenumber)*(Math.sin((angle*(double)(basenumber))));

            double newx = Math.pow(magnitude,basenumerinumber)*(Math.cos((angle*(double)(basenumerinumber))));
            double newy = Math.pow(magnitude,basenumerinumber)*(Math.sin((angle*(double)(basenumerinumber))));

            angle = Math.atan2(newy, newx);

            double magnituderoot = nthroot2(baserecinumber,Math.sqrt(newx*newx+newy*newy));



            for(int i = 0 ; i < ((baserecinumber));i++)
            {


                double rootx = magnituderoot*(Math.cos((angle+i*2f*Math.PI)/(double)(baserecinumber)));
                double rooty = magnituderoot*(Math.sin((angle+i*2f*Math.PI)/(double)(baserecinumber)));

                double x1=(xSquared*rootx-ySquared*rooty) + c;
                double y1=(xSquared*rooty+rootx*ySquared) + ci;
                g.setColor(Color.WHITE);
                g.drawLine((int)((x+2)*100),(int)((y+2)*100),(int)((x1+2)*100),(int)((y1+2)*100));
                total += evaluatePoint2(x1,y1,currentDepth+1,maxDepth,0,c,ci,basenumber,basenumerinumber,baserecinumber);
            }


            return total;
        }
    }

    public  static double nthroot2(int n, double A) {
        return nthroot2(n, A, .001);
    }
    public static double nthroot2(int n, double A, double p) {
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

 }