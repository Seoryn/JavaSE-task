package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private String name;
    private List<Book> books;

    // 생성자
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 도서 추가
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
            System.out.println(
                "도서가 추가되었습니다: " + book.getTitle()
            );
        }
    }

    // 제목으로 검색
    public Book findByTitle(String title) {
        for (Book book : books) {
            if (title != null && title.equals(book.getTitle())) {
                return book;
            }
        }
        return null;
    }

    // 저자로 검색
    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<Book>();

        for (Book book : books) {
            if (author != null && author.equals(book.getAuthor())) {
                result.add(book);
            }
        }
        return result;
    }

    // ISBN으로 검색
    public Book findByISBN(String isbn) {
        for (Book book : books) {
            if (isbn != null && isbn.equals(book.getIsbn())) {
                return book;
            }
        }
        return null;
    }

    // 상세 설명에 나온 메서드 이름도 지원
    public Book findBookByTitle(String title) {
        return findByTitle(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        return findByAuthor(author);
    }

    public Book findBookByISBN(String isbn) {
        return findByISBN(isbn);
    }

    // ISBN으로 대출
    public boolean checkOutBook(String isbn) {
        Book book = findByISBN(isbn);

        if (book != null) {
            return book.checkOut();
        }
        return false;
    }

    // ISBN으로 반납
    public boolean returnBook(String isbn) {
        Book book = findByISBN(isbn);

        if (book != null && !book.isAvailable()) {
            book.returnBook();
            return true;
        }
        return false;
    }

    // 대출 가능한 도서 목록
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<Book>();

        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    // 전체 도서 목록
    public List<Book> getAllBooks() {
        return new ArrayList<Book>(books);
    }

    // 전체 도서 수
    public int getTotalBooks() {
        return books.size();
    }

    // 대출 가능한 도서 수
    public int getAvailableBooksCount() {
        return getAvailableBooks().size();
    }

    // 대출 중인 도서 수
    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
}
