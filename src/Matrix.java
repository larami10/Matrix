/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * @author Luis Ramirez-Zamacona, Professor Acuna
 * @version 05/29/2019
 */
public class Matrix implements MatrixInterface {
	
	private final int rows;		// Declare final int variable rows
	private final int columns;	// Declare final int variable columns
	private final int[][] matrix;	// Declare final int 2D Array matrix
	
	/**
	 * Constructor will initialize the rows and columns of a matrix
	 * 
	 * @param data 2D integer matrix
	 */
	public Matrix(int[][] data)
	{
		// Initialize the number of rows
		rows = data.length;
		
		// Initialize the number of columns
		if(rows == 0)
		{
			// Initialize columns equal to 0 if rows equals 0
			columns = 0;
		}
		// Else initialize the number of columns from int[][] data 
		else
		{
			columns = data[0].length;
		}
		
		// Initialize the size of our matrix
		matrix = new int[rows][columns];
		
		// Initialize the elements in matrix
		for(int i = 0; i < data.length; i++)
		{
			for(int j = 0; j < data[i].length; j++)
			{
				matrix[i][j] = data[i][j];
			}
		}
	}

	/**
	*  Returns the element at particular point in the matrix.
	*  @param y y position
	*  @param x x position
	*  @return element
	*/
	public int getElement(int y, int x)
	{
		return matrix[y][x];
	}

	/**
	*  Returns the number of rows in the matrix.
	*  @return rows
	*/
	public int getRows()
	{
		return rows;
	}
	
	/**
	*  Returns the number of columns in the matrix.
	*  @return columns
	*/
	public int getColumns()
	{
		return columns;
	}

	/**
	*  Returns this matrix scaled by a factor. That is, computes kA where
	*  k is a constant and A is a matrix (this object).
	*  @param scalar scalar
	*  @return matrix
	*/
	public MatrixInterface scale(int scalar)
	{
		// Initialize a temporary matrix
		int[][] temp = new int[rows][columns];
		
		// Find the appropriate elements for scaled matrix
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				temp[i][j] = scalar * matrix[i][j];
			}
		}
		
		// Create and return the new scaled matrix
		MatrixInterface scale = new Matrix(temp);
		
		return scale;
	}
	
	/**
	*  Returns this matrix added with another matrix. That is, computes A+B
	*  where A and B are matrices (this object, and another respectively).
	*  @param other added
	*  @return matrix
	*  @throws RuntimeException if matrices do not have matching dimensions.
	*/
	public MatrixInterface plus(MatrixInterface other)
	{
		// If the dimensions of both matrices are not the same, throw RuntimeException
		if(rows != other.getRows() && columns != other.getColumns())
		{
			throw new RuntimeException("Cannot Add a " + rows + "x" + columns
										+ " Matrix with a " + other.getRows() + "x"
										+ other.getColumns() + " Matrix");
		}
		else
		{
			// Initialize a temporary matrix
			int[][] temp = new int[rows][columns];
			
			// Find the appropriate elements for added matrix
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					temp[i][j] = matrix[i][j] + other.getElement(i, j);
				}
			}
			
			// Create and return the new added matrix
			MatrixInterface plus = new Matrix(temp);
			
			return plus;
		}
	}

	/**
	*  Returns this matrix subtracted by another matrix. That is, computes A-B
	*  where A and B are matrices (this object, and another respectively).
	*  @param other subtracted
	*  @return matrix
	*  @throws RuntimeException if matrices do not have matching dimensions.
	*/
	public MatrixInterface minus(MatrixInterface other)
	{
		// If the dimensions of both matrices are not the same, throw RuntimeException
		if(rows != other.getRows() && columns != other.getColumns())
		{
			throw new RuntimeException("Cannot Subtract a " + rows + "x" + columns
										+ " Matrix with a " + other.getRows() + "x"
										+ other.getColumns() + " Matrix");
		}
		else
		{
			// Initialize a temporary matrix
			int[][] temp = new int[rows][columns];
			
			// Find the appropriate elements for subtracted matrix
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					temp[i][j] = matrix[i][j] - other.getElement(i, j);
				}
			}
			
			// Create and return the new subtracted matrix
			MatrixInterface minus = new Matrix(temp);
			
			return minus;
		}
	}
	
	/**
	*  Returns this matrix multiplied by another matrix. That is, computes AB
	*  where A and B are matrices (this object, and another respectively).
	*  @param other multiplicand
	*  @return matrix
	*  @throws RuntimeException if matrices do not have matching dimensions.
	*/
	public MatrixInterface multiply(MatrixInterface other)
	{
		// If the number of columns in matrix A is not the same as the
		// number of rows in matrix B, throw RuntimeException
		if(columns != other.getRows())
		{
			throw new RuntimeException("Cannot Multiply a " + rows + "x" + columns
										+ " Matrix with a " + other.getRows() + "x"
										+ other.getColumns() + " Matrix");
		}
		else
		{
			// Initialize a temporary matrix
			int[][] temp = new int[rows][columns];
			
			// Find the appropriate elements for multiplied matrix
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					for(int k = 0; k < rows; k++)
					{
						temp[i][j] += matrix[i][k] * other.getElement(k, j);
					}
				}
			}
			
			// Create and return the new multiplied matrix
			MatrixInterface multiply = new Matrix(temp);
			
			return multiply;
		}
	}

	/**
	*  Returns true if this matrix matches another matrix.
	*  @param other another matrix
	*  @return equality
	*/
	@Override
	public boolean equals(Object other)
	{
		// Return true of this matrix matches other matrix
		if(other == this)
			return true;
		
		// Return false if other matrix equals null
		if(other == null)
			return false;
		
		// Return false of the element in other matrix are not
		// the same as this matrix
		if(other.toString().equals(this.toString()) == false)
			return false;
		
		MatrixInterface m = (MatrixInterface) other;
		
		// Return true if the number of rows and columns are the same
		// for both matrices.
		return this.getRows() == m.getRows() && this.getColumns() == m.getColumns();
	}
	
	/**
	*  Returns a string representation of this matrix. A new line character will
	*  separate each row, while a space will separate each column.
	*  @return string representation
	*/
	@Override
	public String toString()
	{
		int count = 0;	// Counter variable
		
		String s = "";	// Declare String variable s
		
		// Print the Element of a Matrix
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				s += matrix[i][j] + " ";
				count++;
				
				if(count % 3 == 0)
				{
					s += "\n";
				}
			}
		}
		return s;
	}
    
    
    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        
        MatrixInterface m1 = new Matrix(data1);
        MatrixInterface m2 = new Matrix(data2);
        MatrixInterface m3 = new Matrix(data3);
        MatrixInterface m4 = new Matrix(data4);
        
        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());
        
        //check for reference issues
        System.out.println("\nm2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true
        
        //test operations (valid)
        System.out.println("\n2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        System.out.println("m2 * m3:\n" + m2.multiply(m3));
        
        //test operations (invalid)
        System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 - m2" + m1.minus(m2));
        //System.out.println("m1 * m2" + m1.multiply(m2));
    }
}