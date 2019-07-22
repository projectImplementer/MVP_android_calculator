package designpattern_mvp.calculator.Presenter;


import designpattern_mvp.calculator.Enums.OperationsEnumeration;

import static designpattern_mvp.calculator.Extensions.StringExtensions.ADDITION_START;
import static designpattern_mvp.calculator.Extensions.StringExtensions.DIVISION_START;
import static designpattern_mvp.calculator.Extensions.StringExtensions.DOUBLE_LINE;
import static designpattern_mvp.calculator.Extensions.StringExtensions.MULTIPLICATION_START;
import static designpattern_mvp.calculator.Extensions.StringExtensions.NEW_LINE;
import static designpattern_mvp.calculator.Extensions.StringExtensions.PERCENTAGE_START;
import static designpattern_mvp.calculator.Extensions.StringExtensions.POWER_START;
import static designpattern_mvp.calculator.Extensions.StringExtensions.SQUAREROOT_START;
import static designpattern_mvp.calculator.Extensions.StringExtensions.SUBSTRACTION_START;

public class Calculation
{
	public StringBuilder calculatedString(double nr1, double nr2)
	{
		String[] arrayBeginText = operationsText();
		StringBuilder strBuilder = new StringBuilder();
		double[] calcs = AddCalculationsToList(nr1, nr2);

		for(OperationsEnumeration.Operation op : OperationsEnumeration.Operation.values())
		{
			strBuilder.append(arrayBeginText[op.ordinal()]).append(NEW_LINE).append(calcs[op.ordinal()]).append(DOUBLE_LINE);
		}

		return strBuilder;
	}

	private double[] AddCalculationsToList(double nr1, double nr2)
	{
			return new double[]
			{
				nr1 + nr2,
				nr1 - nr2,
				nr1 * nr2,
				nr1 / nr2,
				nr1 % nr2,
				Math.pow(nr1, nr2),
				Math.pow(Math.E, Math.log(nr1)/nr2)
				//Alternative Math.sqrt(nr1,nr2);	
			};
	}

	private static String[] operationsText()
	{
		return new String[]
				{
						ADDITION_START,
						SUBSTRACTION_START,
						MULTIPLICATION_START,
						DIVISION_START,
						PERCENTAGE_START,
						POWER_START,
						SQUAREROOT_START
				};
	}
}
