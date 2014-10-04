public class RetirementYear 
{
	private int preTaxBal;
	private int postTaxBal;
	private int age;
	private int total;
	
	public RetirementYear(int age, int preTaxBal, int postTaxBal)
	{
		this.age = age;
		this.preTaxBal = preTaxBal;
		this.postTaxBal = postTaxBal;
	}
	
	public int getPreTaxBal()
	{
		return preTaxBal;
	}
	
	public int getPostTaxBal()
	{
		return postTaxBal;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void setTotal()
	{
		total = preTaxBal + postTaxBal;
	}
	
	public void setPreTaxBal(int preTaxBal)
	{
		this.preTaxBal = preTaxBal;
	}
	
	public String toString()
	{
		return "  " + age + "\t" + preTaxBal + "\t" + postTaxBal + "\t" + total + "\n";
	}
}