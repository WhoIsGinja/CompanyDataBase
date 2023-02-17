package woo;

class Book extends Product {
    private static final long serialVersionUID = 201708301010L;

    private String title;
    private String author;
    private String ISBN;

    public Book(String id, String title, String author, String ISBN, String supplierID, int price, int criticalValue) {
        super(id, price, criticalValue, supplierID);
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;

        setPeriod(3);
    }

    /* Getters */
    public String getTitle(){ return this.title; } 
    public String getAuthor() { return this.author; }
    public String getISBN() { return this.ISBN; }

    @Override
    public String toString() {
        return "BOOK" + "|" + super.toString() + "|" + this.title + "|" + this.author + "|" + this.ISBN;
    }
}
