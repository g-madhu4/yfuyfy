class main
{
    public static void main(String args[])
    {
        skip("","i fouand the aaa aa reason to my weaeness");
    }
    public static void skip(String p,String o)
    {
        if(o.isEmpty())
        {
            System.out.println(p);
            return;
        }
        char ch=o.charAt(0);
        if(ch=='a')
        {
            skip(p,o.substring(1));
        }
        else
        {
           skip(p+ch,o.substring(1));
        }
    }
}
