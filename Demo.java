import java.util.*;
//A class to implement Runnable Interface
class Exp_tracker implements Runnable
{
    Thread t2,t1;
    //Declaring required data members
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
    //Creating a constructor
    Exp_tracker(String s,double amt)
    {
        name=s;
        Nec=0.5*amt;
        Wants=0.3*amt;
        Savings=0.2*amt;
    }
    //Creating a run method to implement thread 1
    public void run()   
    {
        System.out.println("\nThread 1 started\n");
        double sum=EMI+utility_bill;
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
    //starts thread 1
    public void start()
    {
        if(t1==null)
        {
            t1=new Thread(this);
            t1.start();
        }
    }
    //transfers the accepted details to exp_tracker object
    void transfer(Accept E)
    {
        this.name=E.name;
        this.amt=E.amt;
        this.Nec=E.Nec;
        this.Wants=E.Wants;
        this.Savings=E.Savings;
        this.utility_bill=E.utility_bill;
        this.EMI=E.EMI;
        this.wk=E.wk;
        for(int c=0;c<5;c++)
        {
            this.fuel[c]=E.fuel[c];
            this.groceries[c]=E.groceries[c];
            this.health_care[c]=E.health_care[c];
            this.eating_out[c]=E.eating_out[c];
            this.entertainment[c]=E.entertainment[c];
            this.vacation[c]=E.vacation[c];
        }
    }
}


class Accept extends Exp_tracker
{
    Accept(String s,double amt)//creating a constructor
    {
        super(s,amt);
    }
    //Creating a second run method to implement thread 2
    public void run()
    {
        System.out.println("\nThread 2 started\n");
        //Checks for exception
        try
        {
            Thread.sleep(1000);
            //Thread 2 goes to sleep
        }
        catch(InterruptedException e)
        {
            System.out.println("Exception caught"+e);
        }
        System.out.println("Enter your EMI and utility bill per month");
        EMI=sc.nextDouble();
        utility_bill=sc.nextDouble();
        //Creating a loop for the user to enter multiple rounds of inputs
        int ch;
        do{
            System.out.println("Enter 1. to input your expenses in Necessity \n2. for your expenses in Wants \n3. for Savings \n4.exit and print");
            ch=sc.nextInt();
            switch(ch)
            {
                case 1:
                necessity();
                break;
                case 2:
                wants();
                break;
                case 3:
                System.out.println("Enter your expenses spent on vacation");
                vacation[wk]=sc.nextInt();//taking input of the expenses on vacation
                break;
                case 4:
                System.out.println("Your expenditure has been noted");
                Exp_tracker temp=new Exp_tracker(name,amt);
                temp.transfer(this);
                temp.start();
                wk++;
                break;
                default:
                System.out.println("Invalid Input");
                break;
            }
        }while(ch!=4);
    }
    //method to take input of the expenses under necessity
    void necessity()
    {
        System.out.println("Enter your expenses in fuel,groceries,health care this week");
        fuel[wk]=sc.nextDouble();
        groceries[wk]=sc.nextDouble();
        health_care[wk]=sc.nextDouble();
    }
    //method to take input of the expenses under wants
    void wants()
    {
        System.out.println("Enter your expenses in eating out and entertainment this week");
        eating_out[wk]=sc.nextDouble();
        entertainment[wk]=sc.nextDouble();
    }
    //starts thread 2
    public void start()
    {
        if(t2==null)
        {
            t2=new Thread(this);
            t2.start();
        }
    }
}


//main class
class Demo
{
    public static void main(String args[]) //main method
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your name and your monthly salary"); //taking basic input
        String name=sc.next();
        double m_amt=sc.nextDouble();
        //creating objects of Exp_tracker and Accept
        Exp_tracker E=new Exp_tracker(name,m_amt);
        Accept A=new Accept(name,m_amt);
        System.out.println("Expenditure until last week:");
        E.start(); //starts thread 1
        A.start(); //starts thread 2
    }
}

