package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;


    public String addBook(BookRequestDto bookRequestDto){

        int authorId  = bookRequestDto.getAuthorId();

        Author author = authorRepository.findById(authorId).get();

        Book book = new Book();
        book.setGenre(bookRequestDto.getGenre());
        book.setIssued(false);
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());
        book.setAuthor(author);

        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);

        authorRepository.save(author);

        return "Book Added successfully";

    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
