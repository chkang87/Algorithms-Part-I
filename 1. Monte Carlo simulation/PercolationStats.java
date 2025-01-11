import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int[] opens;
    private double[] fractions;
    private int n;
    private int trials;
    public double mean;
    public double stddev;
    public double confidenceLo;
    public double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0){
            throw new IllegalArgumentException("n or tirals <=0");
        }
        this.opens = new int[trials];
        this.fractions = new double[trials];
        this.n = n;
        this.trials = trials;

        for(int i=0; i < trials; i++){
            Percolation percolo = new Percolation(n);

            while(true){
                int row = edu.princeton.cs.algs4.StdRandom.uniformInt(1, n+1);
                int col = edu.princeton.cs.algs4.StdRandom.uniformInt(1, n+1);

                if(percolo.isOpen(row, col) == false){
                    percolo.open(row, col);
                    if(percolo.percolates()){
                        this.opens[i] = percolo.numberOfOpenSites();
                        this.fractions[i] = (double)this.opens[i]/(n*n);
                        break;
                    }
                }
            }
        }
        this.mean = this.mean();
        this.stddev = this.stddev();
        this.confidenceLo = this.confidenceLo();
        this.confidenceHi = this.confidenceHi();        
    }

    // sample mean of percolation threshold
    public double mean(){
        double mean = 0.0;
        double sum = 0;
        for(int i=0; i<this.trials; i++){
            sum = sum + this.opens[i];
        }
        mean = sum/this.trials/(this.n*this.n);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        double stddevSum = 0.0;
        double stddevPow = 0.0;
        double stddev = 0.0;
        for(int i=0; i<this.trials; i++){
            stddevSum = stddevSum + Math.pow((this.fractions[i] - this.mean), 2);
        }
        stddevPow = stddevSum/(this.trials-1);
        stddev = Math.sqrt(stddevPow);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return this.mean - 1.96*this.stddev/Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return this.mean + 1.96*this.stddev/Math.sqrt(this.trials);
    }

   // test client (see below)
   public static void main(String[] args){
        PercolationStats Pers = new PercolationStats(200, 100);
        double[] confidenceInterval = {0.0, 0.0};
        confidenceInterval[0] = Pers.confidenceLo();
        confidenceInterval[1] = Pers.confidenceHi();
        System.out.println(Pers.mean);
        System.out.println(Pers.stddev);
        System.out.println("["+Pers.confidenceLo+", "+Pers.confidenceHi+"]");
   }

}
// test