package FeatureGeneration.Fpgrowth;

public class FrequentItemResults {
	public ItemSetNode []largeItemset;
	public int [] numLarge;
	//public int [] support1;
	public int [] largeItem1;	
	//public int expectedK;
	public int realK;
	//public int threshold;
	public String [] wordList;
	
	public FrequentItemResults(Fpt fpt)
	{
		this.largeItemset = fpt.largeItemset;
		this.numLarge = fpt.numLarge;
		//this.support1 = fpt.support1;
		this.largeItem1 = fpt.largeItem1;
		//this.expectedK = fpt.expectedK;
		this.realK = fpt.realK;
		//this.threshold = fpt.threshold;
		this.wordList = fpt.wordList;
	}
}
