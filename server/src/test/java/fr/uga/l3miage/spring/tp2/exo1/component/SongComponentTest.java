package fr.uga.l3miage.spring.tp2.exo1.component;

import fr.uga.l3miage.spring.tp2.exo1.components.SongComponent;
import fr.uga.l3miage.spring.tp2.exo1.exceptions.technical.NotFoundSongEntityException;
import fr.uga.l3miage.spring.tp2.exo1.models.SongEntity;
import fr.uga.l3miage.spring.tp2.exo1.repositories.SongRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SongComponentTest {
    @Autowired
    private SongComponent songComponent;
    @MockBean
    private SongRepository songRepository;

    @Test
    void GetSongEntityNotFound() {
        //Given
        when(songRepository.findById(anyString())).thenReturn(Optional.empty());

        //then - when
        assertThrows(NotFoundSongEntityException.class, ()->songComponent.getSongEntityById("hotline bling"));
    }

    @Test
    void GetSongEntityFound() {
        //Given
        SongEntity songEntity = SongEntity.builder()
                .title("hotling bling")
                .build();
        when(songRepository.findById(anyString())).thenReturn(Optional.of(songEntity));

        //Then - when
        assertDoesNotThrow(()->songComponent.getSongEntityById("hotline bling"));
    }

    @Test
    void GetSetSongEntity() {
        //Given
        Set<SongEntity> songs = new HashSet<>();

        SongEntity song1 = SongEntity.builder()
                .title("hotling bling")
                .build();
        SongEntity song2 = SongEntity.builder()
                .title("Love yourself")
                .build();
        songs.add(song1);
        songs.add(song2);

        when(songRepository.findAllByTitleIsIn(anySet())).thenReturn(songs);

        //Then - when
        assertDoesNotThrow(()->songComponent.getSetSongEntity(anySet()));

    }
}
