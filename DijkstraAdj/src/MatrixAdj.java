
public class MatrixAdj {
	private int mEdgNum;
	private char[] mVexs;
	private int[][] mMatrix;
	private static final int INF = Integer.MAX_VALUE;
	
	public 	MatrixAdj(char[] vexs, int[][] matrix ){
		
		//初始化数据
		int vlen = vexs.length;
		
		mVexs = new char[vlen];
		for(int i=0; i<vlen; i++){
			mVexs[i] = vexs[i];
		}
		
		for(int i=0; i<vexs.length; i++){
			for(int j=i+1; j<vexs.length; j++){
				if(matrix[i][j] != INF)
					mEdgNum++;
			}
		}
		
		mMatrix = new int[vlen][vlen];
		for(int i=0; i<vexs.length; i++){
			for(int j=0; j<vexs.length; j++){
				if(matrix[i][j] != INF)
					mMatrix[i][j] = matrix[i][j];
				else
					mMatrix[i][j] = INF;
			}
		}
	}
	
	/* 参数说明：
     *       vs -- 起始顶点(start vertex)。即计算"顶点vs"到其它顶点的最短路径。
     *     prev -- 前驱顶点数组。即，prev[i]的值是"顶点vs"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点。
     *     dist -- 长度数组。即，dist[i]是"顶点vs"到"顶点i"的最短路径的长度。
	 */
	public void Dijkstra(int vs, int[] prev, int[] dist){
		
		//flag[i]=true 表示已经获得最短路径；flag[i]=false 表示还未获得最短路径；
		boolean[] flag = new boolean[mVexs.length];
		
		for(int i=0; i<mVexs.length; i++){
			flag[i]=false;
			prev[i]= 0;
			dist[i]=mMatrix[vs][i];
		}
		
		
		flag[vs]=true;
		dist[vs]=0;
		
		int k=0;
		for(int i=0; i<mVexs.length-1; i++){
			int min = INF;
			for(int j=0; j<mVexs.length; j++){
				if(flag[j]==false && dist[j]<min){
					min=dist[j];
					k=j;
				}
			}
			flag[k]=true;
			
			for(int j=0; j<mVexs.length; j++){
				int temp = mMatrix[k][j]==INF ? INF : (min+mMatrix[k][j]);
				if(flag[j]==false && temp<dist[j]){
					dist[j]=temp;
					prev[j]=k;
				}
			}	
		}
		
		System.out.printf("Dijkstra(%c):\n",mVexs[vs]);
		for(int i=0; i<mVexs.length; i++){
			System.out.printf("shortest(%c,%c) = %d , %c\n",mVexs[vs],mVexs[i],dist[i],mVexs[prev[i]]);
		}
	}
	
	public static void main(String[] args){
		
		char[] vexs={'A','B','C','D','E'};
		int[][] matrix ={/*A*/	/*B*/	/*C*/	/*D*/	/*E*/
				/*A*/	{ 0  ,   2  ,    INF ,    8 ,    12 },
				/*B*/	{ 2  ,   0  ,     3 ,     4 ,    INF},
				/*C*/	{ INF,   3  ,     0 ,     3,      2 },
				/*D*/	{ 8  ,   4  ,     3 ,     0 ,     4 },
				/*E*/	{ 12 ,  INF ,     2 ,    4 ,      0 }
		};
		
		MatrixAdj m = new MatrixAdj(vexs,matrix);
		int[] prev = new int[vexs.length];
		int[] dist = new int[vexs.length];
		m.Dijkstra(0, prev, dist);
	}
}
