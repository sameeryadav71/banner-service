package com.cbic.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "banner")
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@Transient
	private MultipartFile bannerImage;
	@Transient
	private String message;
	private String bannerName;
	private Integer priority;
	private Date createdDate;
	private String bannerDesription;
	
	@JsonIgnore
	@Column(length = 50000000)
	private byte[] imageBytes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		if (priority != null)
			this.priority = priority;
		else
			this.priority = 1;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getBannerDesription() {
		return bannerDesription;
	}

	public void setBannerDesription(String bannerDesription) {
		this.bannerDesription = bannerDesription;
	}

	public byte[] getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}

	public MultipartFile getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(MultipartFile bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", bannerImage=" + bannerImage + ", message=" + message + ", bannerName="
				+ bannerName + ", priority=" + priority + ", createdDate=" + createdDate + ", bannerDesription="
				+ bannerDesription + ", imageBytes=" + Arrays.toString(imageBytes) + "]";
	}

}
