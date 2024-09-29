package es.uca.GaviotaPK.GaviotaParking;

import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface AbonoRepository extends BaseRepository<Abono> {
    Abono getAbonoById(UUID id);
}
