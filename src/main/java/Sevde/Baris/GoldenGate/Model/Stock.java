package Sevde.Baris.GoldenGate.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private Double currentPrice;

    @Column
    private Double priceYesterday;

    @Column
    private Double priceOneWeekAgo;

    @Column
    private Double priceOneMonthAgo;

    @Column
    private Double priceThreeMonthsAgo;

    @Column
    private Double priceOneYearAgo;

    @Column
    private Double priceFiveYearsAgo;

    // matching id (country id == Stock id)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(mappedBy = "purchasedStocks")
    private Set<UserStock> userStocks = new HashSet<>();

}
