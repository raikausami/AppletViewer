import java.awt.Color;

class Board{
    public static final int RECTANGLE_LIMIT = 10;
	public static final int BOARD_MAX_WIDTH = 400;
	public static final int BOARD_MAX_HEIGHT = 500;

    private int width;
	private int height;
    private Rectangle[] rectangles;
    private String msg;
    private String color;

    Board() {
		width = BOARD_MAX_WIDTH;
        height = BOARD_MAX_HEIGHT;
        color = "white";
		rectangles = new Rectangle[RECTANGLE_LIMIT];
	}


    public String add(int width,int height,int x,int y,String color){
        Rectangle rectangle = new Rectangle(width,height,x,y,color);
        if(isCheck(rectangle)){
            for(int num=0;num<rectangles.length; num++){
                if(!isExit(num)){
                    rectangles[num]=rectangle;
                    msg=num+1+"番目";
                    System.out.println(msg);
                    return msg;
                }
            }
            msg ="長方形の数の上限に達しています";
            return msg;
        }
        else{
            msg="作成できません";
            return msg;
        }
    }

    public void delete(int rectangle_number){
        if(rectangles[rectangle_number-1]==null){
            System.out.println("長方形が存在しません");
        }
        else{
            rectangles[rectangle_number-1]=null;
        }
    }


    public void deleteAll(){
        rectangles = new Rectangle[Board.RECTANGLE_LIMIT];
    }


    public void move(int Rectangle_number,int x,int y){
        if(isExit(Rectangle_number-1)){
            Rectangle rec= rectangles[Rectangle_number-1]; //ここあやしい newしてsetterしたほうがあんぱいかも
            rec.move(x,y);
            if(isCheck(rec)) rectangles[Rectangle_number-1]=rec;
        }
        else{
            System.out.println("指定されたRectangleは存在しません");
        }
    }

    public void expand_shrink(int Rectangle_number,int ex_w,int ex_h){
        if(isExit(Rectangle_number-1)){
            Rectangle rec = rectangles[Rectangle_number-1];
            rec.expand_shrink(ex_w,ex_h);
            if(isCheck(rec)) rectangles[Rectangle_number-1]=rec;
        }
        else{
            System.out.println("指定されたRectangleは存在しません");
        }
    }


    public void intersect(int Rectangle_number_1,int Rectangle_number_2){
        if(isExit(Rectangle_number_1-1)&&isExit(Rectangle_number_2-1)){
            int x1=rectangles[Rectangle_number_1-1].getX();
            int x2=rectangles[Rectangle_number_2-1].getX();
            int y1=rectangles[Rectangle_number_1-1].getY();
            int y2=rectangles[Rectangle_number_2-1].getY();
            int w1=rectangles[Rectangle_number_1-1].getWidth();
            int w2=rectangles[Rectangle_number_2-1].getWidth();
            int h1=rectangles[Rectangle_number_1-1].getHeight();
            int h2=rectangles[Rectangle_number_2-1].getHeight();

            String str1 = rectangles[Rectangle_number_1-1].getColor();
            String str2 = rectangles[Rectangle_number_2-1].getColor();
            String str3 = null;

            if(x2>=x1){
                if(x2<=x1+w1 && x1<=x2+w2){
                    if(y2<=y1+h1&&y1<=y2+h2){
                        str3=mixColor(str1,str2);
                        add(x2,y2,x1+w1,y1+h1,str3);
                    }
                }
            }
            else{
                if(x1<=x2+w2 && x2<=x1+w1){
                    if(y1<=y2+h2&&y2<=y1+h1){
                        str3=mixColor(str1,str2);
                        add(x1,y1,x2+w2,y2+h2,str3);
                    }
                }
            }
        }
        else{
            System.out.println("指定されたRectangleは存在しません");
        }
    }


    public boolean isCheck(Rectangle rectangle){
        int count=1;
        for(Rectangle r : rectangles){
            if(isExit(count)){
                count++;
                if(r.getHeight()==rectangle.getHeight()&&r.getWidth()==rectangle.getWidth()&&r.getX()==rectangle.getX()&&r.getY()==rectangle.getY()){
                    return false;
                }
            }
        }
        if(rectangle.getHeight()<1 || rectangle.getWidth()<1){
            System.out.println("長方形ではない");
            return false;
        }
        if(rectangle.getX()<0 || rectangle.getY()<0){
            System.out.println("長方形の始点がボード上に無い");
            return false;
        }
        if(rectangle.getX()+rectangle.getWidth()>getWidth()){
            System.out.println("X座標がはみ出しています");
            return false;
        }
        if(rectangle.getY()+rectangle.getHeight()>getHeight()){
            System.out.println("Y座標がはみ出しています");
            return false;
        }
        if(rectangle.getColor().equals("red")==true | rectangle.getColor().equals("blue")==true | rectangle.getColor().equals("yellow")==true | rectangle.getColor().equals("gray")==true | rectangle.getColor().equals("green")==true | rectangle.getColor().equals("orange")==true | rectangle.getColor().equals("magenta")==true | rectangle.getColor().equals("cyan")==true){
            return true;
        }
        else{
            System.out.println("指定された色ではありません");
            return false;
        }
    }

    public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

    public String getColor(){
        return color;
    }

    public Color getRGBColor(String a){
        Color c = null;
        if(a.equals("yellow")) c = new Color(255,255,0,224);
        else if(a.equals("orange")) c = new Color(255,165,0,224);
        else if(a.equals("blue")) c = new Color(0,0,255,224);
        else if(a.equals("magenta")) c = new Color(255,0,255,224);
        else if(a.equals("green")) c = new Color(0,255,0,224);
        else if(a.equals("cyan")) c = new Color(0,255,255,224);
        else if(a.equals("gray")) c = new Color(128,128,128,224);
        else{
            System.out.println("e");
        }
        return c;
    }

	public Rectangle[] getRectangles() {
		return rectangles;
    }

    public boolean isExit(int rectangleNum){
        if(rectangleNum >= 0 && rectangleNum<RECTANGLE_LIMIT && rectangles[rectangleNum]!=null){
            return true;
        }
        return false;
    }

    public String mixColor(String a,String b){
        if(a.equals("yellow")){
            if(b.equals("blue")) return "green";
            else if(b.equals("red")) return "orange";
        }
        else if(a.equals("red")){
            if(b.equals("yellow")) return "orange";
            else if(b.equals("blue")) return "magenta";
        }
        else if(a.equals("blue")){
            if(b.equals("yellow")) return "green";
            else if(b.equals("red")) return "magenta";
        }
        else if(a.equals(b)){
            return "gray";
        }
        return "cyan";
    }

    public int searchRectangle(int x,int y){
        int count=0;
        int tmpi=11;
        for(Rectangle r : rectangles){
            if(r!=null){
                if(r.getX()<x && r.getY()<y&& x<r.getX()+r.getWidth() && y<r.getY()+r.getHeight()){
                    tmpi=count;
                }
            }
            count++;
        }
        return tmpi; //なかったら11返す
    }
}

