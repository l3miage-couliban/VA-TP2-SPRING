package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.exo1.enums.GenreMusical;
import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity,String> {

    int countByGenreMusicalEquals(GenreMusical genreMusical);
}
