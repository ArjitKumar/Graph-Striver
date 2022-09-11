import java.util.*;
public class graph{
    
// lecture 10
    class Rotten_Oranges
{
    //Function to find minimum time required to rot all oranges. 
    static class Pair{
        int row;
        int col;
        int time;
        Pair( int row, int col, int time) {
            this.row =row;
            this.col = col;
            this.time = time;
        }
    }
    public int orangesRotting(int[][] grid)
    {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        int cntfresh = 0;
        // boolean array banayi uske baad jo bhi rotten oragane h use true mark kiya aur q m 0 time k saath push kridya
        for( int i = 0; i < n ; i++) {
            for( int j = 0 ; j < m ; j++) {
                if(grid[i][j] == 2){
                    q.add(new Pair(i,j,0));
                    vis[i][j] = true;
                }
                if( grid[i][j] == 1) cntfresh++;
            }
        }
        int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}}; // direction array used to travel in 4 directions
        int tm = 0;
        int cnt = 0; // every time we are pushing into the queue we encounter fresh orange so ++
        while( ! q.isEmpty()){
            // rpa algorithm
            Pair p = q.poll();
            int r = p.row;
            int c = p.col;
            int t = p.time;
            tm = Math.max(t,tm); // storing the maximum time in every iteration
            
            for (int d = 0; d < dir.length; d++) {
               int nrow = r + dir[d][0];
               int ncol = c + dir[d][1];

            if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m && vis[nrow][ncol] == false && grid[nrow][ncol] == 1){
                // put in the queue with timer increased
                q.add(new Pair(nrow, ncol, t + 1));
                // and dont't forget to mark vis as true
                vis[nrow][ncol] = true;
                cnt++;
            }
                
        }
        }
        // We can also do this by counter
        // atlast we need and aditional check wheater all the fresh organges were convereted to rotten or not
        //  for( int i = 0; i < n ; i++) {
        //     for( int j = 0 ; j < m ; j++) {
        //         if(grid[i][j] == 1 && vis[i][j] == false){
        //           return -1;
        //         }
        //     }
        // }
        
        if( cnt != cntfresh) return -1;
        return tm;
        
    }
}

// ===================================== DETECT CYCLE IN UN-DIRECTED GRAPH =========================================
class DETECTCYCLEIN_UNDIRECTED_GRAPH {
    // Function to detect cycle in an undirected graph.
    static class Pair{
        int node;
        int parent;
        Pair( int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
    public boolean detectCycleUSingBFS( int src, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
    // marking src = 1;
    vis[src] = true;
    //pushing it into queue with parent 
    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair( src, -1));
    while( !q.isEmpty()){
        //rpa
        Pair p = q.poll();
        int node = p.node;
        int parent = p.parent;
        // adding neihbours
        for( int adjacentNode : adj.get(node)){
            if(!vis[adjacentNode]){
                vis[adjacentNode] = true;
                q.add(new Pair(adjacentNode,node));
            }else if(parent != adjacentNode) return true;
        }
    }
    return false;
    }
    // pepcoding
     public boolean IsCyclic(int src,ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(src);
        while( q.size() > 0){
            int node = q.removeFirst();
            // agr pehel se hi mark h to return krdo true
            if(vis[node] == true) return true;
            vis[node] = true;
            for( int it : adj.get(node)){
                if( vis[it] == false) {
                    q.add(it);
                }
            }
            
        }
        return false;
    }
    public boolean detectCycleUSingDFS(int src, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] vis) {
    // marking src = 1;
    vis[src] = true;
    
    for( int adjacentNode : adj.get(src)){
        if( !vis[adjacentNode] ){
            if(detectCycleUSingDFS(adjacentNode, src, adj, vis) == true) {
                return true;
            }
        }else if( parent != adjacentNode) return true;
    }
    return false;
    }
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] vis = new boolean[V];
        // writing this loop for connected components
        for( int i = 0 ; i < V ; i++) {
            if(!vis[i]){
               if( detectCycleUSingDFS(i,-1,adj,vis)) return true; 
            }
        }
        return false;
    }
}   

// ==========================Distance of nearest cell having 1 ===============================
// GFG 
// LEETOCODE -> 0/1 MATRIX 
class Distance_of_nearest_cell_having_1
{
    //Function to find distance of nearest 1 in the grid for each cell.
    // similar concept as rotten organges
    static class Pair{
        int row;
        int col;
        int time; // distance
        Pair( int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
    public int[][] nearest(int[][] grid)
    {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        ArrayDeque<Pair> q = new ArrayDeque<>();
        for( int i = 0 ; i < n ; i++){
            for( int j = 0 ; j < m ; j++) {
                if(grid[i][j] == 1) {
                    vis[i][j] = true;
                    q.add( new Pair( i,j,0));
                }
            }
        }
        int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
        while( q.size() > 0) {
            Pair p = q.removeFirst();
            int r = p.row;
            int c = p.col;
            int t = p.time; // distance
            res[r][c] = t; //updating the distance in distance matrix
            // now we need to travel in all 4 directions
            
            for (int d = 0; d < dir.length; d++) {
            int nrow = r + dir[d][0];
            int ncol = c + dir[d][1];

            if(nrow >= 0 && ncol >= 0 && nrow < n && ncol < m && vis[nrow][ncol] == false){
                vis[nrow][ncol] = true;
                q.add( new Pair( nrow, ncol, t + 1)); // adding dist + 1;
           }
         }
        }
         return res;
        
    }
}

// 
// ==========================   Replace_Os_with_Xs ===============================
// GFG and LEETOCODE

class Replace_Os_with_Xs{
     public static void dfs(int row,int col,char[][] b, boolean[][] vis,int[][] dir) {
        int n = b.length;
        int m = b[0].length;
        vis[row][col] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = row + dir[d][0];
            int c = col + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == false && b[r][c] == 'O'){
                vis[r][c] = true;
                dfs(r,c,b,vis,dir);
            }
                
        }
    }
    static char[][] fill(int n, int m, char board[][])
    {
        // code here
        int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
        boolean[][] vis = new boolean[n][m];
        // traversing for first row and last row
        for( int j = 0; j < m ; j++) {
            if(board[0][j] == 'O' && vis[0][j] == false){
                dfs(0,j,board,vis,dir);
            }
              if(board[n-1][j] =='O' && vis[n-1][j] == false){
                dfs(n-1,j,board,vis,dir);
            }
        }
        // traversing for first col and last col
         for( int i = 0; i < n ; i++) {
            if(board[i][0] == 'O' && vis[i][0] == false){
                dfs(i,0,board,vis,dir);
            }
             if(board[i][m-1] == 'O' && vis[i][m-1] == false){
                dfs(i,m-1,board,vis,dir);
            }
        }
        for( int  i = 0 ; i < n ; i++) {
            for( int j = 0 ; j < m ; j++) {
                if( board[i][j] == 'O' && vis[i][j] == false) {
                    board[i][j] = 'X';
                }
            }
        }
        return board;

    }
}


// ==========================  Number of Enclaves ===============================
class UsingBFS {
    // gfg
    public static void dfs(int row,int col,int[][] b, boolean[][] vis,int[][] dir) {
        int n = b.length;
        int m = b[0].length;
        vis[row][col] = true;
        for (int d = 0; d < dir.length; d++) {
            int r = row + dir[d][0];
            int c = col + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && vis[r][c] == false && b[r][c] == 1){
                vis[r][c] = true;
                dfs(r,c,b,vis,dir);
            }
                
        }
    }

    int numberOfEnclaves(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
        boolean[][] vis = new boolean[n][m];
        // traversing for first row and last row
        for( int j = 0; j < m ; j++) {
            if(board[0][j] == 1 && vis[0][j] == false){
                dfs(0,j,board,vis,dir);
            }
              if(board[n-1][j] == 1 && vis[n-1][j] == false){
                dfs(n-1,j,board,vis,dir);
            }
        }
        // traversing for first col and last col
         for( int i = 0; i < n ; i++) {
            if(board[i][0] == 1 && vis[i][0] == false){
                dfs(i,0,board,vis,dir);
            }
             if(board[i][m-1] == 1 && vis[i][m-1] == false){
                dfs(i,m-1,board,vis,dir);
            }
        }
        int cnt = 0;
        for( int i = 0 ; i < n ; i++) {
            for( int j = 0 ; j < m ; j++) {
                if( board[i][j] == 1 && vis[i][j] == false) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

// Leetcode 1020
class UsingDFS {
    static class Pair{
        int first;
        int second;
        Pair( int first, int second){
            this.first = first;
            this.second = second;
        }
    }
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        for( int i = 0 ; i < n;i++) {
            for( int j = 0 ; j < m ; j++) {
                // first row, first col, last row and last col
                if( i==0 || j==0 || i == n-1 || j ==m-1){ 
                    if( grid[i][j] == 1){
                        // put it into queue and mark vis
                        q.add( new Pair(i,j));
                        vis[i][j] = true;
                    }
                }
            }
        }
        
        // creating deltarow and deltacol
        int[] deltarow = {-1,+0,+1,0};
        int[] deltacol = {0,+1,+0,-1};
        while( !q.isEmpty()){
            int row = q.peek().first;
            int col = q.peek().second;
            q.remove();
            // travelling in all 4 directions
            for( int i = 0 ; i < 4 ; i++) {
                // calculating r and c
                int r = row + deltarow[i]; 
                int c = col + deltacol[i]; 
                if( r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1 && vis[r][c] == false){
                    // put it into queue and mark it visisted
                    q.add(new Pair(r,c));
                    vis[r][c] = true;
                }
            }
        }
        int cnt = 0;
        for( int i=0;i<n;i++){
            for(int j=0; j<m;j++) {
                if( grid[i][j] == 1 && vis[i][j] == false) cnt++;
            }
        }
        return cnt;
    }
}

// ==========================  Number of Distinct Islands ===============================
class No_Of_Distinct_Island {
      static class Pair{
        int first;
        int second;
        Pair( int first, int second){
            this.first = first;
            this.second = second;
        }
    }
    // Note the List of string is required because in every other case be it pair or something testcase
    // are failing
    public static void dfs(int row, int col,int[][] grid, boolean[][] vis, ArrayList<String> list,int baserow, int basecol){
        // baserow and base colum remain same for one dfs or bfs traversal 
        vis[row][col] = true;
        int n = grid.length;
        int m = grid[0].length;
        // calculating the distance and adding that intot the list
        
    /*  {  int rowval = row - baserow;
           int colval = col - basecol;
           list.add(rowval + " " + colval);
      }                                                */
        // or
        list.add(toString(row - baserow,col - basecol));
        // now simply run dfs for 4 directions
        int[] deltarow = {-1,0,+1,0};
        int[] deltacol = {0,-1,0,+1};
        for( int i =0 ; i < 4 ; i++) {
             int r = row + deltarow[i]; 
             int c = col + deltacol[i]; 
             if( r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1 && vis[r][c] == false){
                   dfs(r,c,grid,vis,list,baserow,basecol);
                }
        }
        
    }
    private static String toString( int r, int c) {
        return Integer.toString(r) + " " + Integer.toString(c);
    }
    static int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        HashSet<ArrayList<String>> set = new HashSet<>();
        for( int i = 0 ; i < n;i++) {
            for( int j = 0 ; j < m ; j++) {
                if( grid[i][j] == 1 && vis[i][j] == false) {
                    // passing list and base row and base coloum
                    ArrayList<String> list = new ArrayList<>();
                    dfs(i,j,grid,vis,list,i,j);
                    set.add(list);
                }
            }
        }
        return set.size();
  }
}


// =========================================== isBipartite ========================================
class isBipartite
{   
    private boolean BFS( int start, ArrayList<ArrayList<Integer>>adj,int[] color){
        color[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while( !q.isEmpty()){
            int node = q.poll();
            
            for( int it : adj.get(node)){
                if(color[it] == -1) { // not colored yet so color it with opp color
                    color[it] = 1 - color[node]; // coloring with opp color of adj node // note !color[node] doesnt work
                    q.add(it);
                }else if(color[it] == color[node]){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean dfs( int node, int col, int color[],ArrayList<ArrayList<Integer>>adj ){
        
        // mark
        color[node] = col;
        for(int it : adj.get(node)){
            if( color[it] == -1){ // please color it
            
                if( dfs(it,1-col,color,adj) == false) return false; // agr ye false to yhi se return age jarrorat nhi 
                
            }else if(color[it] == col){
                return false;
            } 
        }
        return true;
    }
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
    {
        // Code here
        int[] color = new int[V];
        Arrays.fill( color, -1);
        // checking for multiple components

        // for BFS
        // for( int i = 0 ; i < V ; i++) {
        //     if( color[i] == -1) {
        //         if( BFS( i, adj, color) == false) return false; 
        //     }
        // }
        
        // for DFS
        for( int i = 0 ; i < V;i++) {
            if( color[i] == -1) {
                // passing node ,col, arr, and adj
                if(dfs(i,0,color,adj) == false) return false;
            }
        }
        return true;
    }
}


// ========================  Detect cycle in a directed graph using BFS.============================
class Solution {
    // Function to detect cycle in a directed graph.
    private boolean dfs( int node,boolean[] vis,boolean[] pathvis,ArrayList<ArrayList<Integer>> adj ){
        // mark 
        vis[node] = true;
        pathvis[node] = true;
        // traversing for adjecent nodes
        for( int it : adj.get(node)){
            // when the node is not visited
            if( !vis[it]){
                // if in future any cycle is present and it returns true simply return true
                if( dfs( it,vis,pathvis,adj) == true) return true;
            }
            // /when the node is visited we simply need to check wheather pathvis == true or not
            else if(pathvis[it] == true){
                return true;
            }
        }
        // unmarking path vis because we are backtracing
        pathvis[node] = false;
        return false;
    }
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        boolean[] vis = new boolean[V];
        boolean[] pathvis = new boolean[V];
        for( int i = 0 ; i < V ; i++) {
            if( !vis[i]){
                if( dfs(i,vis,pathvis,adj) == true) return true;
            }
        }
        return false;
    }
}

public static void main(String[] args) {

    }
}