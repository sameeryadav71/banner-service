package com.cbic.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cbic.entity.Banner;
import com.cbic.repository.BannerRepository;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepository bannerRepository;

	@Autowired
	private ImageService imageService;

	@Value("${project.image}")
	private String path;

	@Override
	public Banner saveBanner(Banner banner) throws IOException {
		// TODO Auto-generated method stub
		MultipartFile multipartFile = null;
		if (null == banner.getBannerImage()) {
			return null;
		} else {
			multipartFile = banner.getBannerImage();
			banner.setBannerName(multipartFile.getOriginalFilename());
			banner.setCreatedDate(new Date());
			banner.setPriority(1);
			imageService.uploadImge(path, multipartFile);
		}
		return bannerRepository.save(banner);
	}

	@Override
	public Banner findByBannerName(String bannerName) {
		// TODO Auto-generated method stub
		return bannerRepository.findByBannerName(bannerName);
	}

	@Override
	public List<Banner> getAllBanners() {
		// TODO Auto-generated method stub
		return bannerRepository.findAll();
	}

	@Override
	public Banner deleteBanner(Long bannerId) {
		// TODO Auto-generated method stub
		Optional<Banner> optional = bannerRepository.findById(bannerId);
		if (optional.isPresent()) {
			bannerRepository.deleteById(bannerId);
			return optional.get();
		}
		return null;
	}

	@Override
	public Banner saveBanner(MultipartFile multipartFile, String description, String priority) {
		Banner existingBanner = null;
		try {
			existingBanner = bannerRepository.findByBannerName(multipartFile.getOriginalFilename());
			if (null == existingBanner) {
				Banner banner = new Banner();
				banner.setBannerDesription(description);
				banner.setBannerName(multipartFile.getOriginalFilename());
				banner.setPriority(null != priority ? Integer.parseInt(priority) : 1);
				banner.setCreatedDate(new Date());
				banner.setImageBytes(multipartFile.getBytes());
				imageService.uploadImge(path, multipartFile);
				return bannerRepository.save(banner);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
