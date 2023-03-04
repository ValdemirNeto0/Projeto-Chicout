package main.java.com.example.desafio.classes;
import java.util.List;
import java.util.Optional;


//Class interface DAO

public interface DAO<T>{
    Optional<T>get(String nome);
    List<T>getAll();
    void save(T t);
    void update(T t, String[] Parametros);
    void delete(T t);
}
