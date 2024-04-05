package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.exo1.enums.GenreMusical;
import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testRequestCountByGenreMusicalEquals() {
        //given
        ArtistEntity artist1 = ArtistEntity
                .builder()
                .name("Drake")
                .genreMusical(GenreMusical.RAP)
                .build();

        ArtistEntity artist2 = ArtistEntity
                .builder()
                .name("Ninho")
                .genreMusical(GenreMusical.RAP)
                .build();

        ArtistEntity artist3 = ArtistEntity
                .builder()
                .name("Usher")
                .genreMusical(GenreMusical.RANDB)
                .build();

        artistRepository.save(artist1);
        artistRepository.save(artist2);
        artistRepository.save(artist3);
        //when
        int countByGenreMusicalRAP = artistRepository.countByGenreMusicalEquals(GenreMusical.RAP);
        int countByGenreMusicalRANDB = artistRepository.countByGenreMusicalEquals(GenreMusical.RANDB);

        //then
        assertThat(countByGenreMusicalRAP).isEqualTo(2);
        assertThat(countByGenreMusicalRANDB).isEqualTo(1);
    }
}
