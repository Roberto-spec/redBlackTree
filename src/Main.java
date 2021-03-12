import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Tree Дерево =new Tree();
        Scanner scanner=new Scanner(System.in);
        System.out.print("Сколько элементов добавить в дерево?:>_");
        int elements=scanner.nextInt();
        for(int i =0;i<elements;i++){
            System.out.print("Введите кюч элемента:>_");
            Дерево.add(scanner.nextInt());
        }

        System.out.println(Дерево.toString());
    }
}
