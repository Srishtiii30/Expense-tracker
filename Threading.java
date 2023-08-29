public class Threading extends Thread{

    Main c;

    Threading(Main c)
    {
        this.c=c;
    }
    public void run(){
        int i=1;
        while(true)
        {
            this.c.input();
            try{Thread.sleep(1000);}
            catch(Exception e){}
            i++;
        }

    }
    
}
