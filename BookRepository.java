package com.example.demo.book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface BookRepository extends JpaRepository<Book,Integer> {
        public List<Book> findAll();
        public Optional<Book> findById(Integer id);
        @Query("select b from Book b where b.code= ?1")
        public Optional<Book> findByCode(Integer code);

        public Optional<Book> findByName(String name);

    }
