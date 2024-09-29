package es.uca.GaviotaPK.GaviotaParking;

import java.util.List;


public interface BaseRepository<E> {
    List<E> getAll();
    void add(E entity);
    void update(E entity);
    void delete(E entity);
}
