package com.cbic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cbic.entity.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long>{
	
	public Banner findByBannerName(String bannerName);
}
