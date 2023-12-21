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
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "user_stock_portfolio",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "user_stock_id")
    )
    private Set<UserStock> userStocks = new HashSet<>();

}
