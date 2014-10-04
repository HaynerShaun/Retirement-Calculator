import java.util.*; 

public class Retirement 
{
	private ArrayList<RetirementYear> list = new ArrayList<RetirementYear>();
	private String output = "  Age\tPre-Tax\tPost-Tax\tTotal\n  ----------\t----------\t----------\t----------\n";
	private int currentAge;
	private int retirementAge;
	private double incomeTaxRate;
	private double capitalGainsTaxRate; 
	private int preTaxBal;
	private int postTaxBal;
	private int preTaxContribution;
	private int postTaxContribution;
	private double rateOfReturn;
	
	public Retirement(int currentAge, int retirementAge, double incomeTaxRate, double capitalGainsTaxRate, 
			int preTaxBal, int postTaxBal, int preTaxContribution, int postTaxContribution, double rateOfReturn)
	{
		this.currentAge = currentAge;
		this.retirementAge = retirementAge;
		this.incomeTaxRate = incomeTaxRate / 100;
		this.capitalGainsTaxRate =  capitalGainsTaxRate / 100;
		this.preTaxBal = preTaxBal;
		this.postTaxBal = postTaxBal;
		this.preTaxContribution = preTaxContribution;
		this.postTaxContribution = postTaxContribution;
		this.rateOfReturn = rateOfReturn / 100;
		
	}
	
	public void go()
	{
		int newPreTaxBal = 0, newPostTaxBal = 0, captGainsTax = 0, captGains = 0;

		list.add(new RetirementYear(currentAge, preTaxBal, postTaxBal));
		list.get(0).setTotal();
		
		for (int x = 1; x <= retirementAge - currentAge; x++)
		{
			newPreTaxBal = (int)((double)list.get(x - 1).getPreTaxBal() * (1 + rateOfReturn) + 12 * preTaxContribution);
			newPostTaxBal = (int)((double)list.get(x - 1).getPostTaxBal() * (1 + rateOfReturn));
			captGains = newPostTaxBal - list.get(x - 1).getPostTaxBal();
			captGainsTax = (int)((double)captGains * capitalGainsTaxRate);
			newPostTaxBal = newPostTaxBal - captGainsTax + (12 * postTaxContribution);
			list.add(new RetirementYear(currentAge + x, newPreTaxBal, newPostTaxBal));
			if(list.get(x).getAge() == retirementAge)
			{
				list.get(x).setPreTaxBal((int)(newPreTaxBal - ((double)newPreTaxBal * incomeTaxRate)));
			}
			list.get(x).setTotal();
		}
		printToScreen();
	}
	
	private void printToScreen()
	{
		for (int x = 0; x < list.size(); x++)
		{
			if(list.get(x).getPreTaxBal() < Integer.MAX_VALUE && list.get(x).getPostTaxBal() < Integer.MAX_VALUE)
			{
				output += list.get(x).toString();
			}
			else
			{
				x = list.size();
				output += "\nThe calculation results are too large to display in this format.\n";
			}
		}
		View.displayArea.setText(output);
	}
}
