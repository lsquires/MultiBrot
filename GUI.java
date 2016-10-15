import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.util.Arrays;

/**
 * Created by Home on 02/03/2015.
 */
public class GUI {


    public final SpinnerNumberModel model1;
    public final SpinnerNumberModel model2;
    public final SpinnerNumberModel model3;
    public final SpinnerNumberModel model11;
    public final SpinnerNumberModel model4;
    public final SpinnerNumberModel model5;
    public final SpinnerNumberModel model6;

    public ButtonGroup pathGroup;
   // public JTabbedPane tabbedPane1;
    public JProgressBar progressBar1;
    public JButton cancelButton;
    public JButton generateButton;
    public JRadioButton constantPathsRadioButton;
    public JRadioButton noPathsRadioButton;
    public JRadioButton pathsRadioButton;
    public JSpinner pathjiterationsSpinner;
    public JSpinner pspinner2;
    public JSpinner qspinner3;
    public JSpinner iterationspinner4;
    public JSpinner escaperadiusspinner5;
    public JComboBox colourSchemeCombo;
    public JPanel GUI;
    public JSpinner renderradiusspinner1;
    public JSpinner xoffsetspinner;
    public JPanel brotPanel;
    public ImagePanel brot;
    private JSpinner yoffsetspinner;
    private JCheckBox setDragAreaCheckBox;
    private JLabel xlabel;
    private JLabel ylabel;
    private JSpinner threadsSpineer;
    public JLabel threadsLabel;
    private JButton resetViewButton;
    public JComboBox colourActualCombo;
    public JComboBox customBranchcomboBox1;
    public Thread currentThread;
    public Thread currentPathThread;
    public  double range;
    public  boolean renderPath;
    public  boolean willrenderPath;
    public  boolean renderRect;
    public  double xoffset ;
    public  double yoffset;
    public  double mousescaledx ;
    public  double mousescaledy;

    public int mousedragx;
    public int mousedragy;
    public int firstmousedragx;
    public int firstmousedragy;

    public int typeFractal;
    public BrotPathGenerator currentPathThreadGen;
    //public ComboBoxModel branchModel;


    public GUI( ) {

        typeFractal = 0;
        willrenderPath = false;
        brot.gui = this;
        pathGroup = new ButtonGroup();
        pathGroup.add(constantPathsRadioButton);
        pathGroup.add(noPathsRadioButton);
        pathGroup.add(pathsRadioButton);
        renderPath = false;
        customBranchcomboBox1.setEditable(false);

        model1 = new SpinnerNumberModel(5, 1, 100000, 1);
        model11 = new SpinnerNumberModel(5, 1, 100000, 1);
        pathjiterationsSpinner.setModel(model1);
        iterationspinner4.setModel(model11);

        model2 = new SpinnerNumberModel(4.0, 1.0, 1024, 0.01);
        escaperadiusspinner5.setModel(model2);

        pspinner2.setValue(2);
        qspinner3.setValue(1);
        model6 = new SpinnerNumberModel(2, 1, 1000, 1);
        threadsSpineer.setModel(model6);


        model3 = new SpinnerNumberModel(4.0, 0.1, 100, 0.0001);
        renderradiusspinner1.setModel(model3);
        range=4;
        model4 = new SpinnerNumberModel(0, -1000, 1000, 0.01);
        xoffsetspinner.setModel(model4);
        model5 = new SpinnerNumberModel(0, -1000, 1000, 0.01);
        yoffsetspinner.setModel(model5);
        final GUI a = this;
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BrotGenerator b =new BrotGenerator(a,(Integer)threadsSpineer.getValue());
               currentThread =  new Thread(b);
                b.attatch(currentThread.getId());
               currentThread.start();


            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                currentThread =  new Thread();
                currentPathThread =  new Thread();


            }
        });

        setDragAreaCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                willrenderPath = setDragAreaCheckBox.isSelected();

            }
        });



        renderradiusspinner1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                range = (Double)renderradiusspinner1.getValue();
            }
        });

        noPathsRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                renderPath = pathsRadioButton.isSelected();
                if(!renderPath)
                {
                    brot.repaint();
                }
            }
        });

        pathsRadioButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                renderPath = pathsRadioButton.isSelected();
                if(!renderPath)
                {
                    brot.repaint();
                }
            }
        });

        xoffsetspinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                xoffset = (Double)xoffsetspinner.getValue();
            }
        });

        yoffsetspinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                yoffset = (Double)yoffsetspinner.getValue();
            }
        });

        pspinner2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                redoBranches();
            }
        });

        qspinner3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                redoBranches();
            }
        });

        iterationspinner4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                redoBranches();
            }
        });

        brot.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if(renderRect) {

                    mousedragx = e.getX();
                    mousedragy = e.getY();
                    brot.repaint();

                }
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {


                mousescaledx = (((double)((e.getX())-brot.halfsize)*range)/brot.halfsize)+xoffset;
                mousescaledy = ((((double)(brot.halfsize-e.getY()))*range)/brot.halfsize)+yoffset;
                xlabel.setText("X: "+mousescaledx);
                ylabel.setText("Y: "+mousescaledy);
                xlabel.updateUI();
                ylabel.updateUI();

                if(renderPath)
                    {
                        if(currentPathThreadGen!=null)
                            {
                                currentPathThreadGen.abort();
                            }
                        BrotPathGenerator b =new BrotPathGenerator(a,mousescaledx,mousescaledy);
                        currentPathThread =  new Thread(b);
                        b.attatch(currentPathThread.getId());
                        currentPathThreadGen = b;
                        currentPathThread.start();
                    }

                super.mouseMoved(e);
            }
        });


        brot.addMouseListener(new MouseAdapter() {


            @Override
            public void mousePressed(MouseEvent e) {

                firstmousedragx = e.getX();
                firstmousedragy = e.getY();
                if(willrenderPath)
                    {
                        renderRect = true;
                    }
                super.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                mousedragx = e.getX();
                mousedragy = e.getY();
                if(renderRect)
                {
                    dragAreaCalc();
                    
                    renderRect = false;
                    brot.repaint();
                }
                super.mouseReleased(e);
            }
        });


        resetViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                xoffsetspinner.setValue(0.00);
                yoffsetspinner.setValue(0.00);

                renderradiusspinner1.setValue(4.00);



                BrotGenerator b =new BrotGenerator(a,(Integer)threadsSpineer.getValue());
                currentThread =  new Thread(b);
                b.attatch(currentThread.getId());
                currentThread.start();
            }
        });

        colourSchemeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(colourSchemeCombo.getSelectedIndex()==5)
                    {
                        redoBranches();
                        //customBranchcomboBox1.setModel(branchModel);
                    }else
                    {
                        customBranchcomboBox1.setEditable(false);
                        //customBranchcomboBox1.setSelectedIndex(0);
                        customBranchcomboBox1.setPopupVisible(false);
                        customBranchcomboBox1.removeAllItems();
                        //customBranchcomboBox1.setModel(branchModel);
                    }

            }
        });


        /*abbedPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {

                super.componentShown(e);
                typeFractal = tabbedPane1.getSelectedIndex();
            }
        });*/
        brot.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //System.out.println(e.getComponent().getWidth() + "," + e.getComponent().getHeight());

                brot.size = ((e.getComponent().getHeight()< e.getComponent().getWidth())?( e.getComponent().getHeight()):( e.getComponent().getWidth()));
                brot.updateUI();
                super.componentResized(e);
            }
        });


    }

    private void redoBranches() {
        if(colourSchemeCombo.getSelectedIndex()==5) {
            customBranchcomboBox1.removeAllItems();
            customBranchcomboBox1.setEditable(true);
            int baserecinumber = (Integer) qspinner3.getValue();
            int branchIteration = (Integer) iterationspinner4.getValue();
            int number = (int) Math.pow(baserecinumber, branchIteration - 1);
            int[] n = new int[branchIteration - 1];
            int Nr[] = new int[branchIteration - 1];
            for (int i = 0; i < branchIteration - 1; i++) {
                Nr[i] = baserecinumber;
            }
            String combination = "";
            combination = printPermutations(n, Nr, 0, combination);
            //System.out.println(combination);
            String[] combinations = combination.split("p");
            for (int i = 0; i < number; i++) {
                customBranchcomboBox1.addItem(">" + combinations[i]);
            }
        }
    }

    public  String printPermutations(int[] n, int[] Nr, int idx,String current) {
        if (idx == n.length) {  //stop condition for the recursion [base clause]
            //System.out.println(Arrays.toString(n));
            String a = Arrays.toString(n);
           return a.substring(1,a.length()-1)+"p";
        }
        String ne = "";
        for (int i = 1; i <= Nr[idx]; i++) {
            n[idx] = i;
            ne=ne+printPermutations(n, Nr, idx+1,current); //recursive invokation, for next elements
        }
        return current+ne;
    }

        private void dragAreaCalc() {


        double x = (((double)((mousedragx)-brot.halfsize)*range)/brot.halfsize)+xoffset;
        double y =(((double) ((brot.halfsize-mousedragy)) * range) / brot.halfsize) + yoffset;
        double x2 = (((double)((firstmousedragx)-brot.halfsize)*range)/brot.halfsize)+xoffset;
        double y2 =(((double) ((brot.halfsize-firstmousedragy)) * range) / brot.halfsize) + yoffset;

            double size = (Math.abs(x2-x)>Math.abs(y2-y))?(Math.abs(x2-x)/2f):(Math.abs(y2-y)/2f);


            xoffsetspinner.setValue((x < x2) ? (x + size) : (x2 + size));
            yoffsetspinner.setValue((y<y2)?(y+size):(y2+size));

        renderradiusspinner1.setValue(size);



        BrotGenerator b =new BrotGenerator(this,(Integer)threadsSpineer.getValue());
        currentThread =  new Thread(b);
        b.attatch(currentThread.getId());
        currentThread.start();

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Multibrot Fractal Explorer v1   -www.mynameislaurence.com");
        frame.setContentPane(new GUI().GUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1300,800);

    }



}
