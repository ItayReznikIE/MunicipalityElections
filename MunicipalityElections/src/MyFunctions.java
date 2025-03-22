import java.util.Comparator;
import java.util.Vector;

public class MyFunctions {
	
	public static void bubbleSort(Vector<? extends Comparable> v, Comparator c)  
	{
		for(int i =0; i<v.size(); i++)
		{
			for (int j=0 ; j<v.size()-j; j++)
			{
				if(c.compare(v.elementAt(j), v.elementAt(j+1)) == 1)
				{
					swap(v,j,j+1);
				}
			}
		}
	}
	
	public static <T> void swap(Vector<T> v, int a, int b)
	{
		T save = v.elementAt(b);
		v.set(b,v.elementAt(a));
		v.set(a,save);
	}

}
