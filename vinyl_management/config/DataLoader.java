package com.vinyl.vinyl_management.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vinyl.vinyl_management.entity.Album;
import com.vinyl.vinyl_management.entity.Artist;
import com.vinyl.vinyl_management.entity.CollectionItem;
import com.vinyl.vinyl_management.entity.Collector;
import com.vinyl.vinyl_management.entity.Genre;
import com.vinyl.vinyl_management.entity.VinylRelease;
import com.vinyl.vinyl_management.entity.WishlistItem;
import com.vinyl.vinyl_management.repository.AlbumRepository;
import com.vinyl.vinyl_management.repository.ArtistRepository;
import com.vinyl.vinyl_management.repository.CollectionItemRepository;
import com.vinyl.vinyl_management.repository.CollectorRepository;
import com.vinyl.vinyl_management.repository.GenreRepository;
import com.vinyl.vinyl_management.repository.VinylReleaseRepository;
import com.vinyl.vinyl_management.repository.WishlistItemRepository;

@Component
public class DataLoader implements CommandLineRunner {
    
    //@Autowired
    private final GenreRepository genreRepository;
    
    //@Autowired
    private final ArtistRepository artistRepository;
    
    //@Autowired
    private final AlbumRepository albumRepository;
    //@Autowired
    private final VinylReleaseRepository vinylReleaseRepository;
    
    //@Autowired
    private final CollectorRepository collectorRepository;
    
    //@Autowired
    private final CollectionItemRepository collectionItemRepository;
    
    //@Autowired
    private final WishlistItemRepository wishlistItemRepository;

    public DataLoader(AlbumRepository albumRepository, ArtistRepository artistRepository, CollectionItemRepository collectionItemRepository, CollectorRepository collectorRepository, GenreRepository genreRepository, VinylReleaseRepository vinylReleaseRepository, WishlistItemRepository wishlistItemRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.collectionItemRepository = collectionItemRepository;
        this.collectorRepository = collectorRepository;
        this.genreRepository = genreRepository;
        this.vinylReleaseRepository = vinylReleaseRepository;
        this.wishlistItemRepository = wishlistItemRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Загрузка тестовых данных для Vinyl Tracker...");

        if (genreRepository.count() > 0) {
            System.out.println("Данные уже загружены, пропускаем загрузку...");
            System.out.println("Статистика БД:");
            System.out.println("- Жанры: " + genreRepository.count());
            System.out.println("- Исполнители: " + artistRepository.count());
            System.out.println("- Альбомы: " + albumRepository.count());
            System.out.println("- Релизы винила: " + vinylReleaseRepository.count());
            System.out.println("- Коллекционеры: " + collectorRepository.count());
            return;
        }
        if (albumRepository.count() > 0) {
            System.out.println("Данные уже загружены, пропускаем загрузку...");
            System.out.println("Статистика БД:");
            System.out.println("- Жанры: " + genreRepository.count());
            System.out.println("- Исполнители: " + artistRepository.count());
            System.out.println("- Альбомы: " + albumRepository.count());
            System.out.println("- Релизы винила: " + vinylReleaseRepository.count());
            System.out.println("- Коллекционеры: " + collectorRepository.count());
            return;
        }
        
        // 1. Создание жанров
        Genre rock = new Genre("Rock");
        Genre jazz = new Genre("Jazz");
        Genre classical = new Genre("Classical");
        Genre hipHop = new Genre("Hip Hop");
        Genre electronic = new Genre("Electronic");
        
        genreRepository.save(rock);
        genreRepository.save(jazz);
        genreRepository.save(classical);
        genreRepository.save(hipHop);
        genreRepository.save(electronic);
        
        System.out.println("Создано жанров: " + genreRepository.count());
        
        // 2. Создание исполнителей

        Artist pinkFloyd = new Artist("Pink Floyd");
        pinkFloyd.setCountry("United Kingdom");
        pinkFloyd.setFormedYear(1965);
        pinkFloyd.setBiography("Британская прогрессив-рок-группа, знаменитая своими концептуальными альбомами.");
        pinkFloyd.setPhotoUrl("https://example.com/pinkfloyd.jpg");
        
        Artist milesDavis = new Artist("Miles Davis");
        milesDavis.setCountry("United States");
        milesDavis.setFormedYear(1945);
        milesDavis.setBiography("Американский джазовый трубач и композитор, одна из самых влиятельных фигур в истории джаза.");
        milesDavis.setPhotoUrl("https://example.com/milesdavis.jpg");
        
        Artist theBeatles = new Artist("The Beatles");
        theBeatles.setCountry("United Kingdom");
        theBeatles.setFormedYear(1960);
        theBeatles.setBiography("Британская рок-группа из Ливерпуля, одна из самых успешных групп в истории музыки.");
        theBeatles.setPhotoUrl("https://example.com/beatles.jpg");
        
        Artist radiohead = new Artist("Radiohead");
        radiohead.setCountry("United Kingdom");
        radiohead.setFormedYear(1985);
        radiohead.setBiography("Британская альтернативная рок-группа, известная экспериментальным звучанием.");
        
        artistRepository.save(pinkFloyd);
        artistRepository.save(milesDavis);
        artistRepository.save(theBeatles);
        artistRepository.save(radiohead);
        
        System.out.println("Создано исполнителей: " + artistRepository.count());
        
        // 3. Создание альбомов 

        Album darkSide = new Album("The Dark Side of the Moon", 1973, "SHVL 804");
        darkSide.setAlbumArtUrl("https://example.com/darkside.jpg");
        darkSide.setArtist(pinkFloyd);
        darkSide.setGenre(rock);  

        Album kindOfBlue = new Album("Kind of Blue", 1959, "CL 1355");
        kindOfBlue.setAlbumArtUrl("https://example.com/kindofblue.jpg");
        kindOfBlue.setArtist(milesDavis);
        kindOfBlue.setGenre(jazz);  

        Album abbeyRoad = new Album("Abbey Road", 1969, "SO-383");
        abbeyRoad.setAlbumArtUrl("https://example.com/abbeyroad.jpg");
        abbeyRoad.setArtist(theBeatles);
        abbeyRoad.setGenre(rock);  

        Album okComputer = new Album("OK Computer", 1997, "7243 8 55229 2 7");
        okComputer.setAlbumArtUrl("https://example.com/okcomputer.jpg");
        okComputer.setArtist(radiohead);
        okComputer.setGenre(rock);  

        Album wishYouWereHere = new Album("Wish You Were Here", 1975, "SHVL 814");
        wishYouWereHere.setAlbumArtUrl("https://example.com/wishyouwerehere.jpg");
        wishYouWereHere.setArtist(pinkFloyd);
        wishYouWereHere.setGenre(rock);  

        albumRepository.save(darkSide);
        albumRepository.save(kindOfBlue);
        albumRepository.save(abbeyRoad);
        albumRepository.save(okComputer);
        albumRepository.save(wishYouWereHere);
        
        // 4. Создание релизов винила
        // Dark Side of the Moon - несколько релизов
        VinylRelease darksideUk = new VinylRelease();
        darksideUk.setAlbum(darkSide);
        darksideUk.setReleaseCountry("UK");
        darksideUk.setLabel("Harvest");
        darksideUk.setFormat("LP");
        darksideUk.setColor("Black");
        darksideUk.setWeight(180);
        darksideUk.setLimitedEdition(false);
        
        VinylRelease darksideUs = new VinylRelease();
        darksideUs.setAlbum(darkSide);
        darksideUs.setReleaseCountry("US");
        darksideUs.setLabel("Capitol");
        darksideUs.setFormat("LP");
        darksideUs.setColor("Black");
        darksideUs.setWeight(180);
        darksideUs.setLimitedEdition(true);
        darksideUs.setEditionSize(5000);
        
        // Kind of Blue - ограниченный тираж
        VinylRelease kindOfBlueLimited = new VinylRelease();
        kindOfBlueLimited.setAlbum(kindOfBlue);
        kindOfBlueLimited.setReleaseCountry("US");
        kindOfBlueLimited.setLabel("Columbia");
        kindOfBlueLimited.setFormat("LP");
        kindOfBlueLimited.setColor("Blue");
        kindOfBlueLimited.setWeight(200);
        kindOfBlueLimited.setLimitedEdition(true);
        kindOfBlueLimited.setEditionSize(1000);
        
        // Abbey Road
        VinylRelease abbeyRoadUk = new VinylRelease();
        abbeyRoadUk.setAlbum(abbeyRoad);
        abbeyRoadUk.setReleaseCountry("UK");
        abbeyRoadUk.setLabel("Apple");
        abbeyRoadUk.setFormat("LP");
        abbeyRoadUk.setColor("Black");
        abbeyRoadUk.setWeight(180);
        abbeyRoadUk.setLimitedEdition(false);
        
        vinylReleaseRepository.save(darksideUk);
        vinylReleaseRepository.save(darksideUs);
        vinylReleaseRepository.save(kindOfBlueLimited);
        vinylReleaseRepository.save(abbeyRoadUk);
        
        System.out.println("Создано релизов винила: " + vinylReleaseRepository.count());

        // 5. Создание коллекционеров
        Collector admin = new Collector("admin", "admin@vinyltracker.com", "admin123");
        admin.setDisplayName("Администратор системы");
        admin.setAvatarUrl("https://example.com/admin_avatar.jpg");
        
        Collector collector1 = new Collector("vinyl_lover", "collector1@example.com", "password123");
        collector1.setDisplayName("Иван Коллекционеров");
        collector1.setAvatarUrl("https://example.com/user1_avatar.jpg");
        
        Collector collector2 = new Collector("jazz_fan", "jazzfan@example.com", "jazz123");
        collector2.setDisplayName("Мария Джазовая");
        
        collectorRepository.save(admin);
        collectorRepository.save(collector1);
        collectorRepository.save(collector2);
        
        System.out.println("Создано коллекционеров: " + collectorRepository.count());
        
        // 6. Создание элементов коллекции 

        CollectionItem item1 = new CollectionItem(darksideUk, collector1);
        item1.setPurchaseDate(LocalDate.of(2023, 5, 15));
        item1.setPurchasePrice(49.99);
        item1.setCurrentValue(75.00);
        item1.setMediaCondition("NEAR_MINT");    
        item1.setSleeveCondition("VERY_GOOD");   
        item1.setNotes("Куплено на Discogs, отличное состояние");

        CollectionItem item2 = new CollectionItem(kindOfBlueLimited, collector1);
        item2.setPurchaseDate(LocalDate.of(2022, 10, 20));
        item2.setPurchasePrice(89.99);
        item2.setCurrentValue(120.00);
        item2.setMediaCondition("MINT");        
        item2.setSleeveCondition("MINT");       
        item2.setNotes("Лимитированный синий винил, номер 457/1000");

        CollectionItem item3 = new CollectionItem(abbeyRoadUk, collector2);
        item3.setPurchaseDate(LocalDate.of(2023, 1, 10));
        item3.setPurchasePrice(35.50);
        item3.setCurrentValue(45.00);
        item3.setMediaCondition("VERY_GOOD_PLUS");  
        item3.setSleeveCondition("GOOD");           
        item3.setNotes("Первое издание, небольшой износ конверта");

        // 7. Создание элементов wishlist (ИСПРАВЛЕНО!)
        WishlistItem wish1 = new WishlistItem();
        wish1.setVinylRelease(darksideUs);
        wish1.setCollector(collector1);
        wish1.setPriority("HIGH");    
        wish1.setMaxPrice(100.00);
        wish1.setNotes("Ищу американское издание Capitol");

        WishlistItem wish2 = new WishlistItem();
        wish2.setVinylRelease(kindOfBlueLimited);
        wish2.setCollector(collector2);
        wish2.setPriority("MEDIUM");  
        wish2.setMaxPrice(150.00);

        WishlistItem wish3 = new WishlistItem();
        wish3.setVinylRelease(abbeyRoadUk);
        wish3.setCollector(collector1);
        wish3.setPriority("LOW");    
        wish3.setMaxPrice(50.00);
        wish3.setNotes("Может быть заменен на переиздание");
        
        // 8. Вывод статистики
        System.out.println("\n=== СТАТИСТИКА БАЗЫ ДАННЫХ ===");
        System.out.println("Жанры: " + genreRepository.count());
        System.out.println("Исполнители: " + artistRepository.count());
        System.out.println("Альбомы: " + albumRepository.count());
        System.out.println("Релизы винила: " + vinylReleaseRepository.count());
        System.out.println("Коллекционеры: " + collectorRepository.count());
        System.out.println("Элементы коллекции: " + collectionItemRepository.count());
        System.out.println("Элементы wishlist: " + wishlistItemRepository.count());
        
        // Статистика по коллекции
        Double totalValue = collectionItemRepository.getTotalCollectionValue(collector1.getId());
        Long itemCount = collectionItemRepository.countByCollectorId(collector1.getId());
        System.out.println("\nКоллекция пользователя " + collector1.getDisplayName() + ":");
        System.out.println("Количество пластинок: " + itemCount);
        System.out.println("Общая стоимость: $" + String.format("%.2f", totalValue));
        
        System.out.println("\n Тестовые данные для Vinyl Tracker успешно загружены");
    }
}