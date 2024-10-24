package com.aeropink.plot.service;

import com.aeropink.plot.model.PlayerCard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    private static final List<PlayerCard> allCardsList = new ArrayList<>();

    static {
        // Agrega todas las cartas al allCardsList
        allCardsList.add(new PlayerCard("La selfie", "/imagenes/01_la_selfie.jpg", "/audios/01_la_selfie.mp3"));
        allCardsList.add(new PlayerCard("La feminista", "/imagenes/02_la_feminista.jpg", "/audios/02_la_feminista.mp3"));
        allCardsList.add(new PlayerCard("La tarea", "/imagenes/03_la_tarea.jpg", "/audios/03_la_tarea.mp3"));
        allCardsList.add(new PlayerCard("La notificación", "/imagenes/04_la_notificacion.jpg", "/audios/04_la_notificacion.mp3"));
        allCardsList.add(new PlayerCard("La realidad virtual", "/imagenes/05_la_realidad_virtual.jpg", "/audios/05_la_realidad_virtual.mp3"));
        allCardsList.add(new PlayerCard("El hipster", "/imagenes/06_el_hipster.jpg", "/audios/06_el_hipster.mp3"));
        allCardsList.add(new PlayerCard("La hashtag", "/imagenes/07_la_hashtag.jpg", "/audios/07_la_hashtag.mp3"));
        allCardsList.add(new PlayerCard("La papaya", "/imagenes/08_la_papaya.jpg", "/audios/08_la_papaya.mp3"));
        allCardsList.add(new PlayerCard("El calentamiento global", "/imagenes/09_el_calentamiento_global.jpg", "/audios/09_el_calentamiento_global.mp3"));
        allCardsList.add(new PlayerCard("El makeup tutorial", "/imagenes/10_el_makeup_tutorial.jpg", "/audios/10_el_makeup_tutorial.mp3"));
        allCardsList.add(new PlayerCard("La bota picuda", "/imagenes/11_la_bota_picuda.jpg", "/audios/11_la_bota_picuda.mp3"));
        allCardsList.add(new PlayerCard("El podcast", "/imagenes/12_el_podcast.jpg", "/audios/12_el_podcast.mp3"));
        allCardsList.add(new PlayerCard("El yoga", "/imagenes/13_el_yoga.jpg", "/audios/13_el_yoga.mp3"));
        allCardsList.add(new PlayerCard("La chela", "/imagenes/14_la_chela.jpg", "/audios/14_la_chela.mp3"));
        allCardsList.add(new PlayerCard("Las bitcoins", "/imagenes/15_las_bitcoins.jpg", "/audios/15_las_bitcoins.mp3"));
        allCardsList.add(new PlayerCard("El gringo", "/imagenes/16_el_gringo.jpg", "/audios/16_el_gringo.mp3"));
        allCardsList.add(new PlayerCard("El tequila", "/imagenes/17_el_tequila.jpg", "/audios/17_el_tequila.mp3"));
        allCardsList.add(new PlayerCard("La dieta", "/imagenes/18_la_dieta.jpg", "/audios/18_la_dieta.mp3"));
        allCardsList.add(new PlayerCard("El pride", "/imagenes/19_el_pride.jpg", "/audios/19_el_pride.mp3"));
        allCardsList.add(new PlayerCard("El playlist", "/imagenes/20_el_playlist.jpg", "/audios/20_el_playlist.mp3"));
        allCardsList.add(new PlayerCard("El scooter", "/imagenes/21_el_scooter.jpg", "/audios/21_el_scooter.mp3"));
        allCardsList.add(new PlayerCard("El tuitero", "/imagenes/22_el_tuitero.jpg", "/audios/22_el_tuitero.mp3"));
        allCardsList.add(new PlayerCard("El diseño web", "/imagenes/23_el_diseno_web.jpg", "/audios/23_el_diseno_web.mp3"));
        allCardsList.add(new PlayerCard("El ride share", "/imagenes/24_el_ride_share.jpg", "/audios/24_el_ride_share.mp3"));
        allCardsList.add(new PlayerCard("El manicure", "/imagenes/25_el_manicure.jpg", "/audios/25_el_manicure.mp3"));
        allCardsList.add(new PlayerCard("El orgánico", "/imagenes/26_el_organico.jpg", "/audios/26_el_organico.mp3"));
        allCardsList.add(new PlayerCard("El vape", "/imagenes/27_el_vape.jpg", "/audios/27_el_vape.mp3"));
        allCardsList.add(new PlayerCard("El sushi", "/imagenes/28_el_sushi.jpg", "/audios/28_el_sushi.mp3"));
        allCardsList.add(new PlayerCard("El detox", "/imagenes/29_el_detox.jpg", "/audios/29_el_detox.mp3"));
        allCardsList.add(new PlayerCard("El gluten", "/imagenes/30_el_gluten.jpg", "/audios/30_el_gluten.mp3"));
        allCardsList.add(new PlayerCard("La corona de flores", "/imagenes/31_la_corona_de_flores.jpg", "/audios/31_la_corona_de_flores.mp3"));
        allCardsList.add(new PlayerCard("El fidget spinner", "/imagenes/32_el_fidget_spinner.jpg", "/audios/32_el_fidget_spinner.mp3"));
        allCardsList.add(new PlayerCard("El dating app", "/imagenes/33_el_dating_app.jpg", "/audios/33_el_dating_app.mp3"));
        allCardsList.add(new PlayerCard("El piercing", "/imagenes/34_el_piercing.jpg", "/audios/34_el_piercing.mp3"));
        allCardsList.add(new PlayerCard("La bolsa reusable", "/imagenes/35_la_bolsa_reusable.jpg", "/audios/35_la_bolsa_reusable.mp3"));
        allCardsList.add(new PlayerCard("El coachella", "/imagenes/36_el_coachella.jpg", "/audios/36_el_coachella.mp3"));
        allCardsList.add(new PlayerCard("El DJ", "/imagenes/37_el_dj.jpg", "/audios/37_el_dj.mp3"));
        allCardsList.add(new PlayerCard("El brunch", "/imagenes/38_el_brunch.jpg", "/audios/38_el_brunch.mp3"));
        allCardsList.add(new PlayerCard("El vegano", "/imagenes/39_el_vegano.jpg", "/audios/39_el_vegano.mp3"));
        allCardsList.add(new PlayerCard("El latte art", "/imagenes/40_el_latte_art.jpg", "/audios/40_el_latte_art.mp3"));
        allCardsList.add(new PlayerCard("El mirrey", "/imagenes/41_el_mirrey.jpg", "/audios/41_el_mirrey.mp3"));
        allCardsList.add(new PlayerCard("El tatoo", "/imagenes/42_el_tatoo.jpg", "/audios/42_el_tatoo.mp3"));
        allCardsList.add(new PlayerCard("El troll", "/imagenes/43_el_troll.jpg", "/audios/43_el_troll.mp3"));
        allCardsList.add(new PlayerCard("El cactus", "/imagenes/44_el_cactus.jpg", "/audios/44_el_cactus.mp3"));
        allCardsList.add(new PlayerCard("El muro", "/imagenes/45_el_muro.jpg", "/audios/45_el_muro.mp3"));
        allCardsList.add(new PlayerCard("El flotador", "/imagenes/46_el_flotador.jpg", "/audios/46_el_flotador.mp3"));
        allCardsList.add(new PlayerCard("La kettlebell", "/imagenes/47_la_kettlebell.jpg", "/audios/47_la_kettlebell.mp3"));
        allCardsList.add(new PlayerCard("El bottle service", "/imagenes/48_el_bottle_service.jpg", "/audios/48_el_bottle_service.mp3"));
        allCardsList.add(new PlayerCard("La suculenta", "/imagenes/49_la_suculenta.jpg", "/audios/49_la_suculenta.mp3"));
        allCardsList.add(new PlayerCard("El güey", "/imagenes/50_el_guey.jpg", "/audios/50_el_guey.mp3"));
        allCardsList.add(new PlayerCard("La cruda", "/imagenes/51_la_cruda.jpg", "/audios/51_la_cruda.mp3"));
        allCardsList.add(new PlayerCard("El drone", "/imagenes/52_el_drone.jpg", "/audios/52_el_drone.mp3"));
    }

    public static List<PlayerCard> getAllCards() {
        return new ArrayList<>(allCardsList); // Retorna una copia para evitar modificaciones externas
    }
}
