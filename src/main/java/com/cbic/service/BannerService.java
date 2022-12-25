package com.cbic.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cbic.entity.Banner;

public interface BannerService {

	public Banner saveBanner(MultipartFile multipartFile, String description, Integer priority, Date startDate, Date endDate);
	public Banner saveBanner(Banner banner) throws IOException;
	public Banner findByBannerName(String bannerName);
	public List<Banner> getAllBanners();
	public List<Banner> getTop6Banners();
	public Banner deleteBanner(Long bannerId);
}
