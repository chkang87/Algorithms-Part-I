import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int n;
    private int topSite;
    private int bottomSite;
    private edu.princeton.cs.algs4.WeightedQuickUnionUF union;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if(n <= 0){
            throw new IllegalArgumentException("n <= 0");
        }
        this.grid = new boolean[n+1][n+1];
        this.n = n;
        this.union = new edu.princeton.cs.algs4.WeightedQuickUnionUF(n*n+2);
        this.topSite = 0;
        this.bottomSite = n*n+1;
        // unions the first row and the top site
        for(int i = 1; i <= n; i++){
            this.union.union(this.topSite, i);
        }
        // unions the first row and the bottom site
        for(int i = n*n-n+1; i <= n*n; i++){
            this.union.union(this.bottomSite, i);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(row <= 0 || row > n || col <= 0 || col > n){
            throw new IllegalArgumentException("row or col out of index range");
        }

        if(this.isOpen(row, col) == false){
            this.grid[row][col] = true;
            int currentIdx = this.getUnionIdx(row, col);
            int upIdx = this.getUnionIdx(row-1, col);
            int downIdx = this.getUnionIdx(row+1, col);
            int leftIdx = this.getUnionIdx(row, col-1);
            int rightIdx = this.getUnionIdx(row, col+1);

            // union up
            if(row != 1 && this.isOpen(row-1, col)){
                this.union.union(currentIdx, upIdx);
            }
            // union down
            if(row != n && this.isOpen(row+1, col)){
                this.union.union(currentIdx, downIdx);
            }
            // union left
            if(col != 1 && this.isOpen(row, col-1)){
                this.union.union(currentIdx, leftIdx);
            }
            // union right
            if(col != n && this.isOpen(row, col+1)){
                this.union.union(currentIdx, rightIdx);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row <= 0 || row > n || col <= 0 || col > n){
            throw new IllegalArgumentException("row or col out of index range");
        }

        if(grid[row][col] == true){
            return true;
        }
        else{
            return false;
        }

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(row <= 0 || row > n || col <= 0 || col > n){
            throw new IllegalArgumentException("row or col out of index range");
        }
        return this.union.connected(this.topSite, this.getUnionIdx(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        int openCount = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(this.isOpen(i, j)){
                    openCount++;
                }
            }
        }
        return openCount;
    }

    // does the system percolate?
    public boolean percolates(){
        if(this.union.connected(topSite, bottomSite)){
            return true;
        }
        else{
            return false;
        }
    }

    public int getUnionIdx(int row, int col){
        return (row-1) * this.n + col;
    }

    // test client (optional)
    // public static void main(String[] args){
    //     boolean isPercolates = false;
    //     boolean isFull = false;
    //     int openCount = 0;
    //     Percolation percolo = new Percolation(5);
    //     percolo.open(2, 1);
    //     percolo.open(1, 1);
    //     percolo.open(2, 2);
    //     percolo.open(3, 2);
    //     percolo.open(4, 2);
    //     isPercolates = percolo.percolates();
    //     percolo.open(5, 3);
    //     percolo.open(5, 1);
    //     percolo.open(5, 2);
    //     isPercolates = percolo.percolates();
    //     openCount = percolo.numberOfOpenSites();
    //     System.out.println("open");
    // }
}