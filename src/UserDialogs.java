import java.util.Scanner;

public class UserDialogs {
    public static MoveCoords getMove(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter your move (i.e. A7B6): ");
            String s = scanner.nextLine().toUpperCase();
            if(s.length()==4){
                try{
                    int col1 = s.charAt(0) - 65;
                    int row1 = Integer.parseInt(s.substring(1,2))-1;
                    int col2 = s.charAt(2) - 65;
                    int row2 = Integer.parseInt(s.substring(3,4))-1;
                    return new MoveCoords(row1, row2, col1, col2);
                } catch (Exception e){
                    System.out.println("Wrong data!");
                }
            } else{
                System.out.println("Wrong data!");
            }
        }
    }
}
