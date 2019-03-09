class Command{

    private Board board;

    public Command(Board board){
        this.board = board;
    }


    public void create(){
        System.out.println("幅,高さx、y,色を入力");
        int width = new Input().inputInteger("幅");
        int height = new Input().inputInteger("高さ");
        int x = new Input().inputInteger("x");
        int y = new Input().inputInteger("y");
        String color = new Input().inputString("色");
        board.add(width, height, x, y,color);
    }


    public void delete(){
        displayBoard();
        int rectangle_number =new Input().inputInteger("削除したい番号を選んでください");
        board.delete(rectangle_number);
    }


    public void displayBoard(){
        int count = 1;
        for (Rectangle rectangle : board.getRectangles()) {
			if (rectangle != null) {
				System.out.println(rectangle.toString() + "は" + count + "番目に保存されています");
				count++;
			}
		}
    }


    public void move(){
        displayBoard();
        int rectangle_number = new Input().inputInteger("何番目");
        int x = new Input().inputInteger("移動するx距離を入力してください");
        int y = new Input().inputInteger("移動するy距離を入力してください");
        board.move(rectangle_number,x,y);
    }


    public void expand_shrink(){
        displayBoard();
        int rectangle_number = new Input().inputInteger("何番目");
        int ex_x = new Input().inputInteger("拡大/縮小するx距離を入力してください");
        int ex_y = new Input().inputInteger("拡大/縮小するy距離を入力してください");
        board.expand_shrink(rectangle_number,ex_x,ex_y);
    }


    public void deleteAll(){
        board.deleteAll();
    }


    public void intersect(){
        displayBoard();
        int rectangle_number_1 = new Input().inputInteger("何番目");
        int rectangle_number_2 = new Input().inputInteger("何番目");
        board.intersect(rectangle_number_1,rectangle_number_2);
    }
}
