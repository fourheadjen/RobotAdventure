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
	
	Vector[] axes1;
	Vector[] axes2;
	
	Vector[] corners1;
	Vector[] corners2;
	
	Projection p1=new Projection();
	Projection p2=new Projection();
	
	Vector axis;
	Vector minimal;
	Vector point;
	
	double overlap;
	//ArrayList<Integer> ranges
	
	int i;

	public CollisionDetector()
	{
		
	}
	
	public void broadCheck(List<Polygon> poly)
	{
		sortAndSweep.clear();
		active.clear();
		i=0;
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
	}
	
	private boolean SATCheck(Polygon a,Polygon b)
	{
		//System.out.println("sat");
		axes1=a.getAxes();
		axes2=b.getAxes();
		
		//loop over axes1
		for(i=0;i<axes1.length;i++)
		{
			axis=axes1[i];
			p1.project(a,axis);
			p2.project(b,axis);
			
			if(!p1.overlap(p2))
			{
				return false;
			}
			else
			{
				overlap=p1.getOverlap(p2);
				minimal=axis;
			}
		}
		
		//loop over axes2
		for(i=0;i<axes2.length;i++)
		{
			axis=axes2[i];
			p1.project(a,axis);
			p2.project(b,axis);
			
			if(!p1.overlap(p2))
			{
				return false;
			}
			else
			{
				overlap=p1.getOverlap(p2);
				minimal=axis;
			}
		}
		System.out.println(minimal.XExact()+" "+minimal.YExact()+"   "+overlap);
		FindPointOfCollision(a,b);
		return true;
	}
	
	private void FindPointOfCollision(Polygon a,Polygon b)
	{
		stepOne(a,b);
	}
	
	private void stepOne(Polygon a,Polygon b)
	{
		corners1=a.getCorners();
		corners2=b.getCorners();
		for(i=0;i<a.getCount();i++)
		{
			
		}
	}

}
