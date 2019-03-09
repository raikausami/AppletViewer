import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class RectangleEditor extends Applet implements  ActionListener, ItemListener, MouseListener ,Runnable {

    Checkbox cb[];


    Command command;
    Board board;
    public  Button btn1, btn2, btn3, btn4, btn5, btn6;
    private Label moveXLabel;
	private Label moveYLabel;
	private Label xRatioLabel;
	private Label yRatioLabel;
	private TextField moveXText;
	private TextField moveYText;
	private TextField xRatioText;
    private TextField yRatioText;

    //private Color ccheck;
    private String color;
    private int[] x = new int[2]; //[2]がnew
    private int[] y = new int[2];

    //inEventListener listener;

    
    //Thread thread =null;

    public void init(){
        board = new Board();
        command = new Command(board);

        CheckboxGroup cbg = new CheckboxGroup();

        //listener = new inEventListener();
        btn1= new Button("Create");
        btn2= new Button("Move");
        btn3= new Button("Expand/Shrink");
        btn4= new Button("Delete");
        btn5= new Button("DeleteAll");
        btn6= new Button("Intersect");
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);

        addMouseListener(this);

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(btn6);
        
      

		moveXLabel = new Label("x=");
		add(moveXLabel);
		moveXText = new TextField();
		add(moveXText);
		moveYLabel = new Label("y=");
		add(moveYLabel);
		moveYText = new TextField();
		add(moveYText);
		xRatioLabel = new Label("xRatio=");
		add(xRatioLabel);
		xRatioText = new TextField();
		add(xRatioText);
		yRatioLabel = new Label("yRatio=");
		add(yRatioLabel);
		yRatioText = new TextField();
        add(yRatioText);
        
        cb = new Checkbox[3];        // Checkbox型で配列の領域確保
        cb[0] = new Checkbox("赤",true,cbg);    // オブジェクト生成
        cb[1] = new Checkbox("青",false,cbg);
        cb[2] = new Checkbox("黄",false,cbg);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(cb[0]);
        add(cb[1]);
        add(cb[2]);

        // リスナ登録 
        cb[0].addItemListener(this);
        cb[1].addItemListener(this);
        cb[2].addItemListener(this);

        //thread = new Thread(this);
        //thread.start();
    }

    public void run(){
    }

    public void paint(Graphics g){
        for (Rectangle rectangle : board.getRectangles()) {
			if (rectangle != null) {
				g.setColor(board.getRGBColor(rectangle.getColor()));
				g.fillRect(rectangle.getX(),rectangle.getY(),rectangle.getWidth(), rectangle.getHeight());
			}
		}
    }

    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (source==btn1){
            int xpoint = Integer.parseInt(moveXText.getText());
            int ypoint = Integer.parseInt(moveYText.getText());
            int width = Integer.parseInt(moveXText.getText());
            int height = Integer.parseInt(moveYText.getText());
            board.add(width,height,xpoint,ypoint,color);
            repaint();
        }
        else if (source==btn2){
            int rectnum=board.searchRectangle(x[2],y[2]);
            int xpoint = Integer.parseInt(moveXText.getText()); 
            int ypoint = Integer.parseInt(moveYText.getText());
            board.move(rectnum,xpoint,ypoint);
            repaint();
        }
        else if (source==btn3){
            int rectnum=board.searchRectangle(x[2],y[2]);
            int xRatio = Integer.parseInt(xRatioText.getText());
            int yRatio = Integer.parseInt(yRatioText.getText());
            board.expand_shrink(rectnum,xRatio,yRatio);
            repaint();
        }
        else if (source==btn4){
            int rectnum=board.searchRectangle(x[2],y[2]);
            board.delete(rectnum);
            repaint();
        }
        else if (source==btn5){
            board.deleteAll();
            repaint();
        }
        else if (source==btn6){
            int rectnum=board.searchRectangle(x[1],y[1]);
            int rectnum_new=board.searchRectangle(x[2],y[2]);
            board.intersect(rectnum,rectnum_new);
            repaint();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Checkbox obj = (Checkbox)e.getSource();
        String color = obj.getLabel();
        // setBackground()で背景色変更
        if ( cb[0].getState() ){
            color="red";
        }
        else if ( cb[1].getState() ){
            color="blue";
        }
        else if ( cb[2].getState() ){
            color="yellow";
        }
    }


    public void mousePressed( MouseEvent e ){
        x[1]=x[2];
        y[1]=y[2];
		x[2] = e.getX();	// マウスがクリックされた位置のx座標を取得する
		y[2] = e.getY();	// マウスがクリックされた位置のy座標を取得する
	}


    public void mouseReleased(MouseEvent e){ ; }
	public void mouseClicked(MouseEvent e){ ; }
	public void mouseEntered(MouseEvent e){ ; }
	public void mouseExited(MouseEvent e){ ; }
	
}
