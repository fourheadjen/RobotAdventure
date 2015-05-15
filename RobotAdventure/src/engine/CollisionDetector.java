package engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

public class CollisionDetector
{
	
	Stack<Integer> active=new Stack<Integer>();
	TreeMap<Double,String> sortAndSweep=new TreeMap<Double,String>();
	//ArrayList<Integer> ranges

	public CollisionDetector()
	{
		
	}
	
	public ArrayList<Polygon> broadCheck(List<Polygon> poly)
	{
		sortAndSweep.clear();
		active.clear();
		int i=0;
		for(;i<poly.size();i++)
		{
			sortAndSweep.put(poly.get(i).getLowestX(), "B"+i);//x value, object index
			sortAndSweep.put(poly.get(i).getHighestX(), "E"+i);
		}
		while(!sortAndSweep.isEmpty())
		{
			String delete=sortAndSweep.pollFirstEntry().getValue();
			if(delete.contains("E"))
			{
				int n=Integer.parseInt(delete.substring(1));
				Iterator<Integer> itr=active.iterator();
				while(itr.hasNext())
				{
					int current=itr.next();
					if(current==n)
						itr.remove();
					else
					{
						SATCheck(poly.get(n), poly.get(current));
					}
				}
			}
			else
			{
				int n=Integer.parseInt(delete.substring(1));
				active.add(n);
			}
		}
		return null;
	}
	
	private void SATCheck(Polygon a,Polygon b)
	{
		//System.out.println("sat");
	}

}
