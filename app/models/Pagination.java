package models;

public class Pagination {
	
	public int first;
	public int last;
	public int current;

	public Pagination (int first, int last, int current) {
		this.first = first;
		this.last = last;
		this.current = current;
	}

}