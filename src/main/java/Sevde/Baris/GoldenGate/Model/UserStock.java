package Sevde.Baris.GoldenGate.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table
public class UserStock {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Column
    private Date purchasingDate;

    @Column
    private Double purchasingPrice;

    @Column
    private Double purchasedLotAmount;

    @ManyToMany
    @JoinTable(name = "stock_user_stock",
    joinColumns = @JoinColumn(name = "user_stock_id"),
    inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private Set<Stock> purchasedStocks = new HashSet<>();

    // matching userStock, Portfolio_id
    @ManyToMany(mappedBy = "userStocks")
    private Set<Portfolio> portfolios = new HashSet<>();
}
