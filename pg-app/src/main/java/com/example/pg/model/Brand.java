package com.example.pg.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "brand")
public class Brand {
	
	@Id
    @Column(name = "brand_id", length = 10, nullable = false)
    private String brandId;
	
	@Column(name = "brand_name", length = 50, nullable = false)
    private String brandName;

    @Column(name = "brand_type", length = 20, nullable = false)
    private String brandType;

    @Column(name = "inst_id", nullable = false)
    private Long institutionId;
	
 // Relationships
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BinRange> binRanges = new ArrayList<>();

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<BrandRule> brandRules = new ArrayList<>();

}
