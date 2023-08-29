import java.util.*;
class Exp_tracker
{
    Scanner sc=new Scanner(System.in);
    String name;
    double amt,Nec,Wants,Savings,utility_bill,EMI;
    double fuel[]=new double[5];
    double groceries[]=new double[5];
    double health_care[]=new double[5];
    double eating_out[]=new double[5];
    double entertainment[]=new double[5];
    double vacation[]=new double[5];
    int wk=0;
    Exp_tracker(String s,double amt)
    {
        name=s;
        System.out.println("Enter your EMI and utility bill per month");
        EMI=sc.nextDouble();
        utility_bill=sc.nextDouble();
        Nec=0.5*amt;
        Wants=0.3*amt;
        Savings=0.2*amt;
    }
    void input()
    {
        do{
            System.out.println("Enter 1. to input your expenses in Necessity 2. for your expenses in Wants 3. for Savings 4.exit");
            int ch=sc.nextInt();
            switch(ch)
            {
                case 1:
                    necessity();
                    break;
                case 2:
                    wants();
                    break;
                case 3:
                    System.out.println("Enter your expeneses spent on vacaton");
                    vacation[wk]=sc.nextInt();
                    break;
                case 4:
                    System.out.println("Your expenditure has been noted");
                    print();
                    wk++;
                    System.exit(0);
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }while(1>0);
    }
    void necessity()
    {
        System.out.println("Enter your expenses in fuel,groceries,health care this week");
        fuel[wk]=sc.nextDouble();
        groceries[wk]=sc.nextDouble();
        health_care[wk]=sc.nextDouble();
    }
    void wants()
    {
        System.out.println("Enter your expenses in eating out and entertainment this week");
        eating_out[wk]=sc.nextDouble();
        entertainment[wk]=sc.nextDouble();
    }
    void print()
    {
        double sum=0;
        System.out.println("Name:"+name);
        System.out.println("Amount procured for Necessity:"+Nec);
        for(int i= 0;i<=wk;i++)
        {
            sum+=fuel[i]+groceries[i]+health_care[i];
            System.out.println("Amount spent on week "+(i+1));
            System.out.println("fuel:"+fuel[i]+"\t groceries:"+groceries[i]+"\t health care:"+health_care[i]);
        }
        System.out.println("Amount left to spend under Necessity:"+(Nec-sum));
        sum=0;
        System.out.println("\nAmount procured for Wants:"+Wants);
        for(int i= 0;i<=wk;i++)
        {
            sum+=eating_out[i]+entertainment[i];
            System.out.println("Amount spent on week "+(i+1));
            System.out.println("eating out:"+eating_out[i]+"\t entertainment:"+entertainment[i]);
        }
        System.out.println("Amount left to spend under Wants:"+(Wants-sum));
        sum=0;
        System.out.println("\nAmount procured for Savings:"+Savings);
        for(int i= 0;i<=wk;i++)
        {
            sum+=vacation[i];
            System.out.println("Amount spent on week "+(i+1));
            System.out.println("vacation:"+vacation[i]);
        }
        System.out.println("Amount left under Savings:"+(Savings-sum));
    }
}
class Main
{
    public static void main(String args[])
    {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter your name and your monthly salary");
            String name=sc.next();
            double m_amt=sc.nextDouble();
            Exp_tracker E=new Exp_tracker(name,m_amt);
            System.out.println("Enter 1. to input and 2.for displaying");
            int ch=sc.nextInt();
            switch(ch)
            {
                case 1:
                    E.input();
                    break;
                case 2:
                    E.print();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}
