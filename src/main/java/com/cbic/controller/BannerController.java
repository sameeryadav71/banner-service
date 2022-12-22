package com.cbic.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cbic.entity.Banner;
import com.cbic.service.BannerService;
import com.cbic.service.ImageService;
import com.cbic.util.ImageResponse;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/banner")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@Autowired
	private ImageService imageService;

	@Value("${project.image}")
	private String path;

	@PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> saveBanner(@RequestPart("image") MultipartFile image,
			@RequestPart("description") String description,
			@RequestPart(name = "priority", required = false) String priority) {
		Banner banner = null;
		try {
			banner = bannerService.saveBanner(image, description, priority);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ImageResponse(null, "Image not uploaded"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (banner != null) {
			banner.setMessage("Image uploaded successfully");
			return new ResponseEntity<>(banner,HttpStatus.OK);	
		}
		else
			return new ResponseEntity<>(new ImageResponse(image.getOriginalFilename(),
					"Image already exists please choose different image!!"), HttpStatus.OK);

	}

	@GetMapping("/getAllBanners")
	public List<Banner> getAllBanners() {
		List<Banner> banners = null;
		try {
			banners = bannerService.getAllBanners();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return banners;

	}
	
	@GetMapping(value = "db/{imageName}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) throws IOException {

    	byte[] bytes = null;
    	Banner banner = bannerService.findByBannerName(imageName);
    	if(null != banner)
    		bytes = banner.getImageBytes();
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

	@GetMapping(value = "/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void imageDownload(@PathVariable("imageName") String imageName, HttpServletResponse httpServletResponse)
			throws IOException {
		InputStream inputStream = this.imageService.getResource(path, imageName);
		httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, httpServletResponse.getOutputStream());
	}
}
