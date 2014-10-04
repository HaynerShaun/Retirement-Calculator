import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class View 
{
    private JFrame retirementCalculator = new JFrame("Retirement Calculator");
	
    private JLabel currentAgeLabel = new JLabel("Current Age");
    private JTextField currentAge = new JTextField(5);
	private JLabel currentAgeError = new JLabel("Current Age Error");
	
	private JLabel retirementAgeLabel = new JLabel("Retirement Age");
	private JTextField retirementAge = new JTextField(5);
	private JLabel retirementAgeError = new JLabel("Retirement Age Error");
	
	private JLabel incomeTaxRateLabel = new JLabel("Income Tax Rate Percent (Ex: 5.0)");
	private JTextField incomeTaxRate = new JTextField(5);
	private JLabel incomeTaxRateError = new JLabel("Income Tax Rate Error");
	
	private JLabel capitalGainsTaxRateLabel = new JLabel("Capital Gains Tax Rate Percent (Ex: 5.0)");
	private JTextField capitalGainsTaxRate = new JTextField(5);
	private JLabel capitalGainsTaxRateError = new JLabel("Capital Gains Tax Rate Error");
	
	private JLabel preTaxBalLabel = new JLabel("Pre Tax Initial Balance");
	private JTextField preTaxBal = new JTextField(5);
	private JLabel preTaxBalError = new JLabel("Pre Tax Balance Error");
	
	private JLabel postTaxBalLabel = new JLabel("Post Tax Initial Balance");
	private JTextField postTaxBal = new JTextField(5);
	private JLabel postTaxBalError = new JLabel("Post Tax Balance Error");
	
	private JLabel preTaxContributionLabel = new JLabel("Pre Tax Monthly Contribution");
	private JTextField preTaxContribution = new JTextField(5);
	private JLabel preTaxContributionError = new JLabel("Pre Tax Contribution Error");
	
	private JLabel postTaxContributionLabel = new JLabel("Post Tax Monthly Contribution");
	private JTextField postTaxContribution = new JTextField(5);
	private JLabel postTaxContributionError = new JLabel("Post Tax Contribution Error");
	
	private JLabel rateOfReturnLabel = new JLabel("Rate of Return Percent (Ex: 5.0)");
	private JTextField rateOfReturn = new JTextField(5);
	private JLabel rateOfReturnError = new JLabel("Rate of Return Error");
	
    private JButton calculate = new JButton("Calculate");
    private JButton clear = new JButton("Clear");
    
    public static JTextArea displayArea = new JTextArea(30, 30);
    private JScrollPane scrollpane = new JScrollPane(displayArea);
    
    private int count;
    
    private Dimension size;
    
    public void loadGUI() 
    {
    	retirementCalculator.getContentPane().setPreferredSize(new Dimension(750, 550));
        retirementCalculator.setLayout(null);
        retirementCalculator.setVisible(true);
        retirementCalculator.setResizable(false);
        centreWindow(retirementCalculator);
        
    	addLabels();
    	addTextFields();
    	addErrorLabels();
    	addButtons();

    	setLabelLocations();
    	setTextLocations();
    	setErrorLocations();
    	setButtonLocations();
    	clear();
    	testCase();
    	
        calculate.addActionListener (new ButtonListener());
        clear.addActionListener (new ButtonListener());
        
        retirementCalculator.pack();
    }
    
    private void testCase()
    {
    	currentAge.setText("1");
    	retirementAge.setText("10");
    	incomeTaxRate.setText("20");
    	capitalGainsTaxRate.setText("15");
    	preTaxBal.setText("100");
    	postTaxBal.setText("100");
    	preTaxContribution.setText("10");
    	postTaxContribution.setText("10");
    	rateOfReturn.setText("5");
    }
    
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            if(event.getSource() == calculate)
            {
                calculate();
            }
            if(event.getSource() == clear)
            {
                clear();
            }
        }
    }
    
    private void calculate()
    {
    	displayArea.setText("");
    	count = 0;
    	hideErrors();
    	int cAge = checkCurrentAge();
    	int rAge = checkRetirementAge();
    	double iRate = checkIncomeTaxRate();
    	double cgRate = checkCapitalGainsTaxRate();
    	int preBal = checkPreTaxBalance();
    	int postBal = checkPostTaxBalance();
    	int preCon = checkPreTaxContribution();
    	int postCon = checkPostTaxContribution();
    	double rRate = checkRateOfReturn();
    	
    	if(cAge > rAge)
    	{
    		count++;
    		retirementAge.setText("");
    		retirementAgeError.setVisible(true);
    	}
    	
    	if (count == 0)
    	{
    		Retirement retirement = new Retirement(cAge, rAge, iRate, cgRate, preBal, postBal, preCon, postCon, rRate);
    		retirement.go();
    	}
    }
    
    private void clear()
    {
    	currentAge.setText("");
    	retirementAge.setText("");
    	incomeTaxRate.setText("");
    	capitalGainsTaxRate.setText("");
    	preTaxBal.setText("");
    	postTaxBal.setText("");
    	preTaxContribution.setText("");
    	postTaxContribution.setText("");
    	rateOfReturn.setText("");
    	displayArea.setText("");
    	hideErrors();
    }
    
    private void hideErrors()
    {
    	currentAgeError.setVisible(false);
        retirementAgeError.setVisible(false);
        incomeTaxRateError.setVisible(false);
        capitalGainsTaxRateError.setVisible(false);
        preTaxBalError.setVisible(false);
        postTaxBalError.setVisible(false);
        preTaxContributionError.setVisible(false);
        postTaxContributionError.setVisible(false);
        rateOfReturnError.setVisible(false);
    }
    
    private void setButtonLocations()
    {
    	size = calculate.getPreferredSize();
        calculate.setBounds(50, 500, size.width, size.height);
        
        size = clear.getPreferredSize();
        clear.setBounds(150, 500, size.width, size.height);
        
    	scrollpane.setBounds(350,10,350,500);
    }
    
    private void setLabelLocations()
    {
    	size = currentAgeLabel.getPreferredSize();
        currentAgeLabel.setBounds(10, 10, size.width, size.height);
        
        size = retirementAgeLabel.getPreferredSize();
        retirementAgeLabel.setBounds(10, 60, size.width, size.height);
        
        size = incomeTaxRateLabel.getPreferredSize();
        incomeTaxRateLabel.setBounds(10, 110, size.width, size.height);
        
        size = capitalGainsTaxRateLabel.getPreferredSize();
        capitalGainsTaxRateLabel.setBounds(10, 160, size.width, size.height);
        
        size = preTaxBalLabel.getPreferredSize();
        preTaxBalLabel.setBounds(10, 210, size.width, size.height);
        
        size = postTaxBalLabel.getPreferredSize();
        postTaxBalLabel.setBounds(10, 260, size.width, size.height);
        
        size = preTaxContributionLabel.getPreferredSize();
        preTaxContributionLabel.setBounds(10, 310, size.width, size.height);
        
        size = postTaxContributionLabel.getPreferredSize();
        postTaxContributionLabel.setBounds(10, 360, size.width, size.height);
        
        size = rateOfReturnLabel.getPreferredSize();
        rateOfReturnLabel.setBounds(10, 410, size.width, size.height);
    }
    
    private void setTextLocations()
    {   
        size = currentAge.getPreferredSize();
        currentAge.setBounds(250, 10, size.width, size.height);
        
        size = retirementAge.getPreferredSize();
        retirementAge.setBounds(250, 60, size.width, size.height);
        
        size = incomeTaxRate.getPreferredSize();
        incomeTaxRate.setBounds(250, 110, size.width, size.height);
        
        size = capitalGainsTaxRate.getPreferredSize();
        capitalGainsTaxRate.setBounds(250, 160, size.width, size.height);
        
        size = preTaxBal.getPreferredSize();
        preTaxBal.setBounds(250, 210, size.width, size.height);
        
        size = postTaxBal.getPreferredSize();
        postTaxBal.setBounds(250, 260, size.width, size.height);
        
        size = preTaxContribution.getPreferredSize();
        preTaxContribution.setBounds(250, 310, size.width, size.height);
        
        size = postTaxContribution.getPreferredSize();
        postTaxContribution.setBounds(250, 360, size.width, size.height);
        
        size = rateOfReturn.getPreferredSize();
        rateOfReturn.setBounds(250, 410, size.width, size.height);
    }
    
    private void setErrorLocations()
    {   
    	size = currentAgeError.getPreferredSize();
        currentAgeError.setBounds(175, 35, size.width, size.height);
        
        size = retirementAgeError.getPreferredSize();
        retirementAgeError.setBounds(175, 85, size.width, size.height);
        
        size = incomeTaxRateError.getPreferredSize();
        incomeTaxRateError.setBounds(175, 135, size.width, size.height);
        
        size = capitalGainsTaxRateError.getPreferredSize();
        capitalGainsTaxRateError.setBounds(175, 185, size.width, size.height);
        
        size = preTaxBalError.getPreferredSize();
        preTaxBalError.setBounds(175, 235, size.width, size.height);
        
        size = postTaxBalError.getPreferredSize();
        postTaxBalError.setBounds(175, 285, size.width, size.height);
        
        size = preTaxContributionError.getPreferredSize();
        preTaxContributionError.setBounds(175, 335, size.width, size.height);
        
        size = postTaxContributionError.getPreferredSize();
        postTaxContributionError.setBounds(175, 385, size.width, size.height);
        
        size = rateOfReturnError.getPreferredSize();
        rateOfReturnError.setBounds(175, 435, size.width, size.height);
    }
    
    private static void centreWindow(Window frame) 
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 6);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 5);
        frame.setLocation(x, y);
    }
    
    private void addLabels()
    {
    	retirementCalculator.add(currentAgeLabel);
    	retirementCalculator.add(retirementAgeLabel);
    	retirementCalculator.add(incomeTaxRateLabel);
    	retirementCalculator.add(capitalGainsTaxRateLabel);
    	retirementCalculator.add(preTaxBalLabel);
    	retirementCalculator.add(postTaxBalLabel);
    	retirementCalculator.add(preTaxContributionLabel);
    	retirementCalculator.add(postTaxContributionLabel);
    	retirementCalculator.add(rateOfReturnLabel);
    }
    
    private void addTextFields()
    {
    	retirementCalculator.add(currentAge);
    	retirementCalculator.add(retirementAge);
    	retirementCalculator.add(incomeTaxRate);
    	retirementCalculator.add(capitalGainsTaxRate);
    	retirementCalculator.add(preTaxBal);
    	retirementCalculator.add(postTaxBal);
    	retirementCalculator.add(preTaxContribution);
    	retirementCalculator.add(postTaxContribution);
    	retirementCalculator.add(rateOfReturn);
    }
    
    private void addErrorLabels()
    {
    	retirementCalculator.add(currentAgeError);
    	currentAgeError.setForeground(Color.red);
    	
    	retirementCalculator.add(retirementAgeError);
    	retirementAgeError.setForeground(Color.red);
    	
    	retirementCalculator.add(incomeTaxRateError);
    	incomeTaxRateError.setForeground(Color.red);
    	
    	retirementCalculator.add(capitalGainsTaxRateError);
    	capitalGainsTaxRateError.setForeground(Color.red);
    	
    	retirementCalculator.add(preTaxBalError);
    	preTaxBalError.setForeground(Color.red);
    	
    	retirementCalculator.add(postTaxBalError);
    	postTaxBalError.setForeground(Color.red);
    	
    	retirementCalculator.add(preTaxContributionError);
    	preTaxContributionError.setForeground(Color.red);
    	
    	retirementCalculator.add(postTaxContributionError);
    	postTaxContributionError.setForeground(Color.red);
    	
    	retirementCalculator.add(rateOfReturnError);
    	rateOfReturnError.setForeground(Color.red);
    	
    }
    
    private void addButtons()
    {
    	retirementCalculator.add(calculate);
    	retirementCalculator.add(clear);
    	//retirementCalculator.add(displayArea);
    	retirementCalculator.add(scrollpane);
        displayArea.setEditable(false);
    }
    
    public int checkCurrentAge()
    {
    	int input = 0;
    	try
    	{
    		input = Integer.parseInt(currentAge.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		currentAge.setText("");
    		currentAgeError.setVisible(true);
    		count++;
    	}
    	return input;
    }
    
    private int checkRetirementAge()
    {
		int input = 0;
    	try
    	{
    		input = Integer.parseInt(retirementAge.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		retirementAge.setText("");
    		retirementAgeError.setVisible(true);
    		count++;
    	}
    	return input;
    }
    
    private double checkIncomeTaxRate()
    {
		double input = 0.0;
    	try
    	{
    		input = Double.parseDouble(incomeTaxRate.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		incomeTaxRate.setText("");
    		incomeTaxRateError.setVisible(true);
    		count++;
    	}
    	return input;
    }
    
    private double checkCapitalGainsTaxRate()
    {
		double input = 0.0;
    	try
    	{
    		input = Double.parseDouble(capitalGainsTaxRate.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		capitalGainsTaxRate.setText("");
    		capitalGainsTaxRateError.setVisible(true);
    		count++;
    	}
    	return input;
    }
    
    private int checkPreTaxBalance()
    {
    	int input = 0;
    	try
    	{
    		input = Integer.parseInt(preTaxBal.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		preTaxBal.setText("");
    		preTaxBalError.setVisible(true);
    		count++;
    	}
    	return input;
    }
    
    private int checkPostTaxBalance()
    {
    	int input = 0;
    	try
    	{
    		input = Integer.parseInt(postTaxBal.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		postTaxBal.setText("");
    		postTaxBalError.setVisible(true);
    		count++;
    	}
    	return input;
    }
    
    private int checkPreTaxContribution()
    {
    	int input = 0;
    	try
    	{
    		input = Integer.parseInt(preTaxContribution.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		preTaxContribution.setText("");
    		preTaxContributionError.setVisible(true);
    		count++;
    	}
    	return input;
    }
    
    private int checkPostTaxContribution()
    {
    	int input = 0;
    	try
    	{
    		input = Integer.parseInt(postTaxContribution.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		postTaxContribution.setText("");
    		postTaxContributionError.setVisible(true);
    		count++;
    	}
    	return input;
    }
    
    private double checkRateOfReturn()
    {
    	double input = 0.0;
    	try
    	{
    		input = Double.parseDouble(rateOfReturn.getText());
        	if(input < 0)
        	{
        		throw new NumberFormatException();
        	}
    	}
    	catch (NumberFormatException e)
    	{
    		rateOfReturn.setText("");
    		rateOfReturnError.setVisible(true);
    		count++;
    	}
    	return input;
    }
}