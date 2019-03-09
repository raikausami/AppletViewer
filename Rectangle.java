class Rectangle{
    private int width;
    private int height;
    private int x;
    private int y;
    private String color;


    Rectangle(int width,int height,int x,int y,String color){ //長方形の左上の画像
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

    public String getColor(){
        return color;
    }

	public void setWidth(int width){
		this.width = width;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

    public void setColor(String color){
        this.color = color;
    }

	public void move(int xpoint,int ypoint){
        this.x=this.x+xpoint;
        this.y=this.y+ypoint;
    }

	public void expand_shrink(int xfold,int yfold){
		this.width=xfold*this.width;
		this.height=yfold*this.height;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" 幅");
		sb.append(width);
        sb.append("高さ");
		sb.append(height);
		sb.append(" ボード座標(");
		sb.append(x);
		sb.append(",");
		sb.append(y);
        sb.append(")色");
        sb.append(color);
		return sb.toString();
	}
}
