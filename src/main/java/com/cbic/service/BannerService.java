package com.cbic.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cbic.entity.Banner;

public interface BannerService {

	public Banner saveBanner(MultipartFile multipartFile, String description, String priority);
	public Banner saveBanner(Banner banner) throws IOException;
	public Banner findByBannerName(String bannerName);
	public List<Banner> getAllBanners();
	public Banner deleteBanner(Long bannerId);
}
